package de.st_ddt.crazyutil.compatibility;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class CompatibilityLoader
{

	private final static String implementation;
	private final static String version;
	static
	{
		final Server server = Bukkit.getServer();
		final Class<? extends Server> serverClass = server.getClass();
		final String serverClassName = serverClass.getName();
		final String[] serverClassNameParts = serverClassName.split("\\.");
		if (serverClassNameParts.length >= 5 && serverClassNameParts[2].equals("craftbukkit"))
		{
			implementation = "craftbukkit";
			version = serverClassNameParts[3];
			System.out.println("[CrazyCompatibility] Implementation: " + implementation + "@" + version + " found.");
		}
		else
		{
			implementation = null;
			version = null;
			System.err.println("[CrazyCompatibility] No compatible implementation detected for class: " + serverClassName);
		}
	}

	public static boolean loadCompatibilityProvider(final Plugin plugin, final String packagePrefix, final String packageSuffix)
	{
		if (implementation == null || version == null)
			return false;
		try
		{
			Class.forName(packagePrefix + implementation + "." + version + packageSuffix + ".CompatibilityProvider", true, plugin.getClass().getClassLoader());
			System.err.println("[" + plugin.getName() + "] Loaded compatibility provider.");
			return true;
		}
		catch (final Throwable t)
		{
			System.err.println("[" + plugin.getName() + "] Error loading compatibility provider!");
			System.err.println(t.getMessage());
			return false;
		}
	}

	private CompatibilityLoader()
	{
	}
}
