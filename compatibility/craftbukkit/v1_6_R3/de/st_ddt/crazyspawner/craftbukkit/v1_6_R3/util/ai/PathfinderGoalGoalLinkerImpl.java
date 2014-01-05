package de.st_ddt.crazyspawner.craftbukkit.v1_6_R3.util.ai;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import de.st_ddt.crazyspawner.entities.util.ai.PathfinderGoalGoalLinker;

public final class PathfinderGoalGoalLinkerImpl extends PathfinderGoal implements PathfinderGoalGoalLinker
{

	private final PathfinderGoal goal;

	public PathfinderGoalGoalLinkerImpl(final PathfinderGoal goal)
	{
		super();
		this.goal = goal;
		// Set Mutex
		a(goal.j());
	}

	@Override
	public boolean a()
	{
		return goal.a();
	}

	@Override
	public boolean b()
	{
		return goal.b();
	}

	@Override
	public void c()
	{
		goal.c();
	}

	@Override
	public void e()
	{
		goal.e();
	}

	@Override
	public void d()
	{
		goal.d();
	}

	@Override
	public boolean i()
	{
		return goal.i();
	}

	@Override
	public PathfinderGoal getPathfinderGoal()
	{
		return goal;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		if (started)
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
}
