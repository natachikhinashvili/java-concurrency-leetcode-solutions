package TrafficLightControlledIntersection;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Trafficlights{
    ArrayList<Integer> cars;
    ArrayList<Integer> directions;
    ArrayList<Integer> arrivalTimes;
    private int road = 1;


    public Trafficlights(ArrayList<Integer> cars, ArrayList<Integer> directions, ArrayList<Integer> arrivalTimes) {
        this.cars = cars;
        this.directions = directions;
        this.arrivalTimes = arrivalTimes;
    }

    /**
     * @throws InterruptedException
     * 
     */

    public void carArrived(int carId, int roadId, int direction, Runnable turnGreen, Runnable crossCar) throws InterruptedException{
        if(road != roadId){
            turnGreen.run();
            road = roadId;
        }
        crossCar.run();
    }
    public static void main(String[] args) throws InterruptedException{
    }
}