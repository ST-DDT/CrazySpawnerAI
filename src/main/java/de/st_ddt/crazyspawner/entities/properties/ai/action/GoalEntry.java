package de.st_ddt.crazyspawner.entities.properties.ai.action;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.GoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.BasicGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.util.ai.GoalInformation;
import de.st_ddt.crazyutil.ConfigurationSaveable;

public class GoalEntry implements ConfigurationSaveable
{

	public static GoalEntry load(final ConfigurationSection config) throws Throwable
	{
		if (config == null)
			throw new IllegalArgumentException("ConfigurationSection cannot be NULL!");
		final GoalBuilder builder = BasicGoalBuilder.load(config.getConfigurationSection("goal"));
		final int priority = config.getInt("priority", 100);
		return new GoalEntry(builder, priority);
	}

	private final GoalBuilder builder;
	private final int priority;

	public GoalEntry(final GoalBuilder builder, final int priority)
	{
		super();
		this.builder = builder;
		this.priority = priority;
	}

	public GoalBuilder getBuilder()
	{
		return builder;
	}

	public int getPriority()
	{
		return priority;
	}

	public Goal buildGoal(final Creature creature)
	{
		return builder.build(creature);
	}

	public GoalInformation buildGoalInformation(final Creature creature)
	{
		return new GoalInformation(buildGoal(creature), priority);
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		builder.save(config, path + "goal.");
		config.set(path + "priority", priority);
	}
}
