package de.st_ddt.crazyspawner.entities.properties.ai.weapons;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public interface WeaponConfiguration
{

	public ItemStack getItemInHand();

	public double getMinimumRange();

	public double getPreferredRange();

	public double getMaximumRange();

	public Entity attack(LivingEntity entity, boolean force);
}
