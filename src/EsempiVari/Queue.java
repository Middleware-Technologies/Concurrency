package EsempiVari;

import java.util.*;

public class Queue 
{
    private int data[];
    private int lastElem;
    private int firstElem;
    private int processPending;

    public Queue(int size) 
	{
		data = new int[size];
		lastElem = 0;
		firstElem = 0;
		processPending = 0;
    }
    public synchronized void put(int d)  throws InterruptedException 
	{
		while((lastElem+1)%data.length==firstElem) 
		{
			this.wait();
		}
		data[lastElem]=d;
		lastElem++;
		this.notifyAll();
    }
    public synchronized int get() throws InterruptedException 
	{
		int d;
		while(lastElem==firstElem || processPending>0) 
		{
			this.wait();
		}
		d = data[firstElem];
		firstElem++;
		this.notifyAll();
		return d;
    }
    public synchronized void process()  throws InterruptedException 
	{
		processPending++;
		while(lastElem==firstElem) 
		{
			this.wait();
		}
		processPending--;
		// access data without changing it
    }
}