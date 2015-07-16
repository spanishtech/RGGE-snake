package me.soxey6.engine.managers.event;
/**
 * This is an interface for handling event callbacks
 * @author spanish
 *
 */
public interface EventCallback
{
	boolean focused=false;
	void callback(String eventName);
}
