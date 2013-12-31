package de.st_ddt.crazyutil.comparators;

import java.util.Comparator;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityDistanceComparator implements Comparator<Entity>
{

	private final Location relative;

	public EntityDistanceComparator(final Location relative)
	{
		super();
		this.relative = relative;
	}

	@Override
	public int compare(final Entity e1, final Entity e2)
	{
		return LocationDistanceComparator.compare(relative, e1.getLocation(), e2.getLocation());
	}
}
