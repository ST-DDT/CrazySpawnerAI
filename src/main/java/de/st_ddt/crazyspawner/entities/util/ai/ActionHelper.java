package de.st_ddt.crazyspawner.entities.util.ai;

import java.util.List;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.ai.CrazySpawnerAI;
import de.st_ddt.crazyspawner.entities.properties.ai.action.GoalEntry;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.GoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public final class ActionHelper
{

	private ActionHelper()
	{
	}

	private static ActionHelperInterface actionHelper;

	/**
	 * This method initializes this ActionHelper with a matching implementation of the required interface.<br>
	 * This method will be called by {@link CrazySpawnerAI#initialize()}.
	 * 
	 * @return
	 */
	public static boolean initialize()
	{
		for (final Class<? extends ActionHelperInterface> clazz : ActionHelperInterface.ACTIONHELPERCLASSES)
			try
			{
				actionHelper = clazz.newInstance();
				return true;
			}
			catch (final Throwable t)
			{}
		return false;
	}

	/**
	 * Removes all goals from this entity.
	 * 
	 * @param entity
	 *            The entity which goals should be removed.
	 */
	public static void clearGoals(final Creature entity)
	{
		try
		{
			actionHelper.clearGoals(entity);
		}
		catch (final Throwable t)
		{
			throw new IllegalStateException(t);
		}
	}

	/**
	 * Adds a {@link Goal} to the {@link Creature}'s list of goals.
	 * 
	 * @param creature
	 *            The {@link Creature} that should get a new goal.
	 * @param goalEntry
	 *            The {@link Goal} to be added to this {@link Creature}.
	 */
	public static void addGoal(final Creature creature, final GoalEntry goalEntry)
	{
		addGoal(creature, goalEntry.getBuilder(), goalEntry.getPriority());
	}

	/**
	 * Adds a {@link Goal} to the {@link Creature}'s list of goals.
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param goal
	 *            The new {@link Goal} for this {@link Creature}.
	 */
	public static void addGoal(final Creature entity, final GoalBuilder goalBuilder, final int priority)
	{
		addGoal(entity, goalBuilder.build(entity), priority);
	}

	/**
	 * Adds a {@link Goal} to the {@link Creature}'s list of goals.<br>
	 * <b>DO NOT ADD OTHER CREATURE'S GOALS!</b>
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param information
	 *            The new {@link GoalInformation} for this {@link Creature}.
	 */
	public static void addGoal(final Creature creature, final GoalInformation goalInformation)
	{
		addGoal(creature, goalInformation.getGoal(), goalInformation.getPriority());
	}

	/**
	 * Adds a {@link Goal} to the {@link Creature}'s list of goals.<br>
	 * <b>DO NOT ADD OTHER CREATURE'S GOALS!</b>
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param information
	 *            The new {@link GoalInformation} for this {@link Creature}.
	 * @param priorityOverride
	 *            The priority for this goal.
	 */
	public static void addGoal(final Creature creature, final GoalInformation goalInformation, final int priorityOverride)
	{
		addGoal(creature, goalInformation.getGoal(), priorityOverride);
	}

	/**
	 * Adds a {@link Goal} to the {@link Creature}'s list of goals.<br>
	 * <b>DO NOT ADD OTHER CREATURE'S GOALS!</b>
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param goal
	 *            The new {@link Goal} for this {@link Creature}.
	 * @param priority
	 *            The priotity for the goal.
	 */
	public static void addGoal(final Creature creature, final Goal goal, final int priority)
	{
		try
		{
			actionHelper.addGoal(creature, unwrap(goal), priority);
		}
		catch (final Throwable t)
		{
			throw new IllegalStateException(t);
		}
	}

	/**
	 * Returns a list off goals this {@link Creature} tries to achieve.
	 * 
	 * @param entity
	 *            The {@link Creature} whose {@link Goal}s should be listed.
	 * @return A list of {@link Goal}s the given {@link Creature} tries to achieve.
	 */
	public static List<? extends GoalInformation> getGoals(final Creature entity)
	{
		try
		{
			return actionHelper.getGoals(entity);
		}
		catch (final Throwable t)
		{
			throw new IllegalStateException(t);
		}
	}

	/**
	 * Removes any not necessary wrappings from this goal.
	 * 
	 * @param goal
	 *            The goal that should be unwrapped.
	 * @return The unwrapped goal
	 */
	public static Goal unwrap(final Goal goal)
	{
		if (goal instanceof CrazySpawnerAIGoalWrapper)
			return unwrap(((CrazySpawnerAIGoalWrapper) goal).getGoal());
		else if (goal instanceof PathfinderGoalWrapper)
		{
			final Object wrappedGoal = ((PathfinderGoalWrapper) goal).getPathfinderGoal();
			if (wrappedGoal instanceof Goal)
				return unwrap((Goal) wrappedGoal);
			else
				return goal;
		}
		else
			return goal;
	}

	/**
	 * Returns a navigation controller for the given creature.
	 * 
	 * @param entity
	 *            The entity which navigation controller should be returned,
	 * @return The navigation controller for this entity.
	 */
	public static Navigation getNavigation(final Creature entity)
	{
		return actionHelper.getNavigation(entity);
	}
}
