package de.st_ddt.crazyspawner.entities.util.ai;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

/**
 * Wraps a goal so it can be attached to minecraft entities.<br>
 * There cannot be any abstract implementations of this.<br>
 * Implementations must extends version dependent PathfinderGoal class.
 */
public interface GoalWrapper extends Goal
{

	public Goal getGoal();
}
