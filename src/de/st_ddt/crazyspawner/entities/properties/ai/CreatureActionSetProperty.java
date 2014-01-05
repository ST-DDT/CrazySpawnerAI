package de.st_ddt.crazyspawner.entities.properties.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import de.st_ddt.crazyspawner.entities.properties.BasicProperty;
import de.st_ddt.crazyspawner.entities.properties.ai.action.GoalEntry;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;
import de.st_ddt.crazyutil.paramitrisable.Paramitrisable;
import de.st_ddt.crazyutil.paramitrisable.TabbedParamitrisable;

public class CreatureActionSetProperty extends BasicProperty
{

	private final List<GoalEntry> goalEntries;

	public CreatureActionSetProperty()
	{
		super();
		this.goalEntries = null;
	}

	public CreatureActionSetProperty(final GoalEntry... goals)
	{
		super();
		this.goalEntries = new ArrayList<>();
		for (final GoalEntry goal : goals)
			this.goalEntries.add(goal);
	}

	public CreatureActionSetProperty(final Collection<GoalEntry> goals)
	{
		super();
		if (goals == null)
			this.goalEntries = null;
		else
			this.goalEntries = new ArrayList<>(goals);
	}

	public CreatureActionSetProperty(final ConfigurationSection config)
	{
		super(config);
		final List<GoalEntry> goals;
		final ConfigurationSection goalConfig = config.getConfigurationSection("ai.goals");
		if (goalConfig == null)
			goals = null;
		else
		{
			goals = new ArrayList<>();
			for (final String key : goalConfig.getKeys(false))
				try
				{
					goals.add(GoalEntry.load(goalConfig.getConfigurationSection(key)));
				}
				catch (final Throwable e)
				{
					System.err.println(config.getName() + "'s AI Goals " + key + " was corrupted/invalid and has been removed!");
				}
		}
		this.goalEntries = goals;
	}

	public CreatureActionSetProperty(final Map<String, ? extends Paramitrisable> params)
	{
		super(params);
		// TODO Auto-generated constructor stub
		this.goalEntries = null;
	}

	@Override
	public boolean isApplicable(final Class<? extends Entity> clazz)
	{
		return Creature.class.isAssignableFrom(clazz);
	}

	@Override
	public void apply(final Entity entity)
	{
		if (goalEntries == null)
			return;
		final Creature creature = (Creature) entity;
		ActionHelper.clearGoals(creature);
		for (final GoalEntry goalEntry : goalEntries)
			ActionHelper.addGoal(creature, goalEntry.getBuilder(), goalEntry.getPriority());
	}

	@Override
	public void getCommandParams(final Map<String, ? super TabbedParamitrisable> params, final CommandSender sender)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		if (goalEntries == null)
			config.set(path + "ai.goals", null);
		else if (goalEntries.isEmpty())
			config.set(path + "ai.goals", new HashMap<>());
		else
		{
			config.set(path + "ai.goals", null);
			int a = 0;
			for (final GoalEntry goalEntry : goalEntries)
				goalEntry.save(config, path + "ai.goals.g" + (a++) + ".");
		}
	}

	@Override
	public void dummySave(final ConfigurationSection config, final String path)
	{
		config.set("ai.goals.g0", "GoalEntry");
		config.set("ai.goals.g1", "GoalEntry");
		config.set("ai.goals.g2", "GoalEntry");
		config.set("ai.goals.gn", "GoalEntry");
	}

	@Override
	public void show(final CommandSender target)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public boolean equalsDefault()
	{
		return goalEntries == null;
	}
}
