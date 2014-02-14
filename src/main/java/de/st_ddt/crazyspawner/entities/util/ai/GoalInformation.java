package de.st_ddt.crazyspawner.entities.util.ai;

public interface GoalInformation extends PathfinderGoalProvider
{

	@Override
	public Object getPathfinderGoal();

	public int getPriority();
}
