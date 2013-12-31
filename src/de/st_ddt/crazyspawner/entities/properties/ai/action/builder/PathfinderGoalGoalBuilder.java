package de.st_ddt.crazyspawner.entities.properties.ai.action.builder;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.util.ai.PathfinderGoalGoalLinker;

public interface PathfinderGoalGoalBuilder extends GoalBuilder
{

	@Override
	public String getType();

	@Override
	public PathfinderGoalGoalLinker build(Creature entity);

	@Override
	public void save(ConfigurationSection config, String path);
}
