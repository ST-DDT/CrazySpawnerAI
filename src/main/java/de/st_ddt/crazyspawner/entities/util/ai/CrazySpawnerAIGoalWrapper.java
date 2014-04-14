package de.st_ddt.crazyspawner.entities.util.ai;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

/**
 * Instances of this class wrap a {@link Goal} so it can be inserted into minecraft's AI.
 * 
 * @see GoalWrapper
 */
public interface CrazySpawnerAIGoalWrapper extends GoalWrapper
{

	public Goal getGoal();
}
