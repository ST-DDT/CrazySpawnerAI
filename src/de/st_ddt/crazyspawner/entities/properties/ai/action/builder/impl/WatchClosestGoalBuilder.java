package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import java.util.Collection;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.WatchClosestGoal;
import de.st_ddt.crazyspawner.entities.util.ai.Navigation;
import de.st_ddt.crazyutil.conditions.BasicCondition;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.Condition_AND;
import de.st_ddt.crazyutil.conditions.Condition_TRUE;
import de.st_ddt.crazyutil.conditions.entity.Condition_Entity_Type;
import de.st_ddt.crazyutil.conditions.entity.ai.goal.Condition_Entity_AI_Goal_Distance;

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

	public WatchClosestGoalBuilder(final double maxDistance, final EntityType... types)
	{
		super();
		final Condition_AND condition = new Condition_AND();
		condition.getConditions().add(new Condition_Entity_AI_Goal_Distance(maxDistance));
		condition.getConditions().add(new Condition_Entity_Type(types));
		this.watchCondition = condition;
		this.yawRotationSpeed = Navigation.DEFAULTROTATIONSPEED;
		this.pitchRotationSpeed = Navigation.DEFAULTROTATIONSPEED;
	}

	public WatchClosestGoalBuilder(final double maxDistance, final Collection<EntityType> types)
	{
		super();
		final Condition_AND condition = new Condition_AND();
		condition.getConditions().add(new Condition_Entity_AI_Goal_Distance(maxDistance));
		condition.getConditions().add(new Condition_Entity_Type(types));
		this.watchCondition = condition;
		this.yawRotationSpeed = Navigation.DEFAULTROTATIONSPEED;
		this.pitchRotationSpeed = Navigation.DEFAULTROTATIONSPEED;
	}

	public WatchClosestGoalBuilder(final Condition watchCondition, final double yawRotationSpeed, final double pitchRotationSpeed)
	{
		super();
		this.watchCondition = watchCondition;
		this.yawRotationSpeed = yawRotationSpeed;
		this.pitchRotationSpeed = pitchRotationSpeed;
	}

	public WatchClosestGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		Condition watchCondition = null;
		try
		{
			watchCondition = BasicCondition.load(config.getConfigurationSection("watchCondition"));
		}
		catch (final Exception e)
		{
			System.err.println(config.getName() + "'s watch condition was corrupted/invalid and has been removed!");
			watchCondition = new Condition_TRUE();
		}
		this.watchCondition = watchCondition;
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

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		super.save(config, path);
		watchCondition.save(config, path + "watchCondition.");
		config.set(path + "yawRotationSpeed", yawRotationSpeed);
		config.set(path + "pitchRotationSpeed", pitchRotationSpeed);
	}
}
