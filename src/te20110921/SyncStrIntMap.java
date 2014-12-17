package te20110921;

import java.util.*;
public class SyncStrIntMap 
{
	private Map<String,Integer> map;

	public SyncStrIntMap(Map<String, Integer> map) 
	{
		super();
		this.map = map;
	}
	
	public synchronized int get(String key) throws InterruptedException
	{
		while(!this.map.containsKey(key))
		{
			System.out.println(Thread.currentThread().getName()+" KEY "+key+" NOT EXIXST - WAIT");
			this.wait();
		}
		int val= this.map.get(key);
		notifyAll();
		return val;
	}
	public synchronized void put(String key, int value) throws InterruptedException
	{
		while(this.map.containsKey(key))
		{
			System.out.println(Thread.currentThread().getName()+" KEY "+key+" EXIXST - WAIT");
			this.wait();
		}
		System.out.println(Thread.currentThread().getName()+" KEY "+key+" ADDED");
		this.map.put(key, value);
		notifyAll();
	}
	public synchronized void remove(String key) throws InterruptedException
	{
		while(!this.map.containsKey(key))
		{
			System.out.println(Thread.currentThread().getName()+" KEY "+key+" NOT EXIXST - WAIT");
			this.wait();
		}
		System.out.println(Thread.currentThread().getName()+" KEY "+key+" REMOVED");
		this.map.remove(key);
		notifyAll();
	}
	public synchronized boolean containsKey(String key)
	{
		return this.map.containsKey(key);
	}
	public synchronized int size()
	{
		return this.map.size();
	}
}