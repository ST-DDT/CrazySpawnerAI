package de.st_ddt.crazyutil.comparators;

import java.util.Comparator;

import de.st_ddt.crazyspawner.ai.routes.Path;

public class PathLengthComparator implements Comparator<Path>
{

	@Override
	public int compare(final Path o1, final Path o2)
	{
		return Double.compare(o1.getDistance(), o2.getDistance());
	}
}
