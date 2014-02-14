package de.st_ddt.crazyspawner.entities.util.ai;

import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.GoalBuilder;

public final class ActionHelper
{

	private ActionHelper()
	{
	}

	private static ActionHelperInterface actionHelper;

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