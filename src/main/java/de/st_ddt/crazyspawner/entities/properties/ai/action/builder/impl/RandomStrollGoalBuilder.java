package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.RandomStrollGoal;
import de.st_ddt.crazyspawner.entities.util.ai.Navigation;

public final class RandomStrollGoalBuilder extends BasicGoalBuilder
{

	private final int horizontalOffset;
	private final int heightOffset;
	private final double speed;

	public RandomStrollGoalBuilder(final double speed)
	{
		super();
		this.horizontalOffset = 10;
		this.heightOffset = 7;
		this.speed = Navigation.DEFAULTMOVEMENTSPEED;
	}

	public RandomStrollGoalBuilder(final int horizontalOffset, final int heightOffset, final double speed)
	{
		super();
		this.horizontalOffset = horizontalOffset;
		this.heightOffset = heightOffset;
		this.speed = speed;
	}

	public RandomStrollGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		this.horizontalOffset = config.getInt("horizontalOffset", 10);
		this.heightOffset = config.getInt("heightOffset", 7);
		this.speed = config.getDouble("speed", Navigation.DEFAULTMOVEMENTSPEED);
	}

	@Override
	public String getType()
	{
		return "RandomStroll";
	}

	@Override
	public RandomStrollGoal build(final Creature entity)
	{
		return new RandomStrollGoal(entity, horizontalOffset, heightOffset, speed);
	}
}
