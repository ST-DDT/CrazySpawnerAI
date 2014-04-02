package de.st_ddt.crazyspawner.entities.util.ai;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.GoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public interface ActionHelperInterface
{

	public List<Class<? extends ActionHelperInterface>> ACTIONHELPERCLASSES = new ArrayList<>();

	public Object getHandle(Entity entity);

	public Object getHandle(Creature entity);

	public Creature getEntity(Object entity);

	/**
	 * Removes all goals from this entity.
	 * 
	 * @param entity
	 *            The entity which goals should be removed.
	 */
	public void clearGoals(Creature entity) throws Exception;

	/**
	 * Appends a goal to the {@link Creature}'s list of goals.
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param goal
	 *            The new {@link Goal} for this {@link Creature}.
	 */
	public void addGoal(Creature entity, GoalBuilder goal, int priority) throws Exception;

	/**
	 * Appends a goal to the {@link Creature}'s list of goals.<br>
	 * DO NOT ADD OTHER CREATURE'S GOALS!
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param information
	 *            The new {@link GoalInformation} for this {@link Creature}.
	 * @throws Exception
	 */
	public void addGoal(Creature entity, GoalInformation information) throws Exception;

	/**
	 * Appends a goal to the {@link Creature}'s list of goals.<br>
	 * DO NOT ADD OTHER CREATURE'S GOALS!
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param information
	 *            The new {@link GoalInformation} for this {@link Creature}.
	 * @throws Exception
	 */
	public void addGoal(Creature entity, final PathfinderGoalProvider goalProvider, int priorityOverride) throws Exception;

	/**
	 * Returns a list off goals this {@link Creature} tries to achieve.
	 * 
	 * @param entity
	 *            The {@link Creature} whose {@link Goal}s should be listed.
	 * @return A list of {@link Goal}s the given {@link Creature} tries to achieve.
	 */
	public List<? extends GoalInformation> getGoals(Creature entity) throws Exception;

	/**
	 * Returns a navigation controller for the given creature.
	 * 
	 * @param entity
	 *            The entity which navigation controller should be returned,
	 * @return The navigation controller for this entity.
	 */
	public Navigation getNavigation(Creature entity);

	public void attack(Creature entity, Entity target) throws Exception;
}
