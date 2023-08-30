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
        lock.lock();
        try{
            for (int i = 0; i < n; i++) {
                while(!turn){
                    condition.await();               
                }
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