package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.EntityBoundGoal;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;
import de.st_ddt.crazyutil.comparators.EntityDistanceComparator;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.ConditionHelper;
import de.st_ddt.crazyutil.conditions.ExtendedConditionHelper;

public class WatchClosestGoal extends BasicGoal
{

	protected final static long SEARCHINTERVAL = 3000;
	protected final Condition watchedCondition;
	protected final double yawRotationSpeed;
	protected final double pitchRotationSpeed;
	protected Entity watched;
	protected long lastSearched;

	public WatchClosestGoal(final Creature entity, final Condition watchedCondition, final double yawRotationSpeed, final double pitchRotationSpeed)
	{
		super(entity, MUTEX_FLAG_LOOK);
		this.watchedCondition = ExtendedConditionHelper.simpleSecure(watchedCondition, Entity.class, EntityBoundGoal.class);
		this.yawRotationSpeed = yawRotationSpeed;
		this.pitchRotationSpeed = pitchRotationSpeed;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		if (matchesCondition(watched))
			return true;
		else
		{
			watched = null;
			final long now = System.currentTimeMillis();
			if (now > lastSearched + SEARCHINTERVAL)
			{
				lastSearched = now;
				watched = searchWatchable();
				return watched != null;
			}
			else
				return false;
		}
	}

	@Override
	public void start()
	{
		ActionHelper.getNavigation(entity).lookAt(watched, yawRotationSpeed, pitchRotationSpeed);
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
		return target != null && !target.equals(entity) && target.isValid() && ConditionHelper.simpleCheck(watchedCondition, target, this);
	}

	@Override
	public String toString()
	{
		return "CSAI_" + getClass().getSimpleName() + "{Entity: " + entity.getUniqueId().toString() + "; Condition: " + watchedCondition.toString() + "}";
	}
}
