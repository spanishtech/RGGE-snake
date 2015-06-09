package me.soxey6.engine.managers.time;

import java.util.ArrayList;

import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.event.EventManager;

public class Timer
{
	private static Timer timer;
	private ArrayList<Time> times;
	
	public Timer()
	{
		timer=this;
		this.times = new ArrayList<Time>();
	}
	
	public void tick()
	{
		// Iterate through every time
		for(Time time : times)
		{
			// If the current time is greater then the interval + lastRun
			if(System.currentTimeMillis()>=time.getInterval()+time.getLastRun())
			{
				// Trigger the event and reset the lastRun
				EventManager.getEventManager().trigger("TIMER_"+time.getInterval());
				time.setLastRun(System.currentTimeMillis());
			}
		}
	}
	
	public void newTimer(Time time, EventCallback callback)
	{
		// Create a new timer and register the event
		EventManager.getEventManager().registerHook("TIMER_"+time.getInterval(), callback);
		for(Time curTime:getTimes())
		{
			if(curTime.getInterval()==time.getInterval())
				return;
		}
		getTimes().add(time);
	}

	/**
	 * @return the timer
	 */
	public static Timer getTimer()
	{
		return timer;
	}

	/**
	 * @return the times
	 */
	public ArrayList<Time> getTimes()
	{
		return times;
	}

	/**
	 * @param timer the timer to set
	 */
	public static void setTimer(Timer timer)
	{
		Timer.timer = timer;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(ArrayList<Time> times)
	{
		this.times = times;
	}
}
