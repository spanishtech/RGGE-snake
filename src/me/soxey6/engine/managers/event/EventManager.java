package me.soxey6.engine.managers.event;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used for dispatching, hooking and managing events.
 * It is effective at creating efficient and optimized calls to other classes and objects.
 * @author pchilds
 *
 */
public class EventManager {
	
	private HashMap<String,ArrayList<EventCallback>> hooks;
	private static EventManager eventManager;
	public EventManager()
	{
		eventManager=this;
		this.hooks=new HashMap<String,ArrayList<EventCallback>>();
	}

	
	public void trigger(String eventName)
	{
		if(hooks.containsKey(eventName))
		{
			for(EventCallback eventCallback:hooks.get(eventName))
			{
				eventCallback.callback(eventName);
			}
		}
	}
	
	public void registerHook(String eventName, EventCallback callbackLocation)
	{
		if(!hooks.containsKey(eventName))
		{
			ArrayList<EventCallback> tmpCallbackArray = new ArrayList<EventCallback>();
			tmpCallbackArray.add(callbackLocation);
			hooks.put(eventName, tmpCallbackArray);
		}else
			hooks.get(eventName).add(callbackLocation);
	}
	
	//TODO: make event manager.
	public static EventManager getEventManager() {
		return eventManager;
	}
	public static void setEventManager(EventManager eventManager) {
		EventManager.eventManager = eventManager;
	}
}
