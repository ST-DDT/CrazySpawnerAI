package de.st_ddt.crazyutil.conditions.entity.ai.goal;

import org.bukkit.configuration.ConfigurationSection;

import de.st_ddt.crazyutil.conditions.checker.ConditionChecker;
import de.st_ddt.crazyutil.conditions.checker.EntityAndEntityAIGoalConditionChecker;
import de.st_ddt.crazyutil.conditions.checker.EntityAndEntityBoundEntityAIGoalConditionChecker;

public abstract class BasicEntityAndEntityBoundEntityAIGoalCondition extends BasicEntityAndEntityAIGoalCondition
{

	public BasicEntityAndEntityBoundEntityAIGoalCondition()
	{
		super();
	}

	public BasicEntityAndEntityBoundEntityAIGoalCondition(final ConfigurationSection config)
	{
		super(config);
	}

	@Override
	public abstract String getType();

	@Override
	public boolean isApplicable(final Class<? extends ConditionChecker> clazz)
	{
		return EntityAndEntityBoundEntityAIGoalConditionChecker.class.isAssignableFrom(clazz);
	}

	@Override
	public final boolean check(final EntityAndEntityAIGoalConditionChecker checker)
	{
		return check((EntityAndEntityBoundEntityAIGoalConditionChecker) checker);
	}

	public abstract boolean check(final EntityAndEntityBoundEntityAIGoalConditionChecker checker);
}
