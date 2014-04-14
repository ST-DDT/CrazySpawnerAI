package de.st_ddt.crazyspawner.entities.util.ai;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public class GoalInformation implements Comparable<GoalInformation>
{

	private final Goal goal;
	private final int priority;

	public GoalInformation(final Goal goal, final int priority)
	{
		super();
		this.goal = goal;
		this.priority = priority;
	}

	public final Goal getGoal()
	{
		return goal;
	}

	/**
	 * @return The priority of the goal.
	 */
	public final int getPriority()
	{
		return priority;
	}

	@Override
	public final int compareTo(final GoalInformation goalInformation)
	{
		return Integer.compare(priority, goalInformation.priority);
	}

	@Override
	public String toString()
	{
		return goal.toString() + " @prio: " + priority;
	}
}
