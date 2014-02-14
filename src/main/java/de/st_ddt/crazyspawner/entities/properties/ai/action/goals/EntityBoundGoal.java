package de.st_ddt.crazyspawner.entities.properties.ai.action.goals;

import org.bukkit.entity.Creature;

public interface EntityBoundGoal extends Goal
{

	public Creature getEntity();
}
