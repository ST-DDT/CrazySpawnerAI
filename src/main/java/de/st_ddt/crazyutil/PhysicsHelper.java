package de.st_ddt.crazyutil;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class PhysicsHelper
{

	/**
	 * Tries to shoot or throw an {@link Entity} from a given {@link Location} to a given target.<br>
	 * This method does not guarantee that the projectile actually hits the target.<br>
	 * There are mainly four reasons why the target won't be hit by a projectile launched by this method.<br>
	 * <ul>
	 * <li>There are {@link Block}s or {@link Entity}s in the way that will be hit instead</li>
	 * <li>The goal moves unexpectedly</li>
	 * <li>The target is too far away, even a player could not hit the target</li>
	 * <li>Issues with the physics approximation (too short or slightly too high)</li>
	 * </ul>
	 * This method does not check whether the shooter looks in the right direction<br>
	 * but it will prevent the projectile from being launched from inside the shooter.
	 * 
	 * @param type
	 *            The {@link Entity} class to shoot or throw.
	 * @param shooter
	 *            The shooter of the projectile.
	 * @param speed
	 *            The speed of the projectile.
	 * @param target
	 *            The target that should be hit/shot at.
	 * @param targetVelQuote
	 *            The quote of the target velocity that should impact of the target {@link Location}.<br>
	 *            <ul>
	 *            <li>0.0 = aim at given target {@link Location}/no impact</li>
	 *            <li>0.5 = aim at center between given {@link Location} and expected {@link Location}</li>
	 *            <li>1.0 = aim at expected {@link Location}</li>
	 *            </ul>
	 * @return The projectile that has been shot/thrown.
	 */
	public static <T extends Entity> T shoot(final Class<T> type, final Entity shooter, final double speed, final Entity target, final double targetVelQuote)
	{
		final T projectile = shoot(type, getShooterOffset(shooter), true, speed, target, targetVelQuote);
		if (projectile instanceof Projectile && shooter instanceof ProjectileSource)
			((Projectile) projectile).setShooter((ProjectileSource) shooter);
		return projectile;
	}

	/**
	 * Tries to shoot or throw an {@link Entity} from a given {@link Location} to a given target.<br>
	 * This method does not guarantee that the projectile actually hits the target.<br>
	 * There are mainly four reasons why the target won't be hit by a projectile launched by this method.<br>
	 * <ul>
	 * <li>There are {@link Block}s or {@link Entity}s in the way that will be hit instead</li>
	 * <li>The goal moves unexpectedly</li>
	 * <li>The target is too far away, even a player could not hit the target</li>
	 * <li>Issues with the physics approximation (too short or slightly too high)</li>
	 * </ul>
	 * This method does not check whether the shooter looks in the right direction<br>
	 * but it will prevent the projectile from being launched from inside the shooter.
	 * 
	 * @param type
	 *            The {@link Entity} class to shoot or throw.
	 * @param shooter
	 *            The shooter of the projectile.
	 * @param speed
	 *            The speed of the projectile.
	 * @param targetLoc
	 *            The target that should be hit/shot at.
	 * @param targetVel
	 *            The velocity of the target which will be used to pre compensate any movement of the target or NULL if there is no velocity.
	 * @param targetVelQuote
	 *            The quote of the target velocity that should impact of the target {@link Location}.<br>
	 *            <ul>
	 *            <li>0.0 = aim at given target {@link Location}/no impact</li>
	 *            <li>0.5 = aim at center between given {@link Location} and expected {@link Location}</li>
	 *            <li>1.0 = aim at expected {@link Location}</li>
	 *            </ul>
	 * @return The projectile that has been shot/thrown.
	 */
	public static <T extends Entity> T shoot(final Class<T> type, final Entity shooter, final double speed, final Location targetLoc, final Vector targetVel, final double targetVelQuote)
	{
		final T projectile = shoot(type, getShooterOffset(shooter), true, speed, targetLoc, targetVel, targetVelQuote);
		if (projectile instanceof Projectile && shooter instanceof ProjectileSource)
			((Projectile) projectile).setShooter((ProjectileSource) shooter);
		return projectile;
	}

	/**
	 * Tries to shoot or throw an {@link Entity} from a given {@link Location} to a given target.<br>
	 * This method does not guarantee that the projectile actually hits the target.<br>
	 * There are mainly four reasons why the target won't be hit by a projectile launched by this method.<br>
	 * <ul>
	 * <li>There are {@link Block}s or {@link Entity}s in the way that will be hit instead</li>
	 * <li>The goal moves unexpectedly</li>
	 * <li>The target is too far away, even a player could not hit the target</li>
	 * <li>Issues with the physics approximation (too short or slightly too high)</li>
	 * </ul>
	 * 
	 * @param type
	 *            The {@link Entity} class to shoot or throw.
	 * @param launchLoc
	 *            The {@link Location} where the projectile should be launched at.
	 * @param launchWithOffset
	 *            If true, the launch {@link Location} will be adjusted by 1 tick of flight to prevent collisions between the shooter and the projectile.
	 * @param speed
	 *            The speed of the projectile.
	 * @param target
	 *            The target that should be hit/shot at.
	 * @param targetVelQuote
	 *            The quote of the target velocity that should impact of the target {@link Location}.<br>
	 *            <ul>
	 *            <li>0.0 = aim at given target {@link Location}/no impact</li>
	 *            <li>0.5 = aim at center between given {@link Location} and expected {@link Location}</li>
	 *            <li>1.0 = aim at expected {@link Location}</li>
	 *            </ul>
	 * @return The projectile that has been shot/thrown.
	 */
	public static <T extends Entity> T shoot(final Class<T> type, final Location launchLoc, final boolean launchWithOffset, final double speed, final Entity target, final double targetVelQuote)
	{
		return shoot(type, launchLoc, launchWithOffset, speed, getTargetLocation(target), target.getVelocity(), targetVelQuote);
	}

	/**
	 * Tries to shoot or throw an {@link Entity} from a given {@link Location} to a given target.<br>
	 * This method does not guarantee that the projectile actually hits the target.<br>
	 * There are mainly four reasons why the target won't be hit by a projectile launched by this method.<br>
	 * <ul>
	 * <li>There are {@link Block}s or {@link Entity}s in the way that will be hit instead</li>
	 * <li>The goal moves unexpectedly</li>
	 * <li>The target is too far away, even a player could not hit the target</li>
	 * <li>Issues with the physics approximation (too short or slightly too high)</li>
	 * </ul>
	 * 
	 * @param type
	 *            The {@link Entity} class to shoot or throw.
	 * @param launchLoc
	 *            The {@link Location} where the projectile should be launched at.
	 * @param launchWithOffset
	 *            If true, the launch {@link Location} will be adjusted by 1 tick of flight to prevent collisions between the shooter and the projectile.
	 * @param speed
	 *            The speed of the projectile.
	 * @param targetLoc
	 *            The target that should be hit/shot at.
	 * @param targetVel
	 *            The velocity of the target which will be used to pre compensate any movement of the target or NULL if there is no velocity.
	 * @param targetVelQuote
	 *            The quote of the target velocity that should impact of the target {@link Location}.<br>
	 *            <ul>
	 *            <li>0.0 = aim at given target {@link Location}/no impact</li>
	 *            <li>0.5 = aim at center between given {@link Location} and expected {@link Location}</li>
	 *            <li>1.0 = aim at expected {@link Location}</li>
	 *            </ul>
	 * @return The projectile that has been shot/thrown.
	 */
	public static <T extends Entity> T shoot(final Class<T> type, final Location launchLoc, final boolean launchWithOffset, final double speed, final Location targetLoc, Vector targetVel, final double targetVelQuote)
	{
		final Vector offset = new Vector(targetLoc.getX() - launchLoc.getX(), targetLoc.getY() - launchLoc.getY(), targetLoc.getZ() - launchLoc.getZ());
		final double length = offset.length();
		final double time = Math.pow(length, 1.0101) / speed;
		if (targetVel != null && targetVelQuote > 0)
		{
			// Use a copy to prevent unintended modification
			targetVel = targetVel.clone();
			targetVel.multiply(time * targetVelQuote);
			// Calculate target Offset with movement
			offset.add(targetVel);
		}
		// Counter gravitation
		offset.setY(offset.getY() + yGravity(time));
		offset.normalize();
		final Location aLoc = launchLoc.clone().setDirection(offset);
		// Do not shoot from inside the shooter
		if (launchWithOffset)
			aLoc.add(offset);
		final T projectile = launchLoc.getWorld().spawn(aLoc, type);
		projectile.setVelocity(offset.multiply(speed));
		return projectile;
	}

	private static Location getShooterOffset(final Entity shooter)
	{
		if (shooter instanceof LivingEntity)
		{
			final LivingEntity living = (LivingEntity) shooter;
			final Location targetLocation = living.getEyeLocation();
			targetLocation.add(0, -living.getEyeHeight() / 3, 0);
			return targetLocation;
		}
		else
			return shooter.getLocation();
	}

	private static Location getTargetLocation(final Entity target)
	{
		if (target instanceof LivingEntity)
		{
			final LivingEntity living = (LivingEntity) target;
			final Location targetLocation = living.getEyeLocation();
			targetLocation.add(0, -living.getEyeHeight() / 4, 0);
			return targetLocation;
		}
		else
			return target.getLocation();
	}

	private static double yGravity(final double z)
	{
		return 5D * (99D * (Math.pow(99D / 100D, z) - 1D) + z);
	}
}
