package de.st_ddt.crazyspawner.ai.routes;

import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

import de.st_ddt.crazyutil.ConfigurationSaveable;

public class RouteConnection implements ConfigurationSaveable
{

	private final RoutePoint point1;
	private final RoutePoint point2;
	private final double distance;
	private double distanceScale;
	private double scaledDistance;

	public RouteConnection(final RoutePoint point1, final RoutePoint point2)
	{
		this(point1, point2, calcDistance(point1, point2));
	}

	public RouteConnection(final RoutePoint point1, final RoutePoint point2, final double distance)
	{
		this(point1, point2, distance, 1);
	}

	public RouteConnection(final RoutePoint point1, final RoutePoint point2, final double distance, final double distanceScale)
	{
		super();
		if (point1 == null)
			throw new IllegalArgumentException("Connected RoutePoint1 cannot be null!");
		if (point2 == null)
			throw new IllegalArgumentException("Connected RoutePoint2 cannot be null!");
		if (point1 == point2)
			throw new IllegalArgumentException("Cannot connect to the same RoutePoint!");
		this.point1 = point1;
		this.point2 = point2;
		this.distance = distance;
		this.distanceScale = Math.max(distanceScale, 0);
		this.scaledDistance = this.distance * this.distanceScale;
	}

	public RouteConnection(final Map<Integer, RoutePoint> points, final ConfigurationSection config)
	{
		this.point1 = points.get(config.getInt("point1", -1));
		this.point2 = points.get(config.getInt("point2", -1));
		if (point1 == null)
			throw new IllegalArgumentException("Connected RoutePoint1 cannot be null!");
		if (point2 == null)
			throw new IllegalArgumentException("Connected RoutePoint2 cannot be null!");
		if (point1 == point2)
			throw new IllegalArgumentException("Cannot connect to the same RoutePoint!");
		final double distance = config.getDouble("distance", -1);
		if (distance <= 0)
			this.distance = calcDistance(point1, point2);
		else
			this.distance = distance;
		this.distanceScale = Math.max(config.getDouble("distanceScale", 1), 0);
		this.scaledDistance = this.distance * this.distanceScale;
	}

	private final static double calcDistance(final RoutePoint point1, final RoutePoint point2)
	{
		return point1.getLocation().distance(point2.getLocation());
	}

	public RoutePoint getPoint1()
	{
		return point1;
	}

	public RoutePoint getPoint2()
	{
		return point2;
	}

	public void connect()
	{
		this.point1.getConnections().add(this);
		this.point2.getConnections().add(this);
	}

	public RoutePoint getRemotePoint(final RoutePoint point)
	{
		if (point1 == point)
			return point2;
		else if (point2 == point)
			return point1;
		else
			return null;
	}

	public double getDistance()
	{
		return distance;
	}

	public double getDistanceScale()
	{
		return distanceScale;
	}

	public void setDistanceScale(final double distanceScale)
	{
		this.distanceScale = Math.max(distanceScale, 0);
		this.scaledDistance = this.distance * this.distanceScale;
	}

	public double getScaledDistance()
	{
		return scaledDistance;
	}

	public void delete()
	{
		point1.getConnections().remove(this);
		point2.getConnections().remove(this);
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		config.set(path + "point1", point1.getId());
		config.set(path + "point2", point2.getId());
		config.set(path + "distance", distance);
		config.set(path + "distanceScale", distanceScale);
	}

	@Override
	public int hashCode()
	{
		return point1.getId() << 16 + point2.getId();
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RouteConnection other = (RouteConnection) obj;
		if (!point1.equals(other.point1))
			return false;
		if (!point2.equals(other.point2))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Route{" + point1.toString() + "<->" + point2.toString() + "; distance: " + distance + "m}";
	}
}
