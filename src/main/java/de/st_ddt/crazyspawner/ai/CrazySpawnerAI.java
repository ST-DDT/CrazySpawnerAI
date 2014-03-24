package de.st_ddt.crazyspawner.ai;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;

import de.st_ddt.crazyplugin.CrazyPlugin;
import de.st_ddt.crazyspawner.CrazySpawner;
import de.st_ddt.crazyspawner.ai.routes.Path;
import de.st_ddt.crazyspawner.ai.routes.RouteMap;
import de.st_ddt.crazyspawner.ai.routes.RoutePoint;
import de.st_ddt.crazyspawner.entities.CustomizedParentedSpawner;
import de.st_ddt.crazyspawner.entities.NamedParentedSpawner;
import de.st_ddt.crazyspawner.entities.properties.EntityPropertyHelper;
import de.st_ddt.crazyspawner.entities.properties.ai.CreatureActionSetProperty;
import de.st_ddt.crazyspawner.entities.properties.ai.action.GoalEntry;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.MoveToClosestGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.RandomLookaroundGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.WatchClosestGoalBuilder;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;
import de.st_ddt.crazyutil.compatibility.CompatibilityLoader;
import de.st_ddt.crazyutil.source.LocalizedVariable;
import de.st_ddt.crazyutil.source.PermissionVariable;

@LocalizedVariable(variables = { "CRAZYPLUGIN" }, values = { "CRAZYSPAWNERAI" })
@PermissionVariable(variables = { "CRAZYPLUGIN" }, values = { "CRAZYSPAWNERAI" })
public class CrazySpawnerAI extends CrazyPlugin
{

	private static CrazySpawnerAI plugin;

	public static CrazySpawnerAI getPlugin()
	{
		return plugin;
	}

	@Override
	public void initialize()
	{
		super.initialize();
		plugin = this;
		CompatibilityLoader.loadCompatibilityProvider(this, "de.st_ddt.crazyspawner.", ".ai");
		EntityPropertyHelper.registerEntityProperty(CreatureActionSetProperty.class);
		ActionHelper.initialize();
	}

	@Override
	public void enable()
	{
		super.enable();
		// Move to Example Class
		final CustomizedParentedSpawner customizedSpawner = new CustomizedParentedSpawner(EntityType.ZOMBIE);
		final List<GoalEntry> goalEntries = new ArrayList<>();
		goalEntries.add(new GoalEntry(new WatchClosestGoalBuilder(5, EntityType.SKELETON), 1));
		goalEntries.add(new GoalEntry(new MoveToClosestGoalBuilder(1, 10, EntityType.SKELETON), 2));
		goalEntries.add(new GoalEntry(new WatchClosestGoalBuilder(15, EntityType.PLAYER), 2));
		goalEntries.add(new GoalEntry(new RandomLookaroundGoalBuilder(), 3));
		customizedSpawner.addEntityProperty(new CreatureActionSetProperty(goalEntries));
		CrazySpawner.getPlugin().addCustomEntity(new NamedParentedSpawner(customizedSpawner, "AI_Zombie"));
	}

	public RouteMap getRouteMap(final World world)
	{
		return null;
	}

	public Path searchPathTo(final Creature entity, final RoutePoint target)
	{
		final World world = target.getLocation().getWorld();
		if (!world.equals(entity.getWorld()))
			return null;
		final RouteMap map = getRouteMap(world);
		if (map == null)
			return null;
		final RoutePoint start = map.searchRoutePoint(entity.getLocation(), 2);
		if (start == null)
			return null;
		else
			return map.searchPath(entity, start, target);
	}
}
