package de.st_ddt.crazyspawner.craftbukkit.v1_7_R1.ai;

import de.st_ddt.crazyspawner.craftbukkit.v1_7_R1.util.ai.ActionHelperImpl;
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
