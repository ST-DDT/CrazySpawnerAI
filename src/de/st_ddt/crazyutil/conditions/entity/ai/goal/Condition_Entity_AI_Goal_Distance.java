package de.st_ddt.crazyutil.conditions.entity.ai.goal;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import de.st_ddt.crazyutil.conditions.checker.EntityAndEntityBoundEntityAIGoalConditionChecker;

public class Condition_Entity_AI_Goal_Distance extends BasicEntityAndEntityBoundEntityAIGoalCondition
{

	protected final double distance;

	public Condition_Entity_AI_Goal_Distance(final double distance)
	{
		super();
		this.distance = distance;
	}

	public Condition_Entity_AI_Goal_Distance(final ConfigurationSection config)
	{
		super(config);
		this.distance = config.getDouble("distance", 8);
	}

	@Override
	public String getType()
	{
		return "ENTITY_AI_GOAL_DISTANCE";
	}

	@Override
	public boolean check(final EntityAndEntityBoundEntityAIGoalConditionChecker checker)
	{
		final Location goalOwnerLocation = checker.getGoalOwner().getLocation();
		final Location entityLocation = checker.getEntity().getLocation();
		if (goalOwnerLocation.getWorld() == entityLocation.getWorld())
			return goalOwnerLocation.distance(entityLocation) <= distance;
		else
			return false;
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		super.save(config, path);
		config.set(path + "distance", distance);
	}
}
