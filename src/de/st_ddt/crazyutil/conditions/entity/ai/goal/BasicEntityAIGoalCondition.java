package de.st_ddt.crazyutil.conditions.entity.ai.goal;

import org.bukkit.configuration.ConfigurationSection;

import de.st_ddt.crazyutil.conditions.BasicCondition;
import de.st_ddt.crazyutil.conditions.checker.ConditionChecker;
import de.st_ddt.crazyutil.conditions.checker.EntityAIGoalConditionChecker;

public abstract class BasicEntityAIGoalCondition extends BasicCondition
{

	public BasicEntityAIGoalCondition()
	{
		super();
	}

	public BasicEntityAIGoalCondition(final ConfigurationSection config)
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
	public final boolean check(final ConditionChecker checker)
	{
		return check((EntityAIGoalConditionChecker) checker);
	}

	public abstract boolean check(final EntityAIGoalConditionChecker checker);
}
