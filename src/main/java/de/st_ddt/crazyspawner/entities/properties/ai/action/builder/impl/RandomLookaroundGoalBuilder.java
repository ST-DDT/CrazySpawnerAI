package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.RandomLookaroundGoal;

public final class RandomLookaroundGoalBuilder extends BasicGoalBuilder
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
	public RandomLookaroundGoal build(final Creature entity)
	{
		return new RandomLookaroundGoal(entity);
	}
}
