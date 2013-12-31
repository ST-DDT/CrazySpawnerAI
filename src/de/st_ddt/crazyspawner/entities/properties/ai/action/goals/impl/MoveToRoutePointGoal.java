package de.st_ddt.crazyspawner.entities.properties.ai.action.goals.impl;

import org.bukkit.entity.Creature;

import de.st_ddt.crazyspawner.ai.CrazySpawnerAI;
import de.st_ddt.crazyspawner.ai.routes.Path;
import de.st_ddt.crazyspawner.ai.routes.RoutePoint;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelper;

public class MoveToRoutePointGoal extends BasicGoal
{

	protected final static long SEARCHINTERVAL = 3000;
	protected final RoutePoint target;
	protected final double speed;
	protected Path path;
	protected long lastSearched;

	public MoveToRoutePointGoal(final Creature entity, final RoutePoint target, final double speed)
	{
		super(entity);
		this.target = target;
		this.speed = speed;
	}

	@Override
	public boolean shouldExecute(final boolean started)
	{
		if (path == null || !path.getStart().hasAccess(entity))
		{
			path = null;
			final long now = System.currentTimeMillis();
			if (now > lastSearched + SEARCHINTERVAL)
			{
				path = CrazySpawnerAI.getPlugin().searchPathTo(entity, target);
				lastSearched = now;
				if (path == null)
					return false;
			}
		}
		return true;
	}

	@Override
	public void start()
	{
		if (path == null)
			return;
		ActionHelper.getNavigation(entity).tryMoveTo(path.getStart().getLocation(), speed);
	}

	@Override
	public void update()
	{
		if (path == null)
			return;
		RoutePoint point = path.getStart();
		if (point.isWithinRange(entity))
		{
			do
			{
				point = path.removeFirst();
				if (point == null)
				{
					path = null;
					return;
				}
			}
			while (point.isWithinRange(entity));
			ActionHelper.getNavigation(entity).tryMoveTo(point.getLocation(), speed);
		}
	}

	@Override
	public void reset()
	{
		path = null;
		ActionHelper.getNavigation(entity).clearPath();
	}
}
