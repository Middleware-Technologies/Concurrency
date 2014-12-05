package ThreadMatrixProduct;
/*
 * Implementare un sistema di prodotto matrici, in cui ogni thread
 * è responsabile di moltiplicare una specifica riga per le colonne
 * della seconda.
 */
public class MainClass 
{

	public static void main(String[] args) throws InterruptedException 
	{
		int a[][]={{1,2,3,4},
				   {1,2,3,4}};
		
		int b[][]={{1,2,3,4},
				   {1,2,3,4},
				   {1,2,3,4},
				   {1,2,3,4} };
		
		BufferMatrixes buf=new BufferMatrixes(a, b);
		Producter p []= new Producter[a.length];
		for(int i=0;i<a.length;i++)
		{
			p[i]=new Producter(buf, i);
			p[i].start();
		}
		
		for(int i=0;i<a.length;i++)
		{
			p[i].join();
		}
		
		int res[][]=buf.getResult();
		System.out.println("###RESULT#####");
		for(int i=0;i<res.length;i++)
		{
			for(int j=0;j<res[0].length;j++)
			{
				System.out.print(""+res[i][j]+"  ");
			}
			System.out.println("");
		}
	}
	
	public static class Producter extends Thread
	{
		private BufferMatrixes buffer;
		private int row;
		
		public Producter(BufferMatrixes b, int row)
		{
			this.buffer=b;
			this.row=row;
		}
		public void run()
		{
			int colA=this.buffer.getColsOfA();

			for(int i=0;i<colA;i++)
			{
				int val=0;
				for(int j=0;j<colA;j++)
				{
					val+=this.buffer.getFromA(row,j)*this.buffer.getFromB(j,i);
				}
				System.out.println("THREAD "+this.row+" WRITE ("+row+","+i+")="+ val);
				this.buffer.writeCell(row, i, val);
			}
		}
	}
}