package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import org.bukkit.entity.Creature;
import org.bukkit.util.Vector;

import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;
import de.st_ddt.crazyspawner.entities.util.ai.Navigation;

public final class RandomLookaroundGoal extends BasicGoal
{

	private Vector target;
	private int time;

	public RandomLookaroundGoal(final Creature entity)
	{
		super(entity, MUTEX_FLAG_LOOK);
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		if (started)
			return time >= 0;
		else
			return true;
	}

	@Override
	public void start()
	{
		final double rotation = (Math.PI * 2D) * RANDOM.nextDouble();
		target = new Vector(Math.cos(rotation), 0, Math.sin(rotation));
		time = 20 + RANDOM.nextInt(20);
	}

	@Override
	public void update()
	{
		ActionHelper.getNavigation(entity).lookAt(entity.getEyeLocation().add(target), Navigation.DEFAULTROTATIONSPEED, Navigation.DEFAULTROTATIONSPEED);
		time--;
	}
}
