package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import org.bukkit.entity.Creature;
import org.bukkit.util.Vector;

import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;
import de.st_ddt.crazyspawner.entities.util.ai.Navigation;

public class StareInDirectionGoal extends BasicGoal
{

	protected final Vector direction;

	public StareInDirectionGoal(final Creature entity, final Vector direction)
	{
		super(entity, MUTEX_FLAG_LOOK);
		this.direction = direction;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		return true;
	}

	@Override
	public void start()
	{
		ActionHelper.getNavigation(entity).lookInDirection(direction, Navigation.DEFAULTROTATIONSPEED, Navigation.DEFAULTROTATIONSPEED);
	}
}
