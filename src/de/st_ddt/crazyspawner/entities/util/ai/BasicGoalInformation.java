package de.st_ddt.crazyspawner.entities.util.ai;

public abstract class BasicGoalInformation<P> implements GoalInformation
{

	private final P pathfinderGoal;
	private final int priority;

	public BasicGoalInformation(final P pathfinderGoal, final int priority)
	{
		super();
		this.pathfinderGoal = pathfinderGoal;
		this.priority = priority;
	}

	@Override
	public final P getPathfinderGoal()
	{
		return pathfinderGoal;
	}

	@Override
	public final int getPriority()
	{
		return priority;
	}

	@Override
	public String toString()
	{
		return pathfinderGoal.getClass().getSimpleName() + " @prio: " + priority;
	}
}
