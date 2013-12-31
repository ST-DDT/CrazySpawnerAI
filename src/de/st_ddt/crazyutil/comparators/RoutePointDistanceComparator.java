package de.st_ddt.crazyutil.comparators;

import java.util.Comparator;

import org.bukkit.Location;

import de.st_ddt.crazyspawner.ai.routes.RoutePoint;

public class RoutePointDistanceComparator implements Comparator<RoutePoint>
{

	private final Location relative;

	public RoutePointDistanceComparator(final Location relative)
	{
		super();
		this.relative = relative;
	}

	@Override
	public int compare(final RoutePoint point1, final RoutePoint point2)
	{
		return LocationDistanceComparator.compare(relative, point1.getLocation(), point2.getLocation());
	}
}
