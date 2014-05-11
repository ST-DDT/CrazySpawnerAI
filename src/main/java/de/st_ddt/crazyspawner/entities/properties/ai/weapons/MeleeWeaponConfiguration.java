package de.st_ddt.crazyspawner.entities.properties.ai.weapons;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;


public class MeleeWeaponConfiguration implements WeaponConfiguration
{
	
	protected final ItemStack item;

	@Override
	public ItemStack getItemInHand()
	{
		
		return item;
	}

	@Override
	public double getMinimumRange()
	{
		
		return 0;
	}

	@Override
	public double getPreferredRange()
	{
		
		return 1;
	}

	@Override
	public double getMaximumRange()
	{
		
		return 2;
	}

	@Override
	public Entity attack(LivingEntity entity, boolean force)
	{
		
		return null;
	}
}
