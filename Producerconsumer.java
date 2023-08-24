import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producerconsumer<Item> {
    private Queue<Item> queue;
    private Lock lock = new ReentrantLock();
    private Condition notfull = lock.newCondition();
    private Condition notempty = lock.newCondition();
    private int max = 10;

    public void myBlockQueue(int size) {
    	queue =  new LinkedList<>();
    	max = 10;
    }
    public void put(Item item) throws InterruptedException {
    	lock.lock();
    	try {
    		if(queue.size() == max) {
    			notfull.await();
    		}
        	queue.add(item);
        	notempty.signalAll();
    	} finally {
        	lock.unlock();
    	}
    }
    
	public Item take() throws InterruptedException {
		lock.lock();
		try {
			while(queue.size() == 0) {
				notempty.await();
			}
			Item removed = queue.remove();
			notfull.signalAll();
			return removed;
    	} finally {
        	lock.unlock();
    	}
	}
}
