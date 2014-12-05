package ThreadMatrixProduct;
public class BufferMatrixes 
{
	private int a[][];
	private int b[][];
	private int result[][];
	
	public BufferMatrixes(int a[][], int b[][])
	{
		this.a=a;
		this.b=b;
		this.result=new int[this.a.length][this.b[0].length];
	}
	public int getColsOfA()
	{
		return this.a[0].length;
	}
	public int getFromA(int i, int j)
	{
		return this.a[i][j];
	}
	public int getFromB(int i, int j)
	{
		return this.b[i][j];
	}
	public void writeCell(int i, int j, int val)
	{
		this.result[i][j]=val;
	}
	public int[][] getResult()
	{
		return this.result;
	}
}