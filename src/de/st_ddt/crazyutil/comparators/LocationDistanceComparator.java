package de.st_ddt.crazyutil.comparators;

import java.util.Comparator;

import org.bukkit.Location;

public class LocationDistanceComparator implements Comparator<Location>
{

	private final Location relative;

	public LocationDistanceComparator(final Location relative)
	{
		super();
		this.relative = relative;
	}

	@Override
	public int compare(final Location loc1, final Location loc2)
	{
		return compare(relative, loc1, loc2);
	}

	public static int compare(final Location relative, final Location loc1, final Location loc2)
	{
		if (loc1 == loc2)
			return 0;
		int res = Double.compare(relative.distance(loc1), relative.distance(loc2));
		if (res != 0)
			return res;
		res = Double.compare(loc1.getX(), loc2.getX());
		if (res != 0)
			return res;
		res = Double.compare(loc1.getY(), loc2.getY());
		if (res != 0)
			return res;
		return Double.compare(loc1.getZ(), loc2.getZ());
	}
}
