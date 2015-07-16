package me.soxey6.engine.managers.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
	 * @param eventName - The name of the event to trigger.
	 */
	public void trigger(String eventName)
	{
		eventName=eventName.toUpperCase();
		if(hooks.containsKey(eventName)&&!hooks.get(eventName).isEmpty())
		{
			for(int i =0; i<=hooks.get(eventName).size()-1; i++)
			{
				if(hooks.get(eventName).get(i)!=null)
					hooks.get(eventName).get(i).callback(eventName);
			}
		}
	}
	
	/**
	 * Used for registering hooks
	 * @param evenName - The name of the event to hook
	 * @param callbackLocation - The interface to callback to.
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
	
	/**
	 * Removes the hooks from an eventName and callbackLocation
	 * @param eventName The name of the event
	 * @param callbackLocation The location to callback
	 */
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
	
	/**
	 * Removes all hooks associated with this callbackLocation
	 * @param callback The callbackLocation
	 */
	public void removeHooks(EventCallback callback)
	{
		Set<String> keyset = hooks.keySet();
		ArrayList<String> toRemove = new ArrayList<String>();
	    for(String s : keyset)
	    	if(!hooks.get(s).isEmpty())
		        for(int i =0; i<=hooks.get(s).size()-1; i++)
				{
						hooks.get(s).remove(callback);
				}
	    	else
	    		toRemove.add(s);
	    for(String s : toRemove)
	    	hooks.remove(s);
	    	
	}
}
