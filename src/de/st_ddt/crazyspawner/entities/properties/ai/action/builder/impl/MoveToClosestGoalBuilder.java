package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import java.util.Collection;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.MoveToClosestGoal;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.ConditionHelper;
import de.st_ddt.crazyutil.conditions.Condition_AND;
import de.st_ddt.crazyutil.conditions.Condition_TRUE;
import de.st_ddt.crazyutil.conditions.ExtendedConditionHelper;
import de.st_ddt.crazyutil.conditions.entity.Condition_Entity_Type;
import de.st_ddt.crazyutil.conditions.entity.ai.goal.Condition_Entity_AI_Goal_Distance;

public class MoveToClosestGoalBuilder extends BasicGoalBuilder
{

	protected final Condition moveToCondition;
	protected final double speed;

	public MoveToClosestGoalBuilder(final double speed)
	{
		super();
		this.moveToCondition = new Condition_TRUE();
		this.speed = speed;
	}

	public MoveToClosestGoalBuilder(final double speed, final double maxDistance, final EntityType... types)
	{
		super();
		final Condition_AND condition = new Condition_AND();
		condition.getConditions().add(new Condition_Entity_AI_Goal_Distance(0, 1, maxDistance));
		condition.getConditions().add(new Condition_Entity_Type(0, types));
		this.moveToCondition = condition;
		this.speed = speed;
	}

	public MoveToClosestGoalBuilder(final double speed, final double maxDistance, final Collection<EntityType> types)
	{
		super();
		final Condition_AND condition = new Condition_AND();
		condition.getConditions().add(new Condition_Entity_AI_Goal_Distance(0, 1, maxDistance));
		condition.getConditions().add(new Condition_Entity_Type(0, types));
		this.moveToCondition = condition;
		this.speed = speed;
	}

	public MoveToClosestGoalBuilder(final Condition moveToCondition, final double speed)
	{
		super();
		this.moveToCondition = moveToCondition;
		this.speed = speed;
	}

	public MoveToClosestGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		Condition moveToCondition = null;
		try
		{
			moveToCondition = ConditionHelper.simpleLoad(config.getConfigurationSection("moveToCondition"), "Entity", "Goal");
			moveToCondition = ExtendedConditionHelper.simpleSecure(moveToCondition, Entity.class, MoveToClosestGoal.class);
		}
		catch (final Exception e)
		{
			System.err.println(config.getName() + "'s watch condition was corrupted/invalid and has been removed!");
			moveToCondition = new Condition_TRUE();
		}
		this.moveToCondition = moveToCondition;
		this.speed = Math.max(config.getDouble("speed", 1), 0);
	}

	@Override
	public String getType()
	{
		return "MoveToClosest";
	}

	@Override
	public Goal build(final Creature entity)
	{
		return new MoveToClosestGoal(entity, moveToCondition, speed);
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		super.save(config, path);
		ConditionHelper.simpleSave(moveToCondition, config, path + "moveToCondition.", "Entity", "Goal");
		config.set(path + "speed", speed);
	}
}
