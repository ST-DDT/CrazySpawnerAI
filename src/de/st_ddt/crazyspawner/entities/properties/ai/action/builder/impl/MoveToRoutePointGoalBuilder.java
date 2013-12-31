package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.ai.CrazySpawnerAI;
import de.st_ddt.crazyspawner.ai.routes.RouteMap;
import de.st_ddt.crazyspawner.ai.routes.RoutePoint;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.MoveToRoutePointGoal;
import de.st_ddt.crazyutil.conditions.Condition;

public class MoveToRoutePointGoalBuilder extends BasicGoalBuilder
{

	protected final RoutePoint target;
	protected final double speed;

	public MoveToRoutePointGoalBuilder(final RoutePoint target, final double speed)
	{
		super();
		this.target = target;
		this.speed = speed;
	}

	public MoveToRoutePointGoalBuilder(final Condition startCondition, final Condition continueCondition, final RoutePoint target, final double speed)
	{
		super();
		this.target = target;
		this.speed = speed;
	}

	public MoveToRoutePointGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		final int targetId = config.getInt("targetPathPointId", -1);
		final String worldName = config.getString("world", null);
		if (worldName == null)
			throw new IllegalArgumentException("The target's world's name cannot be Null!");
		final World world = Bukkit.getWorld(worldName);
		if (world == null)
			throw new IllegalArgumentException("The target's world " + worldName + " was not found!");
		final RouteMap map = CrazySpawnerAI.getPlugin().getRouteMap(world);
		this.target = map.getPoint(targetId);
		this.speed = config.getDouble("speed", 1);
	}

	@Override
	public String getType()
	{
		return "MoveToRoutePoint";
	}

	@Override
	public MoveToRoutePointGoal build(final Creature entity)
	{
		return new MoveToRoutePointGoal(entity, target, speed);
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		super.save(config, path);
		config.set(path + "targetId", target.getId());
		config.set(path + "world", target.getLocation().getWorld());
		config.set(path + "speed", speed);
	}
}
