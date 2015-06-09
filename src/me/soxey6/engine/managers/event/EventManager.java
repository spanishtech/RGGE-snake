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
	
	private HashMap<String, ArrayList<EventCallback>> hooks;
	private static EventManager eventManager;
	public EventManager()
	{
		eventManager=this;
		this.hooks=new HashMap<String, ArrayList<EventCallback>>();
	}

	/**
	 * Used for triggering certain events and all classes hooked with it.
	 * @param String eventName - The name of the event to trigger.
	 */
	public void trigger(String eventName)
	{
		eventName=eventName.toUpperCase();
		if(hooks.containsKey(eventName))
		{
			for(int i =0; i<=hooks.get(eventName).size()-1; i++)
			{
				hooks.get(eventName).get(i).callback(eventName);
			}
		}
	}
	
	/**
	 * Used for registering hooks
	 * @param String evenName - The name of the event to hook
	 * @param EventCallback callbackLocation - The interface to callback to.
	 */
	public void registerHook(String eventName, EventCallback callbackLocation)
	{
		eventName=eventName.toUpperCase();
		if(!hooks.containsKey(eventName))
		{
			ArrayList<EventCallback> tmpCallbackArray = new ArrayList<EventCallback>();
			tmpCallbackArray.add(callbackLocation);
			hooks.put(eventName, tmpCallbackArray);
		}else
			hooks.get(eventName).add(callbackLocation);
	}
	
	public void removeHook(String eventName, EventCallback callbackLocation)
	{
		eventName=eventName.toUpperCase();
		if(hooks.get(eventName).size()>0)
			hooks.get(eventName).remove(callbackLocation);
	}
	
	public static EventManager getEventManager() {
		return eventManager;
	}
	public static void setEventManager(EventManager eventManager) {
		EventManager.eventManager = eventManager;
	}
}
