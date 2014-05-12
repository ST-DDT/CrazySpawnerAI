package de.st_ddt.crazyspawner.entities.properties.ai.action.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.ConditionedGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.MoveToClosestGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.MoveToRoutePointGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.RandomLookaroundGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.RandomStrollGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.StareInDirectionGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.WatchClosestGoalBuilder;

public class GoalBuilderHelper
{

	private final static Map<String, Class<? extends GoalBuilder>> GOALBUILDERCLASSES = GoalBuilder.GOALBUILDERCLASSES;
	static
	{
		registerGoalBuilder(ConditionedGoalBuilder.class);
		registerGoalBuilder(MoveToClosestGoalBuilder.class);
		registerGoalBuilder(MoveToRoutePointGoalBuilder.class);
		registerGoalBuilder(RandomLookaroundGoalBuilder.class);
		registerGoalBuilder(RandomStrollGoalBuilder.class);
		registerGoalBuilder(StareInDirectionGoalBuilder.class);
		registerGoalBuilder(WatchClosestGoalBuilder.class);
	}

	public static GoalBuilder load(final ConfigurationSection config) throws Throwable
	{
		if (config == null)
			throw new IllegalArgumentException("ConfigurationSection cannot be NULL!");
		final String type = config.getString("type");
		if (type == null)
			throw new IllegalArgumentException("GoalBuilderType cannot be NULL!");
		final Class<? extends GoalBuilder> clazz = GOALBUILDERCLASSES.get(type);
		if (clazz == null)
			throw new IllegalArgumentException("GoalBuilderType cannot be NULL! The GoalBuilderType \"" + type + "\" is corruped/was not found, please check that!");
		try
		{
			final Constructor<? extends GoalBuilder> constructor = clazz.getConstructor(ConfigurationSection.class);
			return constructor.newInstance(config);
		}
		catch (final InvocationTargetException e)
		{
			if (e.getCause() instanceof IllegalArgumentException)
			{
				// TODO
				System.err.println("WARNING: You messed up your config {path}");
				throw e.getCause();
			}
			else
			{
				System.err.println("WARNING: Serious bug detected, please report this issue!");
				throw e.getCause();
			}
		}
		catch (final Exception e)
		{
			System.err.println("WARNING: Serious bug detected, please report this issue!");
			throw e;
		}
	}

	public static void registerGoalBuilder(final Class<? extends GoalBuilder> clazz, final String... names)
	{
		if (names.length == 0)
			registerGoalBuilder(clazz, clazz.getSimpleName().substring(0, clazz.getSimpleName().length() - 11));
		else
			for (final String name : names)
				GOALBUILDERCLASSES.put(name, clazz);
	}

	private GoalBuilderHelper()
	{
	}
}
