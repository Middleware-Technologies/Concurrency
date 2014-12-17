package EsempiVari;

/*
Implement in Java the PriorityMutex class. It looks like a pthread mutex but uses priorities to choose the thread to
wakeup upon unlocking by the current owner. It offers the following methods: lock(int priority) which acquires the
mutex unless held by another thread, unlock() which releases the mutex, bool tryLock(int priority) which returns true if
the mutex is already locked, otherwise acquires it and return false. The peculiarity of this mutex is that, upon
unlocking, it gives access to (one among) the highest priority waiting threads.
*/
import java.util.ConcurrentModificationException;

public class PriorityMutex 
{
    private boolean locked;
    private Thread currentLocker;
    private int numThreadPerPri[];
    
	public PriorityMutex() 
	{
		locked = false;
		currentLocker = null;
		numThreadPerPri = new int[10];
    }    
    public synchronized void lock(int priority) throws InterruptedException
	{
		numThreadPerPri[priority]++;
		while(locked || maxPri()>priority) {
			this.wait();
		}
		currentLocker = Thread.currentThread();
		locked = true;
		numThreadPerPri[priority]--;
    }
    public synchronized void unlock() throws ConcurrentModificationException 
	{
		if(Thread.currentThread()!=currentLocker) throw new ConcurrentModificationException();
		locked = false;
		this.notifyAll();
    }
    private int maxPri() 
	{
		int res=9;
		while(numThreadPerPri[res]==0) res--;
		return res;
    }
}