package de.st_ddt.crazyspawner.craftbukkit.v1_7_R1.util.ai;

import net.minecraft.server.v1_7_R1.EntityCreature;
import net.minecraft.server.v1_7_R1.RandomPositionGenerator;
import net.minecraft.server.v1_7_R1.Vec3D;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import de.st_ddt.crazyspawner.entities.util.ai.Navigation;
import de.st_ddt.crazyutil.paramitrisable.VectorParamitrisable;

public class NavigationImpl implements Navigation
{

	static ActionHelperImpl ACTIONHELPER;
	private final EntityCreature creature;

	public NavigationImpl(final EntityCreature creature)
	{
		super();
		this.creature = creature;
	}

	@Override
	public boolean getAvoidsWater()
	{
		return creature.getNavigation().a();
	}

	@Override
	public void setAvoidsWater(final boolean avoidWater)
	{
		creature.getNavigation().a(avoidWater);
	}

	@Override
	public boolean getCanBreakDoors()
	{
		return creature.getNavigation().c();
	}

	@Override
	public void setBreakDoors(final boolean breakDoors)
	{
		creature.getNavigation().b(breakDoors);
	}

	@Override
	public void setEnterDoors(final boolean enterDoors)
	{
		creature.getNavigation().c(enterDoors);
	}

	@Override
	public void setAvoidSun(final boolean avoidSun)
	{
		creature.getNavigation().d(avoidSun);
	}

	@Override
	public void setSpeed(final double speed)
	{
		creature.getNavigation().a(speed);
	}

	@Override
	public void setCanSwim(final boolean canSwin)
	{
		creature.getNavigation().e(canSwin);
	}

	@Override
	public boolean hasPath()
	{
		return !creature.getNavigation().g();
	}

	@Override
	public boolean tryMoveTo(final Location target, final double speed)
	{
		if (!target.getWorld().equals(target.getWorld()))
			throw new IllegalArgumentException("Cannot walk to another world!");
		return creature.getNavigation().a(target.getX(), target.getY(), target.getZ(), speed);
	}

	@Override
	public boolean tryMoveTo(final Entity target, final double speed)
	{
		if (!target.getLocation().getWorld().equals(target.getWorld()))
			throw new IllegalArgumentException("Cannot walk to another world!");
		return creature.getNavigation().a(ACTIONHELPER.getHandle(target), speed);
	}

	@Override
	public void clearPath()
	{
		creature.getNavigation().h();
	}

	@Override
	public void jump()
	{
		creature.getControllerJump().a();
	}

	@Override
	public void lookAt(final Location target, final double yawRotationSpeed, final double pitchRotationSpeed)
	{
		creature.getControllerLook().a(target.getX(), target.getY(), target.getZ(), (float) VectorParamitrisable.radToDeg(yawRotationSpeed), (float) VectorParamitrisable.radToDeg(pitchRotationSpeed));
	}

	@Override
	public void lookAt(final Entity target, final double yawRotationSpeed, final double pitchRotationSpeed)
	{
		if (target instanceof LivingEntity)
			lookAt(((LivingEntity) target).getEyeLocation(), yawRotationSpeed, pitchRotationSpeed);
		else
			lookAt(target.getLocation(), yawRotationSpeed, pitchRotationSpeed);
	}

	@Override
	public void lookInDirection(final Vector direction, final double yawRotationSpeed, final double pitchRotationSpeed)
	{
		final Location location = creature.getBukkitEntity().getLocation().add(direction);
		lookAt(location, yawRotationSpeed, pitchRotationSpeed);
	}

	@Override
	public Location randomLocation(final int horizontalOffset, final int heightOffset)
	{
		final Vec3D vec = RandomPositionGenerator.a(creature, horizontalOffset, heightOffset);
		return new Location(creature.getBukkitEntity().getWorld(), vec.c, vec.d, vec.e);
	}
}
