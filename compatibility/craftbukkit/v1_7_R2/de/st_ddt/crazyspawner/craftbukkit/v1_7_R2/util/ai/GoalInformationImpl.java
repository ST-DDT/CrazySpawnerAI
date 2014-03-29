package de.st_ddt.crazyspawner.craftbukkit.v1_7_R2.util.ai;

import net.minecraft.server.v1_7_R2.PathfinderGoal;
import de.st_ddt.crazyspawner.entities.util.ai.BasicGoalInformation;

public final class GoalInformationImpl extends BasicGoalInformation<PathfinderGoal>
{

	public GoalInformationImpl(final PathfinderGoal pathfinderGoal, final int priority)
	{
		super(pathfinderGoal, priority);
	}
}
