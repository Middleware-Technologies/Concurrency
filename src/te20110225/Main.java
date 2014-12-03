package te20110225;

public class Main 
{
	public static void main(String[] args){
		Executor ex = new Executor();
		ex.start();
		ex.execute(new Job(1), 10000);
		ex.execute(new Job(2), 1000);		
		ex.execute(new Job(3), 3000);		
		ex.execute(new Job(4), 6000);		
	}
	
	private static class Job implements Runnable 
	{
		private int id;
		
		public Job(int id){
			this.id = id;
		}
		@Override
		public void run() {
			System.out.println("Sono il job: "+id);
		}		
	}
}