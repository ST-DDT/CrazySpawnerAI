package de.st_ddt.crazyspawner.entities.properties.ai.action.builder;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.util.ai.PathfinderGoalWrapper;

public interface PathfinderGoalGoalBuilder extends GoalBuilder
{

	@Override
	public String getType();

	@Override
	public PathfinderGoalWrapper build(Creature entity);

	@Override
	public void save(ConfigurationSection config, String path);
}
