package me.soxey6.engine.objects;

import java.util.ArrayList;

public class Event
{
	private String name;
	private int priority;
	private ArrayList<Object> data;
	
	public Event(String name)
	{
		this.name=name;
		this.data=new ArrayList<Object>();
	}
	
	public Event(String name, int priority)
	{
		this.name=name;
		this.priority=priority;
		this.data=new ArrayList<Object>();
	}
	
	public void addData(Object object)
	{
		this.data.add(object);
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the priority
	 */
	public int getPriority()
	{
		return priority;
	}

	/**
	 * @return the data
	 */
	public ArrayList<Object> getData()
	{
		return data;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ArrayList<Object> data)
	{
		this.data = data;
	}
}
