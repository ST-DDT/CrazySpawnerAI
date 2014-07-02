package de.st_ddt.crazyspawner.ai.routes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;

import de.st_ddt.crazyutil.comparators.PathLengthComparator;
import de.st_ddt.crazyutil.comparators.RoutePointDistanceComparator;
import de.st_ddt.crazyutil.conditions.ConditionHelper;
import de.st_ddt.crazyutil.config.CrazyYamlConfiguration;

public class RouteMap
{

	private final static File ROUTEFILEDIR = new File("plugins/CrazySpawnerAI/Routes");
	private final static Comparator<Path> PATHLENGTHCOMPARATOR = new PathLengthComparator();
	private final static ExecutorService THREADPOOL = Executors.newCachedThreadPool();
	private final World world;
	private final File file;
	private final Map<Integer, RoutePoint> points = new TreeMap<>();
	private final Map<CoordinateKey, Set<RoutePoint>> pointsPerChunk = new HashMap<>();

	public RouteMap(final World world)
	{
		super();
		this.world = world;
		this.file = new File(ROUTEFILEDIR, world.getName() + ".yml");
		load();
	}

	public void load()
	{
		if (!file.exists())
			return;
		final CrazyYamlConfiguration config = new CrazyYamlConfiguration();
		try
		{
			config.load(file);
		}
		catch (final Exception e)
		{
			System.err.println("[CrazySpawnerAI] Could not read " + file.getPath());
			e.printStackTrace();
			return;
		}
		boolean createBackUp = false;
		final ConfigurationSection pointsConfig = config.getConfigurationSection("points");
		if (pointsConfig == null)
			return;
		for (final String key : pointsConfig.getKeys(false))
			try
			{
				addRoutePoint(new RoutePoint(world, pointsConfig.getConfigurationSection(key)));
			}
			catch (final Exception e)
			{
				System.err.println("[CrazySpawnerAI] " + world.getName() + "'s route point " + key + " was corrupted/invalid and has been removed!");
				e.printStackTrace();
				createBackUp = true;
			}
		final ConfigurationSection connectionsConfig = config.getConfigurationSection("connections");
		if (connectionsConfig != null)
			for (final String key : connectionsConfig.getKeys(false))
				try
				{
					new RouteConnection(points, connectionsConfig.getConfigurationSection(key)).connect();
				}
				catch (final Exception e)
				{
					System.err.println("[CrazySpawnerAI] " + world.getName() + "'s route connection " + key + " was corrupted/invalid and has been removed!");
					e.printStackTrace();
					createBackUp = true;
				}
		if (createBackUp)
		{
			final File backupFile = CrazyYamlConfiguration.createBackup(file);
			System.err.println("[CrazySpawnerAI] Created backup: " + backupFile.getPath());
		}
	}

	public void save()
	{
		final CrazyYamlConfiguration config = new CrazyYamlConfiguration();
		final Set<RouteConnection> connections = new HashSet<>();
		for (final RoutePoint point : points.values())
		{
			point.save(config, "points.p" + point.getId());
			connections.addAll(point.getConnections());
		}
		int cCount = 0;
		for (final RouteConnection connection : connections)
			connection.save(config, "connections.c" + (cCount++));
		try
		{
			file.getParentFile().mkdirs();
			config.save(file);
		}
		catch (final IOException e)
		{
			System.err.println("[CrazySpawnerAI] Could not save route map " + file.getPath() + ".");
			System.err.println(e.getMessage());
		}
	}

	public boolean isPartOfNetwork(final RoutePoint point)
	{
		return points.get(point.getId()) == point;
	}

	/**
	 * Returns the {@link RoutePoint} with the given id.
	 * 
	 * @param id
	 *            The id of the {@link RoutePoint} that should be returned.
	 * @return The {@link RoutePoint} with the given id,<br>
	 *         or Null if there is no {@link RoutePoint} with the given id.
	 */
	public RoutePoint getPoint(final int id)
	{
		return points.get(id);
	}

