package de.st_ddt.crazyutil.conditions.checker;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.EntityBoundGoal;

public interface EntityAndEntityBoundEntityAIGoalConditionChecker extends EntityAndEntityAIGoalConditionChecker, EntityBoundEntityAIGoalConditionChecker
{

	public class SimpleEntityAndEntityBoundEntityAIGoalConditionChecker implements EntityAndEntityBoundEntityAIGoalConditionChecker
	{

		private final Entity entity;
		private final EntityBoundGoal goal;

		public SimpleEntityAndEntityBoundEntityAIGoalConditionChecker(final Entity entity, final EntityBoundGoal goal)
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
		public EntityBoundGoal getGoal()
		{
			return goal;
		}

		@Override
		public Creature getGoalOwner()
		{
			return goal.getEntity();
		}
	}
}
