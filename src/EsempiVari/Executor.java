package EsempiVari;

/*Implement in Java a class Executor, which encapsulates a pre-created set of 10 threads to execute jobs on behalf of the
 * callers. It provides a method: void execute(Runnable job) to submit a new job. The method must suspend the caller if all 10
 * threads are busy executing other jobs. Use only the base synchronization classes provided by Java.
 */

public class Executor 
{
	//The threads
    private Runner runner[];
	//The job
    private Runnable buf;
	
    public Executor() 
	{
		buf = null;
		runner = new Runner[10];
		for(int i=0; i<10; i++) 
		{
			runner[i] = new Runner();
			runner[i].start();
		}
    }
    public synchronized void execute(Runnable job) throws InterruptedException 
	{
		while(buf!=null) wait();
		buf = job;
		notifyAll();
	}
    private class Runner extends Thread 
	{
		public void run() 
		{
			Runnable myjob;
			while(true) 
			{
				synchronized(Executor.this) 
				{
					while(buf==null)
						try 
						{
							Executor.this.wait();
						} catch (InterruptedException e) {						}
					myjob = buf;
					buf=null;
					Executor.this.notifyAll();
				}
				myjob.run();
			}
		}
    }
}