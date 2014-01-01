package de.st_ddt.crazyspawner.craftbukkit.v1_7_R1.properties.ai.action.builder;

import net.minecraft.server.v1_7_R1.EntityCreature;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomStroll;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.craftbukkit.v1_7_R1.util.ai.PathfinderGoalGoalLinkerImpl;
import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl.BasicGoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;

public class RandomStrollGoalBuilder extends BasicGoalBuilder
{

	private final double speed;

	public RandomStrollGoalBuilder()
	{
		super();
		this.speed = 1;
	}

	public RandomStrollGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		this.speed = Math.max(config.getDouble("speed", 1), 0);
	}

	@Override
	public String getType()
	{
		return "RandomStroll";
	}

	@Override
	public Goal build(final Creature entity)
	{
		return new PathfinderGoalGoalLinkerImpl(new PathfinderGoalRandomStroll((EntityCreature) ActionHelper.getHandle(entity), speed));
	}
}
