package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;
import de.st_ddt.crazyspawner.entities.util.ai.Navigation;

public final class RandomStrollGoal extends BasicGoal
{

	private final Navigation navigation;
	private final int horizontalOffset;
	private final int heightOffset;
	private final double speed;

	public RandomStrollGoal(final Creature entity, final int horizontalOffset, final int heightOffset, final double speed)
	{
		super(entity, MUTEX_FLAG_MOVE);
		this.navigation = ActionHelper.getNavigation(entity);
		this.horizontalOffset = horizontalOffset;
		this.heightOffset = heightOffset;
		this.speed = speed;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		if (started)
			return navigation.hasPath();
		else
			return true;
	}

	@Override
	public void start()
	{
		navigation.tryMoveTo(navigation.randomLocation(horizontalOffset, heightOffset), speed);
	}
}
