package te20110225;
import java.util.TreeMap;


public class Executor {

	private TreeMap<Long, Runnable> jobs;
	
	public Executor(){
		jobs = new TreeMap<>();
	}
	
	public void start(){
		new Consumer().start();
	}

	public synchronized void execute(Runnable job, long delay){
		jobs.put(System.currentTimeMillis()+delay, job);
		notifyAll();
	}

	private class Consumer extends Thread {

		public void run() {
			while(true){
				synchronized(Executor.this){
					while(jobs.isEmpty()) {
						try {
							Executor.this.wait();
						} catch (InterruptedException e) {
						}					
					}
					if(jobs.firstKey()<=System.currentTimeMillis()){
						System.out.println("Sono il consumer, eseguo qualcosa.");
						((Runnable)jobs.firstEntry().getValue()).run();
						jobs.remove(jobs.firstKey());
					} else {
						try {
							Executor.this.wait(jobs.firstKey()-System.currentTimeMillis());
						} catch (InterruptedException e) {
						}
					}
				}

			}
		}
	}


}
