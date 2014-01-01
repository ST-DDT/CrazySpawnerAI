package de.st_ddt.crazyspawner.craftbukkit.v1_6_R3.properties.ai.action.builder;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.PathfinderGoalRandomLookaround;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.craftbukkit.v1_6_R3.util.ai.PathfinderGoalGoalLinkerImpl;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.BasicGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;

public class RandomLookAroundGoalBuilder extends BasicGoalBuilder
{

	public RandomLookAroundGoalBuilder()
	{
		super();
	}

	public RandomLookAroundGoalBuilder(final ConfigurationSection config)
	{
		super(config);
	}

	@Override
	public String getType()
	{
		return "RandomStroll";
	}

	@Override
	public Goal build(final Creature entity)
	{
		return new PathfinderGoalGoalLinkerImpl(new PathfinderGoalRandomLookaround((EntityCreature) ActionHelper.getHandle(entity)));
	}
}
