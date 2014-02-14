package de.st_ddt.crazyutil.conditions.entity.ai.goal;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.EntityBoundGoal;
import de.st_ddt.crazyutil.conditions.BasicCondition;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.ExtendedConditionHelper;

public class Condition_Entity_AI_Goal_Distance extends BasicCondition
{

	protected final int entityIndex;
	protected final int goalIndex;
	protected final double distance;

	public Condition_Entity_AI_Goal_Distance(final int entityIndex, final int goalIndex, final double distance)
	{
		super();
		this.entityIndex = entityIndex;
		this.goalIndex = goalIndex;
		this.distance = distance;
	}

	public Condition_Entity_AI_Goal_Distance(final ConfigurationSection config, final Map<String, Integer> parameterIndexes)
	{
		super(config, parameterIndexes);
		this.entityIndex = parameterIndexes.get(config.getString("checkedEntity", "Entity"));
		this.goalIndex = parameterIndexes.get(config.getString("checkedGoal", "Goal"));
		this.distance = config.getDouble("distance", 8);
	}

	@Override
	public Condition secure(final Map<Integer, ? extends Collection<Class<?>>> classes)
	{
		final Condition condition = ExtendedConditionHelper.secure(this, entityIndex, Entity.class, classes);
		return ExtendedConditionHelper.secure(condition, goalIndex, EntityBoundGoal.class, classes);
	}

	@Override
	public boolean check(final Map<Integer, ? extends Object> parameters)
	{
		final EntityBoundGoal goal = (EntityBoundGoal) parameters.get(goalIndex);
		final Location goalOwnerLocation = goal.getEntity().getEyeLocation();
		final Entity entity = (Entity) parameters.get(entityIndex);
		final Location entityLocation = entity.getLocation();
		if (goalOwnerLocation.getWorld() == entityLocation.getWorld())
			return goalOwnerLocation.distance(entityLocation) <= distance;
		else
			return false;
	}

	@Override
	public void save(final ConfigurationSection config, final String path, final Map<Integer, String> parameterNames)
	{
		super.save(config, path, parameterNames);
		config.set(path + "checkedEntity", parameterNames.get(entityIndex));
		config.set(path + "checkedGoal", parameterNames.get(goalIndex));
		config.set(path + "distance", distance);
	}
}
