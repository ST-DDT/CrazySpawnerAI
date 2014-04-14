package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import java.util.Random;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.EntityBoundGoal;

public abstract class BasicGoal implements EntityBoundGoal
{

	protected final static Random RANDOM = new Random();
	protected final Creature entity;
	private final int mutexBitFlags;

	public BasicGoal(final Creature entity, final int mutexBitFlags)
	{
		super();
		this.entity = entity;
		this.mutexBitFlags = mutexBitFlags;
	}

	@Override
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
	public int getMutexBitFlags()
	{
		return mutexBitFlags;
	}

	@Override
	public String toString()
	{
		return "CSAI_" + getClass().getSimpleName() + "{Entity: " + entity.getUniqueId().toString() + "}";
	}
}
