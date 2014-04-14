package de.st_ddt.crazyspawner.craftbukkit.v1_7_R3.util.ai;

import net.minecraft.server.v1_7_R3.PathfinderGoal;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.util.ai.CrazySpawnerAIGoalWrapper;

public final class CrazySpawnerAIGoalWrapperImpl extends PathfinderGoal implements CrazySpawnerAIGoalWrapper
{

	private final Goal goal;

	public CrazySpawnerAIGoalWrapperImpl(final Goal goal)
	{
		super();
		this.goal = goal;
		// Set Mutex
		a(goal.getMutexBitFlags());
	}

	// CheckRun - Start
	@Override
	public boolean a()
	{
		return goal.shouldExecute(false);
	}

	// CheckRun - Continue
	@Override
	public boolean b()
	{
		return goal.shouldExecute(true);
	}

	// Check Interruptible
	@Override
	public boolean i()
	{
		return goal.isInterruptible();
	}

	// Start
	@Override
	public void c()
	{
		goal.start();
	}

	// Update
	@Override
	public void e()
	{
		goal.update();
	}

	// Reset
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