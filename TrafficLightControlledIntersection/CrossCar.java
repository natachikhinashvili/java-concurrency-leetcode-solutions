package TrafficLightControlledIntersection;
import java.util.concurrent.Semaphore;

public class CrossCar implements Runnable{
    private static Semaphore semaphore;
    public int carId;
    public int roadId;
    public int direction;
    public static void main(String[] args){
        semaphore = new Semaphore(1);
    }    
    public CrossCar(int carId, int roadId, int direction) {
        this.carId = carId;
        this.roadId = roadId;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(1000);
            semaphore.release();
            System.out.println("Car " + carId + " Has Passed Road " + roadId + " In Direction " + direction);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
