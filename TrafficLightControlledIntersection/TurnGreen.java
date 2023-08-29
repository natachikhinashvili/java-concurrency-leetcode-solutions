package TrafficLightControlledIntersection;
public class TurnGreen implements Runnable {
    public static void main(String[] args){
        
    }

    @Override
    public void run() {
            Thread mainThread = Thread.currentThread();
            String threadName = mainThread.getName();
            System.out.println("Traffic Light On Road " + threadName + " Is Green");
    }
    
}
