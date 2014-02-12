package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;

import de.st_ddt.crazyutil.comparators.EntityDistanceComparator;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.ConditionHelper;

public class TargetGoal extends BasicGoal
{

	protected final Condition targetCondition;
	protected long lastSearched;
	protected LivingEntity lastMatching;

	public TargetGoal(final Creature entity, final Condition targetCondition)
	{
		super(entity, 0);
		this.targetCondition = targetCondition.secure(ConditionHelper.simpleParameterClasses(LivingEntity.class));
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		LivingEntity target = entity.getTarget();
		if (target == null)
		{
			target = searchTarget(false);
			if (target == null)
				return false;
			else
			{
				lastMatching = target;
				return true;
			}
		}
		else if (matchConditions(target))
		{
			lastMatching = null;
			return false;
		}
		else
		{
			target = searchTarget(false);
			if (target == null)
				return false;
			else
			{
				lastMatching = target;
				return true;
			}
		}
	}

	@Override
	public void start()
	{
		if (lastMatching == null)
			return;
		if (matchConditions(lastMatching))
			entity.setTarget(lastMatching);
		else
			lastMatching = null;
	}

	public LivingEntity searchTarget(final boolean force)
	{
		final long now = System.currentTimeMillis();
		if (!force)
			if (now - lastSearched < 1000)
				return null;
		lastSearched = now;
		final List<LivingEntity> entites = new ArrayList<>(entity.getWorld().getEntitiesByClass(LivingEntity.class));
		Collections.sort(entites, new EntityDistanceComparator(entity.getLocation()));
		for (final LivingEntity entity : entites)
			if (matchConditions(entity))
				return entity;
		return null;
	}

	public boolean matchConditions(final LivingEntity entity)
	{
		return entity.isValid() && targetCondition.check(ConditionHelper.simpleParameters(entity));
	}
}
