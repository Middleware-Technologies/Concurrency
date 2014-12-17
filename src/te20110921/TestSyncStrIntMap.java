package te20110921;

import java.util.*;

enum Operation {PUT, GET, SIZE, REMOVE,  CONTAINSKEY};

public class TestSyncStrIntMap 
{

	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		Map <String,Integer> mapTest=new HashMap<String,Integer>();
		
		SyncStrIntMap test = new SyncStrIntMap(mapTest);
		
		MyThread t1=new MyThread(test, Operation.REMOVE , "el1", "t1");
		MyThread t2=new MyThread(test, Operation.REMOVE , "el1", "t2");
		MyThread t3=new MyThread(test, Operation.GET , "el1", "t3");
		MyThread t4=new MyThread(test, Operation.PUT , "el1","t4");
		MyThread t5=new MyThread(test, Operation.SIZE , "el1","t5");
		MyThread t6=new MyThread(test, Operation.CONTAINSKEY , "el1","t6");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
	
	public static class MyThread extends Thread
	{
		private SyncStrIntMap myMap;
		private Operation op;
		private String key;
		public MyThread(SyncStrIntMap myMap,  Operation op, String key, String name)
		{
			super(name);
			this.myMap=myMap;
			this.op=op;
			this.key=key;
		}
		public void run()
		{
			try
			{
				switch (op) {
				case PUT: 
						this.myMap.put(key, key.hashCode()); break;
				case CONTAINSKEY:
						System.out.println(Thread.currentThread().getName()+ "KEY: "+this.key+ " PRESENT? "+ this.myMap.containsKey(key)); break;
				case GET:
						System.out.println(Thread.currentThread().getName()+ "KEY: "+this.key+" VALUE: "+ this.myMap.get(key));break;
				case SIZE:
					System.out.println(Thread.currentThread().getName()+ "SIZE: "+this.myMap.size());break;
				case REMOVE:
					this.myMap.remove(key);break;
				}
			}
			catch(Exception e)
			{
				
			}
			
		}
	}

}
