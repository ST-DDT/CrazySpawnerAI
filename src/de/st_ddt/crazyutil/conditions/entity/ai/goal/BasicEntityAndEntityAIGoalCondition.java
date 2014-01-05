package de.st_ddt.crazyutil.conditions.entity.ai.goal;

import org.bukkit.configuration.ConfigurationSection;

import de.st_ddt.crazyutil.conditions.checker.ConditionChecker;
import de.st_ddt.crazyutil.conditions.checker.EntityAIGoalConditionChecker;
import de.st_ddt.crazyutil.conditions.checker.EntityAndEntityAIGoalConditionChecker;

public abstract class BasicEntityAndEntityAIGoalCondition extends BasicEntityAIGoalCondition
{

	public BasicEntityAndEntityAIGoalCondition()
	{
		super();
	}

	public BasicEntityAndEntityAIGoalCondition(final ConfigurationSection config)
	{
		super(config);
	}

	@Override
	public abstract String getType();

	@Override
	public boolean isApplicable(final Class<? extends ConditionChecker> clazz)
	{
		return EntityAIGoalConditionChecker.class.isAssignableFrom(clazz);
	}

	@Override
	public final boolean check(final EntityAIGoalConditionChecker checker)
	{
		return check((EntityAndEntityAIGoalConditionChecker) checker);
	}

	public abstract boolean check(final EntityAndEntityAIGoalConditionChecker checker);
}
