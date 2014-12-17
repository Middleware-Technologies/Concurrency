package EsempiVari;

public class PriorityBuffer 
{
    private int data;
    private boolean empty;
    private int numReaders;

    public PriorityBuffer() 
	{
		empty = true;
		numReaders=0;
    }
    public synchronized void put(int data) throws InterruptedException 
	{
		while(!empty) wait();
		this.data = data;
		empty = false;
		notifyAll();
    }
    public synchronized int get() throws InterruptedException 
	{
		int d;
		while(numReaders>0 || empty) wait();
		d = data;
		empty = true;
		notifyAll();
		return d;
    }
    public synchronized int read() throws InterruptedException 
	{
		numReaders++;
		while(empty) wait();
		numReaders--;
		notifyAll();
		return data;
    }
}