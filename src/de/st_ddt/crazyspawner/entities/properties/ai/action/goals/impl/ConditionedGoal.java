package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.checker.CreatureConditionChecker.SimpleCreatureConditionChecker;

public final class ConditionedGoal extends BasicGoal
{

	protected final Condition startCondition;
	protected final Condition continueCondition;
	protected final Goal goal;

	public ConditionedGoal(final Creature entity, final Condition startCondition, final Condition continueCondition, final Goal goal)
	{
		super(entity, goal.getMutexBitFlags());
		this.startCondition = startCondition;
		this.continueCondition = continueCondition;
		this.goal = goal;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		if (started)
			return continueCondition.check(new SimpleCreatureConditionChecker(entity)) && goal.shouldExecute(true);
		else
			return startCondition.check(new SimpleCreatureConditionChecker(entity)) && goal.shouldExecute(false);
	}

	@Override
	public boolean isInterruptible()
	{
		return goal.isInterruptible();
	}

	@Override
	public void start()
	{
		goal.start();
	}

	@Override
	public void update()
	{
		goal.update();
	}

	@Override
	public void reset()
	{
		goal.reset();
	}

	@Override
	public int getMutexBitFlags()
	{
		return goal.getMutexBitFlags();
	}
}
