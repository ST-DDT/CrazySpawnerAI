package de.st_ddt.crazyspawner.entities.properties.ai.action.builder.impl;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.properties.ai.action.builder.GoalBuilder;
import de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl.ConditionedGoal;
import de.st_ddt.crazyutil.conditions.BasicCondition;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.Condition_TRUE;
import de.st_ddt.crazyutil.conditions.checker.CreatureConditionChecker;

public final class ConditionedGoalBuilder extends BasicGoalBuilder
{

	protected final Condition startCondition;
	protected final Condition continueCondition;
	protected final GoalBuilder builder;

	public ConditionedGoalBuilder()
	{
		super();
		this.startCondition = new Condition_TRUE();
		this.continueCondition = new Condition_TRUE();
		this.builder = new WatchClosestGoalBuilder();
	}

	public ConditionedGoalBuilder(final Condition startCondition, final Condition continueCondition, final GoalBuilder builder)
	{
		super();
		if (startCondition == null)
			this.startCondition = new Condition_TRUE();
		else if (startCondition.isApplicable(CreatureConditionChecker.class))
			this.startCondition = startCondition;
		else
			throw new IllegalArgumentException("The startCondition is not applicable for Creatures!");
		if (continueCondition == null)
			this.continueCondition = new Condition_TRUE();
		else if (continueCondition.isApplicable(CreatureConditionChecker.class))
			this.continueCondition = continueCondition;
		else
			throw new IllegalArgumentException("The startCondition is not applicable for Creatures!");
		this.builder = builder;
	}

	public ConditionedGoalBuilder(final ConfigurationSection config)
	{
		super(config);
		Condition startCondition = null;
		try
		{
			startCondition = BasicCondition.load(config.getConfigurationSection("startCondition"));
		}
		catch (final Exception e)
		{
			System.err.println(config.getName() + "'s start condition was corrupted/invalid and has been removed!");
			startCondition = new Condition_TRUE();
		}
		this.startCondition = startCondition;
		Condition continueCondition = null;
		try
		{
			continueCondition = BasicCondition.load(config.getConfigurationSection("continueCondition"));
		}
		catch (final Exception e)
		{
			System.err.println(config.getName() + "'s continue condition was corrupted/invalid and has been removed!");
			continueCondition = new Condition_TRUE();
		}
		this.continueCondition = continueCondition;
		GoalBuilder builder = null;
		try
		{
			builder = BasicGoalBuilder.load(config.getConfigurationSection("goal"));
		}
		catch (final Throwable e)
		{
			System.err.println(config.getName() + "'s goal was corrupted/invalid and has been removed!");
			builder = new WatchClosestGoalBuilder();
		}
		this.builder = builder;
	}

	@Override
	public String getType()
	{
		return "ConditionedGoal";
	}

	@Override
	public ConditionedGoal build(final Creature entity)
	{
		return new ConditionedGoal(entity, startCondition, continueCondition, builder.build(entity));
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		super.save(config, path);
		startCondition.save(config, path + "startCondition.");
		continueCondition.save(config, path + "continueCondition.");
		builder.save(config, path + "goal.");
	}
}
