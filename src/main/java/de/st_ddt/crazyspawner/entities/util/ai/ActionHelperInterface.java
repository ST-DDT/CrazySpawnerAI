package de.st_ddt.crazyspawner.entities.util.ai;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

/**
 * Helper interface for {@link ActionHelper}, providing access to version dependent methods/features.
 */
public interface ActionHelperInterface
{

	public List<Class<? extends ActionHelperInterface>> ACTIONHELPERCLASSES = new ArrayList<>();

	/**
	 * Adds a {@link Goal} to the {@link Creature}'s list of goals.<br>
	 * <b>DO NOT ADD OTHER CREATURE'S GOALS!</b>
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param goal
	 *            The new {@link Goal} for this {@link Creature}.
	 * @param priority
	 *            The priority for the {@link Goal}.
	 * @throws Exception
	 *             If anything went wrong.
	 */
	public void addGoal(Creature entity, Goal goal, int priority) throws Exception;

	/**
	 * Returns a list off goals this {@link Creature} tries to achieve.<br>
	 * Modifing the returned list has no impact on the real AI.<br>
	 * Modifing the elements in this list has.
	 * 
	 * @param entity
	 *            The {@link Creature} whose {@link Goal}s should be listed.
	 * @return A list of {@link Goal}s the given {@link Creature} tries to achieve.
	 * @throws Exception
	 *             If anything went wrong.
	 */
	public List<GoalInformation> getGoals(Creature entity) throws Exception;

	/**
	 * Removes all goals from this entity.
	 * 
	 * @param entity
	 *            The entity which goals should be removed.
	 * @throws Exception
	 *             If anything went wrong.
	 */
	public void clearGoals(Creature entity) throws Exception;

	/**
	 * Returns a navigation controller for the given creature.
	 * 
	 * @param entity
	 *            The entity which navigation controller should be returned.
	 * @return The navigation controller for this entity.
	 */
	public Navigation getNavigation(Creature entity);
}
