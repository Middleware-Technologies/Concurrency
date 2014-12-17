package EsempiVari;

public class ThreadEx2 
{
    private int data1;
    private int data2;
    private static int data3;
    
	public synchronized void setData(int d) 
	{
		data1 = data2 = data3 = d;
    }

    private class Inner 
	{
		public void setData1(int d) 
		{
			synchronized(ThreadEx2.this) {
			data1 = d;
			}
		}
    }
}
