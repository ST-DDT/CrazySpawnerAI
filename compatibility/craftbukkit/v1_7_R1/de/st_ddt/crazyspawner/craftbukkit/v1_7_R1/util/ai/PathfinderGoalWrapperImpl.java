package de.st_ddt.crazyspawner.craftbukkit.v1_7_R1.util.ai;

import net.minecraft.server.v1_7_R1.PathfinderGoal;
import de.st_ddt.crazyspawner.entities.util.ai.PathfinderGoalWrapper;

public final class PathfinderGoalWrapperImpl extends PathfinderGoal implements PathfinderGoalWrapper
{

	private final PathfinderGoal goal;

	public PathfinderGoalWrapperImpl(final PathfinderGoal goal)
	{
		super();
		this.goal = goal;
		// Set Mutex
		a(goal.j());
	}

	// CheckRun - Start
	@Override
	public boolean a()
	{
		return goal.a();
	}

	// CheckRun - Continue
	@Override
	public boolean b()
	{
		return goal.b();
	}

	// Check Interruptible
	@Override
	public boolean i()
	{
		return goal.i();
	}

	// Start
	@Override
	public void c()
	{
		goal.c();
	}

	// Update
	@Override
	public void e()
	{
		goal.e();
	}

	// Reset
	@Override
	public void d()
	{
		goal.d();
	}

	@Override
	public PathfinderGoal getPathfinderGoal()
	{
		return goal;
	}

	@Override
	public boolean shouldExecute(final boolean isRunning)
	{
		if (isRunning)
			return goal.b();
		else
			return goal.a();
	}

	@Override
	public boolean isInterruptible()
	{
		return goal.i();
	}

	@Override
	public void start()
	{
		goal.c();
	}

	@Override
	public void update()
	{
		goal.e();
	}

	@Override
	public void reset()
	{
		goal.d();
	}

	@Override
	public int getMutexBitFlags()
	{
		return goal.j();
	}

	@Override
	public String toString()
	{
		return goal.getClass().getSimpleName();
	}
}
