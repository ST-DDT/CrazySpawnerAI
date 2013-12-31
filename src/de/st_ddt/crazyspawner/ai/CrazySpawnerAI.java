package de.st_ddt.crazyspawner.ai;

import org.bukkit.World;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyplugin.CrazyPlugin;
import de.st_ddt.crazyspawner.ai.routes.Path;
import de.st_ddt.crazyspawner.ai.routes.RouteMap;
import de.st_ddt.crazyspawner.ai.routes.RoutePoint;
import de.st_ddt.crazyspawner.entities.properties.EntityPropertyHelper;
import de.st_ddt.crazyspawner.entities.properties.ai.CreatureActionSetProperty;
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
	public void onLoad()
	{
		plugin = this;
		CompatibilityLoader.loadCompatibilityProvider(this, "de.st_ddt.crazyspawner.", ".ai");
		EntityPropertyHelper.registerEntityProperty(CreatureActionSetProperty.class);
		ActionHelper.initialize();
		super.onLoad();
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
