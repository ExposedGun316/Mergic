package no.runsafe.mergic;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.event.plugin.IPluginDisabled;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ControlledEntityCleaner implements IPluginDisabled
{
	public ControlledEntityCleaner(IServer server)
	{
		this.server = server;
	}

	@Override
	public void OnPluginDisabled()
	{
		for (Map.Entry<String, List<Integer>> node : entities.entrySet())
		{
			IWorld world = server.getWorld(node.getKey());
			if (world != null)
			{
				for (Integer entityID : node.getValue())
				{
					IEntity entity = world.getEntityById(entityID);
					if (entity != null)
						entity.remove(); // Remove entity from the world.
				}
			}
		}
	}

	private final IServer server;

	public static void registerEntity(IEntity entity)
	{
		IWorld world = entity.getWorld();
		if (world != null)
		{
			String worldName = world.getName();
			if (!entities.containsKey(worldName)) // Check if we have a container for this world.
				entities.put(worldName, new ArrayList<Integer>()); // Create one if not.

			entities.get(worldName).add(entity.getEntityId()); // Add the entity ID to the world object.
		}
	}

	public static void unregisterEntity(IEntity entity)
	{
		IWorld world = entity.getWorld();
		if (world != null)
		{
			String worldName = world.getName();
			if (entities.containsKey(worldName)) // Check if we have a container for this world.
				entities.get(worldName).remove((Object) entity.getEntityId()); // Remove the entity.
		}
	}

	private static ConcurrentHashMap<String, List<Integer>> entities = new ConcurrentHashMap<String, List<Integer>>();
}
