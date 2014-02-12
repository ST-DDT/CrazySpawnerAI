package de.st_ddt.crazyspawner.entities.properties.ai.action.builder;

import java.util.Map;
import java.util.TreeMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyutil.ConfigurationSaveable;

public interface GoalBuilder extends ConfigurationSaveable
{

	public final static Map<String, Class<? extends GoalBuilder>> GOALBUILDERCLASSES = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	/**
	 * @return The type of this goal builder, required for saving.
	 */
	public String getType();

	public Goal build(Creature entity);

	@Override
	public void save(ConfigurationSection config, String path);
}
