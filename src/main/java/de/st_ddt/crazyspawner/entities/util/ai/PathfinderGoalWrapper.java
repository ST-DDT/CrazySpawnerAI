package de.st_ddt.crazyspawner.entities.util.ai;

/**
 * Instances of this class wrap a <b>PathfinderGoal</b> so it can be handled by CrazySpawnerAI.
 * 
 * @see GoalWrapper
 */
public interface PathfinderGoalWrapper extends GoalWrapper
{

	public Object getPathfinderGoal();
}
