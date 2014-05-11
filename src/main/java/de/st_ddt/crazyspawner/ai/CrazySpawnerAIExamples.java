package de.st_ddt.crazyspawner.ai;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;

import de.st_ddt.crazyspawner.CrazySpawner;
import de.st_ddt.crazyspawner.entities.properties.ai.CreatureActionSetProperty;
import de.st_ddt.crazyspawner.entities.properties.ai.action.GoalEntry;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.MoveToClosestGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.RandomLookaroundGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.WatchClosestGoalBuilder;
import de.st_ddt.crazyspawner.entities.spawners.CustomizedParentedSpawner;
import de.st_ddt.crazyspawner.entities.spawners.NamedParentedSpawner;

final class CrazySpawnerAIExamples
{

	public static void saveExampleEntities(final String previousVersion)
	{
		final CrazySpawner plugin = CrazySpawner.getPlugin();
		// - AI Zombie
		{
			final CustomizedParentedSpawner customizedSpawner = new CustomizedParentedSpawner(EntityType.ZOMBIE);
			final List<GoalEntry> goalEntries = new ArrayList<>();
			goalEntries.add(new GoalEntry(new WatchClosestGoalBuilder(5, EntityType.SKELETON), 1));
			goalEntries.add(new GoalEntry(new MoveToClosestGoalBuilder(1, 10, EntityType.SKELETON), 2));
			goalEntries.add(new GoalEntry(new WatchClosestGoalBuilder(15, EntityType.PLAYER), 2));
			goalEntries.add(new GoalEntry(new RandomLookaroundGoalBuilder(), 3));
			customizedSpawner.addEntityProperty(new CreatureActionSetProperty(goalEntries));
			plugin.addCustomEntity(new NamedParentedSpawner(customizedSpawner, "AI_Zombie"));
		}
		plugin.saveCustomEntities();
	}
}
