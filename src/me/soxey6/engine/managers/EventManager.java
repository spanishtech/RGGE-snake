package me.soxey6.engine.managers;

public class EventManager {
	private static EventManager eventManager;
	public EventManager()
	{
		this.eventManager=this;
	}
	//TODO: make event manager.
	public static EventManager getEventManager() {
		return eventManager;
	}
	public static void setEventManager(EventManager eventManager) {
		EventManager.eventManager = eventManager;
	}
}
