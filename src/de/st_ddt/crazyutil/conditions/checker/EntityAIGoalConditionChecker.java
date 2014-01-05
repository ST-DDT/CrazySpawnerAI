package de.st_ddt.crazyutil.conditions.checker;

import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.Goal;

public interface EntityAIGoalConditionChecker extends ConditionChecker
{

	public Goal getGoal();

	public class SimpleEntityAIGoalConditionChecker implements EntityAIGoalConditionChecker
	{

		private final Goal goal;

		public SimpleEntityAIGoalConditionChecker(final Goal goal)
		{
			super();
			this.goal = goal;
		}

		@Override
		public Goal getGoal()
		{
			return goal;
		}
	}
}
