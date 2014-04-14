package de.st_ddt.crazyspawner.craftbukkit.v1_6_R3.util.ai;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityInsentient;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalSelector;

import org.bukkit.craftbukkit.v1_6_R3.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelperInterface;
import de.st_ddt.crazyspawner.entities.util.ai.GoalInformation;

public class ActionHelperImpl implements ActionHelperInterface
{

	/**
	 * The field belonging to {@link EntityInsentient#goalSelector). <br>
	 * Class: PathfinderGoalSelector<br> Represents: The {@link EntityInsentient}'s goals
	 */
	private final static Field goalSelector;
	/**
	 * The field belonging to {@link PathfinderGoalSelector#a}.<br>
	 * Class: List<? extends {@link PathfinderGoalSelectorItem}<br>
	 */
	private final static Field pathfinderGoalSelectorItemList;
	/**
	 * The field belonging to {@link PathfinderGoalSelector#b}.<br>
	 * Class: List<? extends {@link PathfinderGoalSelectorItem}
	 */
	private final static Field currentPathfinderGoalSelectorItemList;
	/**
	 * The field belonging to {@link PathfinderGoalSelectorItem#a}.<br>
	 * Class: {@link PathfinderGoal}
	 */
	private final static Field pathfinderGoal;
	/**
	 * The field belonging to {@link PathfinderGoalSelectorItem#b}.<br>
	 * Class: int
	 */
	private final static Field pathfinderGoalPriority;
	static
	{
		Field _goalSelector = null;
		Field _pathfinderGoalSelectorItemList = null;
		Field _currentPathfinderGoalSelectorItemList = null;
		Field _pathfinderGoal = null;
		Field _pathfinderGoalPriority = null;
		try
		{
			final Class<?> mcEntityClass = EntityInsentient.class;
			_goalSelector = mcEntityClass.getDeclaredField("goalSelector");
			_goalSelector.setAccessible(true);
			final Class<?> mcGoalSelectorClass = PathfinderGoalSelector.class;
			_pathfinderGoalSelectorItemList = mcGoalSelectorClass.getDeclaredField("a");
			_pathfinderGoalSelectorItemList.setAccessible(true);
			_currentPathfinderGoalSelectorItemList = mcGoalSelectorClass.getDeclaredField("b");
			_currentPathfinderGoalSelectorItemList.setAccessible(true);
			final Class<?> mcGoalSelectorItemClass = Class.forName("net.minecraft.server.v1_6_R3.PathfinderGoalSelectorItem");
			_pathfinderGoal = mcGoalSelectorItemClass.getDeclaredField("a");
			_pathfinderGoal.setAccessible(true);
			_pathfinderGoalPriority = mcGoalSelectorItemClass.getDeclaredField("b");
			_pathfinderGoalPriority.setAccessible(true);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			goalSelector = _goalSelector;
			pathfinderGoalSelectorItemList = _pathfinderGoalSelectorItemList;
			currentPathfinderGoalSelectorItemList = _currentPathfinderGoalSelectorItemList;
			pathfinderGoal = _pathfinderGoal;
			pathfinderGoalPriority = _pathfinderGoalPriority;
		}
	}

	public ActionHelperImpl()
	{
		super();
		NavigationImpl.ACTIONHELPER = this;
	}

	@Override
	public net.minecraft.server.v1_6_R3.Entity getHandle(final Entity entity)
	{
		return ((CraftEntity) entity).getHandle();
	}

	@Override
	public EntityCreature getHandle(final Creature entity)
	{
		return ((CraftCreature) entity).getHandle();
	}

	@Override
	public Creature getEntity(final Object entity)
	{
		final EntityCreature creature = (EntityCreature) entity;
		return (Creature) creature.getBukkitEntity();
	}

	protected PathfinderGoalSelector getGoalSelector(final EntityCreature creature) throws Exception
	{
		return (PathfinderGoalSelector) goalSelector.get(creature);
	}

	protected List<?> getGoalSelectorItemsList(final EntityCreature creature) throws Exception
	{
		return (List<?>) pathfinderGoalSelectorItemList.get(getGoalSelector(creature));
	}

	protected List<?> getTemporarySelectorItemsList(final EntityCreature creature) throws Exception
	{
		return (List<?>) currentPathfinderGoalSelectorItemList.get(getGoalSelector(creature));
	}

	protected Map<PathfinderGoal, Integer> getGoals(final EntityCreature creature) throws Exception
	{
		final List<?> goals = getGoalSelectorItemsList(creature);
		final Map<PathfinderGoal, Integer> res = new LinkedHashMap<>(goals.size());
		for (final Object goal : goals)
			res.put((PathfinderGoal) pathfinderGoal.get(goal), (int) pathfinderGoalPriority.get(goal));
		return res;
	}

	protected void addGoal(final EntityCreature creature, final PathfinderGoal pathfinderGoal, final int priority) throws Exception
	{
		getGoalSelector(creature).a(priority, pathfinderGoal);
	}

	protected void addTemporaryGoal(final EntityCreature creature, final PathfinderGoal pathfinderGoal, final int priority) throws Exception
	{
		getGoalSelector(creature).a(priority, pathfinderGoal);
	}

	@Override
	public void addGoal(final Creature creature, final Goal goal, final int priority) throws Exception
	{
		addGoal(getHandle(creature), wrap(goal), priority);
	}

	public PathfinderGoal wrap(final Goal goal)
	{
		if (goal instanceof PathfinderGoal)
			return (PathfinderGoal) goal;
		else
			return new CrazySpawnerAIGoalWrapperImpl(goal);
	}

	@Override
	public List<GoalInformation> getGoals(final Creature entity) throws Exception
	{
		final Map<PathfinderGoal, Integer> goals = getGoals(getHandle(entity));
		final List<GoalInformation> res = new ArrayList<>(goals.size());
		for (final Entry<PathfinderGoal, Integer> entry : goals.entrySet())
			res.add(new GoalInformation(wrap(entry.getKey()), entry.getValue()));
		return res;
	}

	public Goal wrap(final PathfinderGoal goal)
	{
		if (goal instanceof Goal)
			return (Goal) goal;
		else
			return new PathfinderGoalWrapperImpl(goal);
	}

	@Override
	public void clearGoals(final Creature entity) throws Exception
	{
		final EntityCreature creature = getHandle(entity);
		getGoalSelectorItemsList(creature).clear();
		getTemporarySelectorItemsList(creature).clear();
	}

	@Override
	public NavigationImpl getNavigation(final Creature entity)
	{
		return new NavigationImpl(getHandle(entity));
	}
}