	/**
	 * Searches the closest {@link RoutePoint} next to the given location within chunkSearchRange.
	 * 
	 * @param location
	 *            The location where the {@link RoutePoint} should be searched.
	 * @param chunkSearchRange
	 *            The range in chunks where {@link RoutePoint}s are searched.<br>
	 *            0=Only the chunk the location is in.
	 * @return The closest {@link RoutePoint} next to the given location within chunkSearchRange.
	 */
	public RoutePoint searchRoutePoint(final Location location, final int chunkSearchRange)
	{
		final int cX = location.getBlockX() / 16;
		final int cZ = location.getBlockZ() / 16;
		final List<RoutePoint> list = new ArrayList<>();
		for (int oX = -chunkSearchRange; oX <= chunkSearchRange; oX++)
			for (int oZ = -chunkSearchRange; oZ <= chunkSearchRange; oZ++)
			{
				final Set<RoutePoint> points = pointsPerChunk.get(new CoordinateKey(cX + oX, cZ + oZ));
				if (points != null)
					list.addAll(points);
			}
		if (list.isEmpty())
			return null;
		Collections.sort(list, new RoutePointDistanceComparator(location));
		return list.get(0);
	}

	/**
	 * Searches the shortest path between the start point and the target point, which can be used by the given entity.
	 * 
	 * @param entity
	 *            The entity that should be able to access all found {@link RoutePoint}s along the way.
	 * @param start
	 *            The searched {@link Path}'s start point.
	 * @param target
	 *            The searched {@link Path}'s target point.
	 * @return The shortest path between the start point and the target point.
	 */
	public Path searchPath(final LivingEntity entity, final RoutePoint start, final RoutePoint target)
	{
		final Map<Integer, Object> parameters = ConditionHelper.simpleParameters(entity);
		final Queue<Path> pathsStart = new PriorityQueue<>(100, PATHLENGTHCOMPARATOR);
		final Path pathStart = new Path(start);
		pathsStart.add(pathStart);
		final Queue<Path> pathsTarget = new PriorityQueue<>(100, PATHLENGTHCOMPARATOR);
		final Path pathTarget = new Path(target);
		pathsTarget.add(pathTarget);
		final Map<RoutePoint, Path> visitedStart = Collections.synchronizedMap(new HashMap<RoutePoint, Path>());
		visitedStart.put(start, pathStart);
		final Map<RoutePoint, Path> visitedTarget = Collections.synchronizedMap(new HashMap<RoutePoint, Path>());
		visitedTarget.put(target, pathTarget);
		final RouteCallable startCallable = new RouteCallable(pathsStart, parameters, visitedStart, visitedTarget);
		final RouteCallable targetCallable = new RouteCallable(pathsTarget, parameters, visitedTarget, visitedStart);
		// Actually search the path.
		// This part is two threaded.
		// One thread searches the path beginning with at the start point.
		// The other one searches the path beginning with at the target point.
		try
		{
			while (true)
			{
				final Path startPath = pathsStart.poll();
				final Path targetPath = pathsTarget.poll();
				// No more paths to search.
				// if one of both is Null there is no way between both points.
				// Disjunkte Graphen
				if (startPath == null || targetPath == null)
					return null;
				startCallable.setPath(startPath);
				targetCallable.setPath(targetPath);
				final Future<Path> startFuture = THREADPOOL.submit(startCallable);
				final Future<Path> targetFuture = THREADPOOL.submit(targetCallable);
				final Path startFutureResult = startFuture.get();
				if (startFutureResult != null)
					return startFutureResult;
				final Path targetFutureResult = targetFuture.get();
				if (targetFutureResult != null)
					return targetFutureResult.reverse();
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Performs a single search step.<br>
	 * Iterates trough all possible connections from the paths target.<br>
	 * The connection will be ignored, if the target point has been visited before.<br>
	 * If the target has been visited by the reverse traveler before the merged paths will be returned. Otherwise the found connection will be added to the queue for later checking.
	 * 
	 * @param path
	 *            The existing path to be continued.
	 * @param pathQueue
	 *            The path queue containing paths that should be checked next/later.
	 * @param parameters
	 *            The parameters used to validate whether the {@link RoutePoint}s can be accessed.
	 * @param visitedLocal
	 *            The {@link RoutePoint}s visited by the forward/local search and the shortest paths to them.
	 * @param visitedRemote
	 *            The {@link RoutePoint}s visited by the reverse/remote search and the shortest paths to them.
	 * @return The shortest path between the starting point and the target point.<br>
	 *         More specific the merged paths from the (first) RoutePoint available in both visited maps.<br>
	 *         Null, if no Path is found.
	 */
	private static Path searchPath(final Path path, final Queue<Path> pathQueue, final Map<Integer, Object> parameters, final Map<RoutePoint, Path> visitedLocal, final Map<RoutePoint, Path> visitedRemote)
	{
		final RoutePoint startPathEnd = path.getTarget();
		for (final RouteConnection connection : startPathEnd.getConnections())
		{
			final RoutePoint remote = connection.getRemotePoint(startPathEnd);
			final Path remotePath;
			if (visitedLocal.containsKey(remote))
				continue;
			remotePath = path.clone();
			remotePath.addConnection(connection);
			visitedLocal.put(remote, remotePath);
			if (!remote.getAccessCondition().check(parameters))
				continue;
			final Path connectionPath = visitedRemote.get(remote);
			if (connectionPath != null)
				return mergePaths(remotePath, connectionPath);
			pathQueue.add(remotePath);
		}
		return null;
	}

	/**
	 * Merges the given paths to one.<br>
	 * The second path will be reversed, before it will be attached to the first one.<br>
	 * This method does not modify the input paths.
	 * 
	 * @param pathFromStart
	 *            The first part of the path.
	 * @param pathFromTarget
	 *            The second part of the path.<br>
	 *            The path will be reversed internally.
	 * @return A new path starting at the pathFromStart's start point and ending at the pathFromEnd's start point.
	 */
	private static Path mergePaths(final Path pathFromStart, final Path pathFromTarget)
	{
		final Path path = pathFromStart.clone();
		path.append(pathFromTarget.reverse());
		return path;
	}

	/**
	 * Adds the given {@link RoutePoint} to the repository of this {@link RouteMap}
	 * 
	 * @param point
	 *            The {@link RouteMap} that should be added to this {@link RouteMap}
	 */
	public void addRoutePoint(final RoutePoint point)
	{
		points.put(point.getId(), point);
		final Location location = point.getLocation();
		final CoordinateKey key = new CoordinateKey(location.getBlockX() / 16, location.getBlockZ() / 16);
		Set<RoutePoint> chunkPoints = pointsPerChunk.get(key);
		if (chunkPoints == null)
		{
			chunkPoints = new HashSet<>();
			pointsPerChunk.put(key, chunkPoints);
		}
		chunkPoints.add(point);
	}

	/**
	 * Helper class to store value pairs.
	 */
	private final static class CoordinateKey
	{

		private final int x;
		private final int z;

		public CoordinateKey(final int x, final int z)
		{
			super();
			this.x = x;
			this.z = z;
		}

		@Override
		public int hashCode()
		{
			return (x << 16) + (z & 0x8000FFFF);
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
			final CoordinateKey other = (CoordinateKey) obj;
			if (x != other.x)
				return false;
			if (z != other.z)
				return false;
			return true;
		}
	}

	private static class RouteCallable implements Callable<Path>
	{

		private final Queue<Path> pathQueue;
		private final Map<Integer, Object> parameters;
		private final Map<RoutePoint, Path> visitedLokal;
		private final Map<RoutePoint, Path> visitedRemote;
		private Path path;

		public RouteCallable(final Queue<Path> pathQueue, final Map<Integer, Object> parameters, final Map<RoutePoint, Path> visitedLokal, final Map<RoutePoint, Path> visitedRemote)
		{
			super();
			this.pathQueue = pathQueue;
			this.parameters = parameters;
			this.visitedLokal = visitedLokal;
			this.visitedRemote = visitedRemote;
		}

		public void setPath(final Path path)
		{
			this.path = path;
		}

		@Override
		public Path call() throws Exception
		{
			return searchPath(path, pathQueue, parameters, visitedLokal, visitedRemote);
		}
	}
}
