import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class L853CarFleet {
    //Intuition: while at least one car is not at target continue iterating, for each iteration, 
    public int carFleetBruteForce(int target, int[] position, int[] speed) {
        Map<Integer, Fleet> fleetsByPosition = new HashMap();
        //Initate fleets:
        for (int i = 0; i < position.length; i++) {
            Fleet currentFleet = fleetsByPosition.get(position[i]);
            if (currentFleet == null) {
                Fleet fleet = new Fleet(new ArrayList<>(), speed[i]);
                fleet.cars.add(i);
            } else {
                currentFleet.cars.add(i);
                currentFleet.speed = Math.min(speed[i], currentFleet.speed);
            }
        }
        int fleetCount = fleetsByPosition.get(target) == null ? 0 : 1;
        Fleet arrived = fleetsByPosition.get(target);
        while (arrived == null || arrived.cars.size() < position.length) {
            for (Entry<Integer, Fleet> entry : fleetsByPosition.entrySet()) {
                int nextPosition = entry.getKey() + entry.getValue().speed;
                // nextPosition
                // if(next)
            }
        }
        return 0;

    }
}

class Fleet {
    public List<Integer> cars;
    public int speed;

    public Fleet() {
    }

    public Fleet(List<Integer> cars, int speed) {
        this.cars = cars;
        this.speed = speed;
    }
    
}