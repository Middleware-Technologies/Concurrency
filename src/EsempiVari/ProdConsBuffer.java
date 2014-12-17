package EsempiVari;

public class ProdConsBuffer 
{
    private int data;
	
	public ProdConsBuffer() 
	{
		data = -1;
    }
    public synchronized void produce(int data) throws InterruptedException 
	{
		while(data>=0) { // buffer is full
			this.wait();
		}
		this.data = data;
		this.notifyAll();
    }
    public synchronized int consume() throws InterruptedException 
	{
		int d;
		while(data<0) 
		{ // buffer is empty
			this.wait();
		}
		d = data;
		data = -1;
		this.notifyAll();
		return d;
    }
}