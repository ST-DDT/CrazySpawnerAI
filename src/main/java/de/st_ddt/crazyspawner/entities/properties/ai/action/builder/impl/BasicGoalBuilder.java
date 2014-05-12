package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.GoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public abstract class BasicGoalBuilder implements GoalBuilder
{

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
