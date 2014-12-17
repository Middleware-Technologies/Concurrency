package EsempiVari;

import java.util.*;

public class PrinterSpooler 
{
    private int nextJobNum;
    private List<Job> jobs;
	
    public PrinterSpooler() 
	{
		nextJobNum = 1;
		jobs = new ArrayList<Job>();
		new Thread() 
		{
			public void run() {
			while(true)
				try {
					print();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
    }
    public synchronized int addJob(String doc) 
	{
		jobs.add(new Job(nextJobNum, doc));
		nextJobNum++;
		notify();
		return nextJobNum-1;
    }
    public synchronized void removeJob(int jobNum) 
	{
		Iterator<Job> it = jobs.iterator();
		while(it.hasNext()) 
		{
			if(it.next().getJobNum()==jobNum) 
			{
				it.remove();
				break;
			}
		}
    }
    private void print() throws InterruptedException 
	{
		// take the first job and print it or wait if no jobs in queue
		Job j;
		synchronized(this) {
			while(jobs.isEmpty()) wait();
			j = jobs.remove(0);
		}// print the job (long operation)
    }
    public synchronized List<Job> listAll() 
	{
		List<Job> j = new ArrayList<Job>(jobs);
		return j;
    }
}

class Job 
{
    private int jobNum;
    private String doc;

    public Job(int jobNum, String doc) 
	{
		this.jobNum = jobNum;
		this.doc = doc;
    }
    public int getJobNum() 
	{ 
		return jobNum; 
	}
    public String getDoc() 
	{ 
		return doc; 
	}
}