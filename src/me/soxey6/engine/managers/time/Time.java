package me.soxey6.engine.managers.time;

public class Time
{
	private long interval;
	private long lastRun;
	
	public Time(long interval)
	{
		this.interval=interval;
		this.lastRun=System.currentTimeMillis();
	}
	
	public Time(long interval, long lastRun)
	{
		this.interval=interval;
		this.lastRun=lastRun;
	}

	/**
	 * @return the interval
	 */
	public long getInterval()
	{
		return interval;
	}

	/**
	 * @return the lastRun
	 */
	public long getLastRun()
	{
		return lastRun;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(long interval)
	{
		this.interval = interval;
	}

	/**
	 * @param lastRun the lastRun to set
	 */
	public void setLastRun(long lastRun)
	{
		this.lastRun = lastRun;
	}
}
