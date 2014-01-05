package de.st_ddt.crazyspawner.entities.properties.ai.action.goals;

/**
 * Represents a goal a creature tries to achieve.
 */
public interface Goal
{

	/**
	 * This flag expresses that the goal influences the entity's movement.
	 */
	public final static int MUTEX_FLAG_MOVE = 1;
	/**
	 * This flag expresses that the goal influences the entity's look.
	 */
	public final static int MUTEX_FLAG_LOOK = 2;

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

	/**
	 * This method prevents goals executing simultaneously actions that will overwrite each other.<br>
	 * For example look at entity and random lookaround.
	 * 
	 * @return The Mutex Bit Flags of this goal.
	 */
	public int getMutexBitFlags();
}
