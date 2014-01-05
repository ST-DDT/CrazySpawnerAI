package de.st_ddt.crazyutil.conditions.checker;

import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public interface EntityAndEntityAIGoalConditionChecker extends EntityConditionChecker, EntityAIGoalConditionChecker
{

	public class SimpleEntityAndEntityAIGoalConditionChecker implements EntityAndEntityAIGoalConditionChecker
	{

		private final Entity entity;
		private final Goal goal;

		public SimpleEntityAndEntityAIGoalConditionChecker(final Entity entity, final Goal goal)
		{
			super();
			this.entity = entity;
			this.goal = goal;
		}

		@Override
		public Entity getEntity()
		{
			return entity;
		}

		@Override
		public Goal getGoal()
		{
			return goal;
		}
	}
}
