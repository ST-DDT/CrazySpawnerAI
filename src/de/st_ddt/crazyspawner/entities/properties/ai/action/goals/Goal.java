package de.st_ddt.crazyspawner.entities.properties.ai.action.goals;

/**
 * Represents a goal a creature tries to achieve.
 */
public interface Goal
{

	/**
	 * Checks whether this goal should (still) be executed or not.
	 * 
	 * @param started
	 *            True, if the goal is currently active and has already been started.
	 * @return True, if the task should be executed. False otherwise.
	 */
	public boolean shouldExecute(boolean started);

	/**
	 * Checks whether this task can be interrupted by a higher priority (=lower value) task.
	 * 
	 * @return True, if this task may be interrupted. False otherwise.
	 */
	public boolean isInterruptible();

	/**
	 * Execute a one shot task or start executing a continuous task.<br>
	 * Only called on startup.
	 */
	public void start();

	/**
	 * Updates this goal.
	 */
	public void update();

	/**
	 * Resets this goal.
	 */
	public void reset();
}
