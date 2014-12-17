package EsempiVari;

public class Buffer 
{
	private int data;
	private Boolean empty;

	public Buffer() 
	{
		empty = true;
		data = 0;
	}
	public synchronized void put(int data) throws InterruptedException 
	{
		while(!empty) 
			this.wait(); 
		notifyAll();
		this.data = data;
		empty = false;
	}

	public synchronized int get() throws InterruptedException 
	{
		while(empty) this.wait(); 
		notifyAll();
		empty = true;
		return data;
	}
	public synchronized int read() throws InterruptedException 
	{
		while(empty) 
			this.wait(); 
		return data;
	}
}
