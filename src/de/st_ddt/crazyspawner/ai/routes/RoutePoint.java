package de.st_ddt.crazyspawner.ai.routes;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import de.st_ddt.crazyutil.ConfigurationSaveable;
import de.st_ddt.crazyutil.ObjectSaveLoadHelper;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.ConditionHelper;
import de.st_ddt.crazyutil.conditions.Condition_TRUE;
import de.st_ddt.crazyutil.conditions.ExtendedConditionHelper;
import de.st_ddt.crazyutil.paramitrisable.LocationParamitrisable;

public class RoutePoint implements ConfigurationSaveable
{

	private static int newID = 1;
	private final int id;
	private final Location location;
	private String name;
	private final Condition accessCondition;
	private final Set<RouteConnection> connectedPaths = new HashSet<>();
	private final double size;

	public RoutePoint(final Location location)
	{
		this(location, (String) null);
	}

	public RoutePoint(final Location location, final String name)
	{
		this(location, name, new Condition_TRUE(), 0);
	}

	public RoutePoint(final Location location, final Condition accessCondition)
	{
		this(location, null, accessCondition, 0);
	}

	public RoutePoint(final Location location, final String name, final Condition accessCondition, final double size)
	{
		super();
		this.id = (newID++);
		this.location = LocationParamitrisable.simplyfyLocation(location.clone(), 2, 0);
		this.location.setYaw(0);
		this.location.setPitch(0);
		this.name = name;
		this.accessCondition = ExtendedConditionHelper.simpleSecure(accessCondition, LivingEntity.class);
		this.size = size;
	}

	public RoutePoint(final World world, final ConfigurationSection config)
	{
		super();
		this.id = config.getInt("id", newID);
		this.location = LocationParamitrisable.simplyfyLocation(ObjectSaveLoadHelper.loadLocation(config, world), 2, 0);
		this.location.setYaw(0);
		this.location.setPitch(0);
		this.name = config.getString("name", null);
		Condition condition = null;
		try
		{
			condition = ConditionHelper.simpleLoad(config.getConfigurationSection("accessCondition"), "Entity");
			condition = ExtendedConditionHelper.simpleSecure(condition, LivingEntity.class);
		}
		catch (final Exception e)
		{
			System.err.println("RoutePoint " + id + "'s access condition was corrupted/invalid and has been removed!");
			e.printStackTrace();
			condition = new Condition_TRUE();
		}
		this.accessCondition = condition;
		this.size = config.getDouble("size", 1);
	}

	public int getId()
	{
		return id;
	}

	public Location getLocation()
	{
		return location;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public boolean hasAccess(final LivingEntity entity)
	{
		return ConditionHelper.simpleCheck(accessCondition, entity);
	}

	public Condition getAccessCondition()
	{
		return accessCondition;
	}

	public Set<RouteConnection> getConnections()
	{
		return connectedPaths;
	}

	public boolean isWithinRange(final Entity entity)
	{
		return isWithinRange(entity.getLocation());
	}

	public boolean isWithinRange(final Location location)
	{
		return this.location.distance(location) <= size;
	}

	public double getSize()
	{
		return size;
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		config.set(path + "id", id);
		ObjectSaveLoadHelper.saveLocation(config, path + "location.", location, false, false);
		config.set(path + "name", name);
		ConditionHelper.simpleSave(accessCondition, config, path + "condition.", "Entity");
		config.set(path + "size", size);
	}

	@Override
	public String toString()
	{
		return "RoutePoint{id: " + id + "; " + (name == null ? "" : "name: " + name + "; ") + "location: " + location.toString() + "}";
	}
}
