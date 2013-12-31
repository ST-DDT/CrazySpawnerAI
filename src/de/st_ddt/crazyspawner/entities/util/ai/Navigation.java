package de.st_ddt.crazyspawner.entities.util.ai;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import de.st_ddt.crazyspawner.entities.properties.VelocityProperty;

public interface Navigation
{

	public final static double DEFAULTROTATIONSPEED = VelocityProperty.degToRad(30);

	/**
	 * @return True, if the path finder should avoid water.
	 */
	public boolean getAvoidsWater();

	/**
	 * Sets whether the path finder should avoid water.
	 * 
	 * @param avoidWater
	 *            Whether the path finder should avoid water.
	 */
	public void setAvoidsWater(boolean avoidWater);

	/**
	 * @return True, if the entity can break doors. False otherwise.
	 */
	public boolean getCanBreakDoors();

	/**
	 * Sets whether the entity can enter open doors.
	 * 
	 * @param breakDoors
	 *            Whether the entity can break doors.
	 */
	public void setBreakDoors(boolean breakDoors);

	/**
	 * Sets whether the entity can enter open doors.
	 * 
	 * @param enterDoors
	 *            Whether the entity can enter open doors.
	 */
	public void setEnterDoors(boolean enterDoors);

	/**
	 * Sets whether the path finder should avoid sunlight
	 * 
	 * @param avoidSun
	 *            Whether the path finder should avoid water.
	 */
	public void setAvoidSun(boolean avoidSun);

	/**
	 * Sets the speed used to reach the current target.<br>
	 * This value will be overwriten withe the next target specification.
	 * 
	 * @param speed
	 *            The speed used to reach the current target.
	 */
	public void setSpeed(double speed);

	/**
	 * Sets whether the entity can swim.
	 * 
	 * @param canSwin
	 *            Whether the entity can swim.
	 */
	public void setCanSwim(boolean canSwin);

	/**
	 * Checks whether this entity has a path to his target.
	 * 
	 * @return True, if the entity has a path and has not finished reaching its target.
	 */
	public boolean hasPath();

	/**
	 * Try to find and set a path to the given target location.
	 * 
	 * @param target
	 *            The target the entity should move to.
	 * @param speed
	 *            The entity's movement speed.
	 * @return True, if a path to the given target was found and will be executed.
	 */
	public boolean tryMoveTo(Location target, double speed);

	/**
	 * Try to find and set a path to the given target entity.
	 * 
	 * @param target
	 *            The target the entity should move to.
	 * @param speed
	 *            The entity's movement speed.
	 * @return True, if a path to the given target was found and will be executed.
	 */
	public boolean tryMoveTo(Entity target, double speed);

	/**
	 * Clears the current movement target.
	 */
	public void clearPath();

	/**
	 * Let the entity jump.
	 */
	public void jump();

	/**
	 * Look at the given target location.
	 * 
	 * @param target
	 *            The target location to look at.
	 * @param yawRotationSpeed
	 *            The yaw rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 * @param pitchRotationSpeed
	 *            The pitch rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 */
	public void lookAt(Location target, double yawRotationSpeed, double pitchRotationSpeed);

	/**
	 * Look at the given target entity.
	 * 
	 * @param target
	 *            The target entity to look at.
	 * @param yawRotationSpeed
	 *            The yaw rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 * @param pitchRotationSpeed
	 *            The pitch rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 */
	public void lookAt(Entity target, double yawRotationSpeed, double pitchRotationSpeed);

	/**
	 * Look in the given direction
	 * 
	 * @param yaw
	 *            The yaw direction to face to.<br>
	 *            In Randians.
	 * @param pitch
	 *            The pitch direction to face to.<br>
	 *            In Randians.
	 * @param yawRotationSpeed
	 *            The yaw rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 * @param pitchRotationSpeed
	 *            The pitch rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 */
	// public void lookInDirection(double yaw, double pitch, double yawRotationSpeed, double pitchRotationSpeed);
	/**
	 * Look in the given direction.
	 * 
	 * @param direction
	 *            The location to look at.
	 * @param yawRotationSpeed
	 *            The yaw rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 * @param pitchRotationSpeed
	 *            The pitch rotation speed.<br>
	 *            In Radians.<br>
	 *            Default = {@link #DEFAULTROTATIONSPEED}
	 */
	public void lookInDirection(Vector direction, double yawRotationSpeed, double pitchRotationSpeed);
}
