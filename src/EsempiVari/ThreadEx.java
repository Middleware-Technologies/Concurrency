package EsempiVari;

public class ThreadEx 
{
    public void foo(String msg) 
	{
		try {
			Thread.sleep(1000);
			System.out.println("Foo called: "+msg);
			Thread.sleep(1000);
		} catch(Exception ex) {ex.printStackTrace();}
    }
    public void bar() 
	{
		// call foo in parallel w.r.t. bar
		new Thread() 
		{
			public void run() {
			foo("Ciao");
			}
		}.start();
    }
    public void barDaemon() 
	{
		// call foo in parallel w.r.t. bar
		Thread t = new Thread() 
		{
			public void run() {
			foo("Ciao");
			}
		};
		t.setDaemon(true);
		t.start();
    }
    public static void main(String args[]) 
	{
		ThreadEx t = new ThreadEx();
		t.barDaemon();
		System.out.println("Finishing");
    }
}
