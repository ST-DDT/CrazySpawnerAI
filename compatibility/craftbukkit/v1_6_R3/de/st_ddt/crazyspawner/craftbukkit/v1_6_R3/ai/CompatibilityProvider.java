package de.st_ddt.crazyspawner.craftbukkit.v1_6_R3.ai;

import de.st_ddt.crazyspawner.craftbukkit.v1_6_R3.entities.util.ai.ActionHelperImpl;
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
