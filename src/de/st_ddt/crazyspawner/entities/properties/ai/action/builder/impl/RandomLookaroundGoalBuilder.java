package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.RandomLookaroundGoal;

public class RandomLookaroundGoalBuilder extends BasicGoalBuilder
{

	public RandomLookaroundGoalBuilder()
	{
		super();
	}

	public RandomLookaroundGoalBuilder(final ConfigurationSection config)
	{
		super(config);
	}

	@Override
	public String getType()
	{
		return "RandomLookaround";
	}

	@Override
	public Goal build(final Creature entity)
	{
		return new RandomLookaroundGoal(entity);
	}
}
