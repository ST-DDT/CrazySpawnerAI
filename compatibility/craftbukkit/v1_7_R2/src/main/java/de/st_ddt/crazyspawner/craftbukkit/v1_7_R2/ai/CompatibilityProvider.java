package de.st_ddt.crazyspawner.craftbukkit.v1_7_R2.ai;

import de.st_ddt.crazyspawner.craftbukkit.v1_7_R2.entities.util.ai.ActionHelperImpl;
import de.st_ddt.crazyspawner.entities.util.ai.ActionHelperInterface;

public class CompatibilityProvider
{

	static
	{
		ActionHelperInterface.ACTIONHELPERCLASSES.add(ActionHelperImpl.class);
	}

	private CompatibilityProvider()
	{
	}
}
