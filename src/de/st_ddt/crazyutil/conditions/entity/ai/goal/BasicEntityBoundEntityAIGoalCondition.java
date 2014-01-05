package de.st_ddt.crazyutil.conditions.entity.ai.goal;

import org.bukkit.configuration.ConfigurationSection;

import de.st_ddt.crazyutil.conditions.checker.ConditionChecker;
import de.st_ddt.crazyutil.conditions.checker.EntityAIGoalConditionChecker;
import de.st_ddt.crazyutil.conditions.checker.EntityBoundEntityAIGoalConditionChecker;

public abstract class BasicEntityBoundEntityAIGoalCondition extends BasicEntityAIGoalCondition
{

	public BasicEntityBoundEntityAIGoalCondition()
	{
		super();
	}

	public BasicEntityBoundEntityAIGoalCondition(final ConfigurationSection config)
	{
		super(config);
	}

	@Override
	public abstract String getType();

	@Override
	public boolean isApplicable(final Class<? extends ConditionChecker> clazz)
	{
		return EntityBoundEntityAIGoalConditionChecker.class.isAssignableFrom(clazz);
	}

	@Override
	public final boolean check(final EntityAIGoalConditionChecker checker)
	{
		return check((EntityBoundEntityAIGoalConditionChecker) checker);
	}

	public abstract boolean check(final EntityBoundEntityAIGoalConditionChecker checker);
}
