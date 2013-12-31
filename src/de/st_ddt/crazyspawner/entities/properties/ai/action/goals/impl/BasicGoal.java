package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public abstract class BasicGoal implements Goal
{

	protected final Creature entity;

	public BasicGoal(final Creature entity)
	{
		super();
		this.entity = entity;
	}

	public final Creature getEntity()
	{
		return entity;
	}

	@Override
	public abstract boolean shouldExecute(boolean started);

	@Override
	public boolean isInterruptible()
	{
		return true;
	}

	@Override
	public abstract void start();

	@Override
	public void update()
	{
	}

	@Override
	public void reset()
	{
	}

	@Override
	public String toString()
	{
		return "CSAI_" + getClass().getSimpleName() + "{Entity: " + entity.getUniqueId().toString() + "}";
	}
}
