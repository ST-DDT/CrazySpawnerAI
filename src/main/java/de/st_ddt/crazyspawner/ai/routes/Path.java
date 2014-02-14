package de.st_ddt.crazyspawner.ai.routes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Path
{

	public final static DecimalFormat DISTANCEFORMAT = new DecimalFormat("0.##");
	private RoutePoint start;
	private RoutePoint target;
	private final List<RouteConnection> connections = new LinkedList<>();
	private double distance = 0;

	public Path(final RoutePoint start)
	{
		super();
		this.start = start;
		this.target = start;
	}

	public void addConnection(final RouteConnection connection)
	{
		final RoutePoint newTarget = connection.getRemotePoint(target);
		if (newTarget == null)
			throw new IllegalArgumentException("Cannot append disconnected route connection!");
		this.target = newTarget;
		connections.add(connection);
		distance += connection.getScaledDistance();
	}

	public RoutePoint getStart()
	{
		return start;
	}

	/**
	 * Removes the first element from this path.
	 * 
	 * @return The new start point of this route.
	 */
	public RoutePoint removeFirst()
	{
		if (connections.isEmpty())
			return null;
		final RouteConnection connection = connections.remove(0);
		start = connection.getRemotePoint(start);
		distance -= connection.getScaledDistance();
		return start;
	}

	public RoutePoint getTarget()
	{
		return target;
	}

	public List<RouteConnection> getConnections()
	{
		return connections;
	}

	public double getDistance()
	{
		return distance;
	}

	@Override
	public Path clone()
	{
		final Path path = new Path(start);
		path.target = target;
		path.connections.addAll(connections);
		path.distance = distance;
		return path;
	}

	public Path reverse()
	{
		final Path path = new Path(this.target);
		path.target = this.start;
		final List<RouteConnection> connections = new ArrayList<>(this.connections);
		Collections.reverse(connections);
		path.connections.addAll(connections);
		path.distance = distance;
		return path;
	}

	public void append(final Path path)
	{
		if (this.target != path.start)
			throw new IllegalArgumentException("Cannot append disconnected route connection!");
		this.target = path.target;
		this.connections.addAll(path.connections);
		this.distance += path.distance;
	}

	@Override
	public String toString()
	{
		RoutePoint point = start;
		final StringBuilder builder = new StringBuilder("Path{Id:" + point.getId());
		for (final RouteConnection connection : connections)
		{
			point = connection.getRemotePoint(point);
			builder.append("--(" + DISTANCEFORMAT.format(connection.getScaledDistance()) + "m)->Id:" + point.getId());
		}
		builder.append("; length: " + connections.size());
		builder.append("; distance: " + DISTANCEFORMAT.format(distance) + "m}");
		return builder.toString();
	}
}
