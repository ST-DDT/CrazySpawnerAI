package de.st_ddt.crazyspawner.craftbukkit.v1_7_R1.util.ai;

import net.minecraft.server.v1_7_R1.PathfinderGoal;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.util.ai.GoalWrapper;

public final class GoalWrapperImpl extends PathfinderGoal implements GoalWrapper
{

	private final Goal goal;

	public GoalWrapperImpl(final Goal goal)
	{
		super();
		this.goal = goal;
		// Set Mutex
		a(goal.getMutexBitFlags());
	}

	@Override
	public boolean a()
	{
		return goal.shouldExecute(false);
	}

	@Override
	public boolean b()
	{
		return goal.shouldExecute(true);
	}

	@Override
	public boolean i()
	{
		return goal.isInterruptible();
	}

	@Override
	public void c()
	{
		goal.start();
	}

	@Override
	public void e()
	{
		goal.update();
	}

	@Override
	public void d()
	{
		goal.reset();
	}

	@Override
	public Goal getGoal()
	{
		return goal;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		return goal.shouldExecute(started);
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
