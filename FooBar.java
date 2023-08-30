import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    boolean turn = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {        
    /* we use lock to acquire a lock on following section of code, if you dont use it you will get "illegal monitor state exception"
    * since without acquiring reentrantlock, the await() and signal() would not be used in within a block that is synchronized on the same lock.
    */
        lock.lock();
        try{
            for (int i = 0; i < n; i++) {
                // following code waits while its bar's turn
                while(!turn){
                    condition.await();               
                }
                // following code runs runnable, sets turn to be false and signals condition so now it would be bar's turn
                    printFoo.run();
                    turn = false;
                    condition.signal();        
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        lock.lock();
        try{
            for (int i = 0; i < n; i++) {
                while(turn){
                    condition.await();     
                }          
                    printBar.run();
                    turn = true;               
                    condition.signal();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}