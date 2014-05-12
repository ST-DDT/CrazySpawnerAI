package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.GoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public abstract class BasicGoalBuilder implements GoalBuilder
{

	public static GoalBuilder load(final ConfigurationSection config) throws Throwable
	{
		if (config == null)
			throw new IllegalArgumentException("ConfigurationSection cannot be NULL!");
		final String type = config.getString("type");
		if (type == null)
			throw new IllegalArgumentException("GoalBuilderType cannot be NULL!");
		final Class<? extends GoalBuilder> clazz = GOALBUILDERCLASSES.get(type);
		if (clazz == null)
			throw new IllegalArgumentException("GoalBuilderType cannot be NULL! The GoalBuilderType is corruped, please check that!");
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

	public BasicGoalBuilder()
	{
		super();
	}

	public BasicGoalBuilder(final ConfigurationSection config)
	{
		super();
	}

	@Override
	public abstract String getType();

	@Override
	public abstract Goal build(Creature entity);

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		config.set(path + "type", getType());
	}
}
