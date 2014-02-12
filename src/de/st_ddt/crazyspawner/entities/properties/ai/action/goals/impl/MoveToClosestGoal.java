package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;
import de.st_ddt.crazyutil.comparators.EntityDistanceComparator;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.ConditionHelper;
import de.st_ddt.crazyutil.conditions.ExtendedConditionHelper;

public class MoveToClosestGoal extends BasicGoal
{

	protected final static long SEARCHINTERVAL = 3000;
	protected final Condition moveToCondition;
	protected final double speed;
	protected Entity moveTo;
	protected long lastSearched;

	public MoveToClosestGoal(final Creature entity, final Condition moveToCondition, final double speed)
	{
		super(entity, MUTEX_FLAG_MOVE);
		this.moveToCondition = ExtendedConditionHelper.simpleSecure(moveToCondition, Entity.class, MoveToClosestGoal.class);
		this.speed = speed;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		if (matchesCondition(moveTo))
			return true;
		else
		{
			moveTo = null;
			final long now = System.currentTimeMillis();
			if (now > lastSearched + SEARCHINTERVAL)
			{
				lastSearched = now;
				moveTo = searchWatchable();
				return moveTo != null;
			}
			else
				return false;
		}
	}

	@Override
	public void start()
	{
		ActionHelper.getNavigation(entity).tryMoveTo(moveTo, speed);
	}

	@Override
	public void update()
	{
		start();
	}

	protected Entity searchWatchable()
	{
		final List<Entity> possibilites = new ArrayList<>();
		for (final Entity temp : entity.getWorld().getEntities())
			if (matchesCondition(temp))
				possibilites.add(temp);
		if (possibilites.isEmpty())
			return null;
		Collections.sort(possibilites, new EntityDistanceComparator(entity.getLocation()));
		return possibilites.get(0);
	}

	protected boolean matchesCondition(final Entity target)
	{
		return target != null && !target.equals(entity) && target.isValid() && moveToCondition.check(ConditionHelper.simpleParameters(target, this));
	}

	@Override
	public String toString()
	{
		return "CSAI_" + getClass().getSimpleName() + "{Entity: " + entity.getUniqueId().toString() + "; Condition: " + moveToCondition.toString() + "}";
	}
}
