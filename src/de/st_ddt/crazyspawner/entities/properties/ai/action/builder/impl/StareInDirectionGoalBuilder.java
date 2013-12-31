package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;
import org.bukkit.util.Vector;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.StareInDirectionGoal;

public class StareInDirectionGoalBuilder extends BasicGoalBuilder
{

	protected final Vector direction;

	public StareInDirectionGoalBuilder()
	{
		super();
		this.direction = new Vector();
	}

	public StareInDirectionGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		this.direction = new Vector(config.getDouble("x", 0), config.getDouble("y", 0), config.getDouble("z", 0));
	}

	@Override
	public String getType()
	{
		return "StareInDirection";
	}

	@Override
	public StareInDirectionGoal build(final Creature entity)
	{
		return new StareInDirectionGoal(entity, direction);
	}
}
