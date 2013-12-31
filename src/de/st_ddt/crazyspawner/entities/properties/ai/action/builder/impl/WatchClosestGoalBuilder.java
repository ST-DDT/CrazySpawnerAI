package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.WatchClosestGoal;
import de.st_ddt.crazyspawner.entities.util.ai.Navigation;
import de.st_ddt.crazyutil.conditions.BasicCondition;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.Condition_TRUE;

public class WatchClosestGoalBuilder extends BasicGoalBuilder
{

	protected final Condition watchCondition;
	protected final double yawRotationSpeed;
	protected final double pitchRotationSpeed;

	public WatchClosestGoalBuilder()
	{
		super();
		this.watchCondition = new Condition_TRUE();
		this.yawRotationSpeed = Navigation.DEFAULTROTATIONSPEED;
		this.pitchRotationSpeed = Navigation.DEFAULTROTATIONSPEED;
	}

	public WatchClosestGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		Condition watchedCondition = null;
		try
		{
			watchedCondition = BasicCondition.load(config.getConfigurationSection("startCondition"));
		}
		catch (final Exception e)
		{
			System.err.println(config.getName() + "'s watch condition was corrupted/invalid and has been removed!");
			watchedCondition = new Condition_TRUE();
		}
		this.watchCondition = watchedCondition;
		this.yawRotationSpeed = Math.max(config.getDouble("yawRotationSpeed", Navigation.DEFAULTROTATIONSPEED), 0);
		this.pitchRotationSpeed = Math.max(config.getDouble("pitchRotationSpeed", Navigation.DEFAULTROTATIONSPEED), 0);
	}

	@Override
	public String getType()
	{
		return "WatchClosest";
	}

	@Override
	public Goal build(final Creature entity)
	{
		return new WatchClosestGoal(entity, watchCondition, 0, 0);
	}
}
