package de.st_ddt.crazyspawner.entities.util.ai;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

/**
 * Bride class between minecraft's <b>PathfinderGoal</b> and {@link Goal}.<br>
 * Wraps a goal so it can be attached to minecraft entities.
 * <p>
 * Implementations of this class must:
 * <ul>
 * <li>Extend version dependent <b>net.minecraft.server.PathfinderGoal</b>.</li>
 * <li>Not contain any logic except the wrapping logic.</li>
 * </ul>
 */
public interface GoalWrapper extends Goal
{
}
