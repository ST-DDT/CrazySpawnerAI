package de.st_ddt.crazyutil.conditions.checker;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.EntityBoundGoal;

public interface EntityBoundEntityAIGoalConditionChecker extends EntityAIGoalConditionChecker
{

	@Override
	public EntityBoundGoal getGoal();

	public Creature getGoalOwner();

	public class SimpleEntityBoundGoalConditionChecker implements EntityBoundEntityAIGoalConditionChecker
	{

		private final EntityBoundGoal goal;

		public SimpleEntityBoundGoalConditionChecker(final EntityBoundGoal goal)
		{
			super();
			this.goal = goal;
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
