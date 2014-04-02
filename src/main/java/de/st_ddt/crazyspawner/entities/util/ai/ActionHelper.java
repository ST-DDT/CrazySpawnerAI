package de.st_ddt.crazyspawner.entities.util.ai;

import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.ai.CrazySpawnerAI;
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

	public static Object getHandle(final Entity entity)
	{
		return actionHelper.getHandle(entity);
	}

	public static Object getHandle(final Creature entity)
	{
		return actionHelper.getHandle(entity);
	}

	public static Creature getEntity(final Object entity)
	{
		return actionHelper.getEntity(entity);
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
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Appends a goal to the {@link Creature}'s list of goals.
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param goal
	 *            The new {@link Goal} for this {@link Creature}.
	 */
	public static void addGoal(final Creature entity, final GoalBuilder goal, final int priority)
	{
		try
		{
			actionHelper.addGoal(entity, goal, priority);
		}
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Appends a goal to the {@link Creature}'s list of goals.<br>
	 * DO NOT ADD OTHER CREATURE'S GOALS!
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param information
	 *            The new {@link GoalInformation} for this {@link Creature}.
	 */
	public static void addGoal(final Creature creature, final GoalInformation goalInformation)
	{
		try
		{
			actionHelper.addGoal(creature, goalInformation);
		}
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Appends a goal to the {@link Creature}'s list of goals.<br>
	 * DO NOT ADD OTHER CREATURE'S GOALS!
	 * 
	 * @param entity
	 *            The {@link Creature} that should get a new goal.
	 * @param information
	 *            The new {@link GoalInformation} for this {@link Creature}.
	 */
	public static void addGoal(final Creature creature, final PathfinderGoalProvider goalProvider, final int priorityOverride)
	{
		try
		{
			actionHelper.addGoal(creature, goalProvider, priorityOverride);
		}
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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

	public static void attack(final Creature entity, final Entity target)
	{
		try
		{
			actionHelper.attack(entity, target);
		}
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
