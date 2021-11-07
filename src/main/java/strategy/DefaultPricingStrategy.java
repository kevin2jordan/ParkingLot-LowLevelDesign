package strategy;

import model.enums.ParkingSpaceType;

import java.util.HashMap;
import java.util.Map;

public class DefaultPricingStrategy implements PricingStrategy{
    private Map<ParkingSpaceType, Double> hourlyCosts = new HashMap<>();

    public DefaultPricingStrategy() {
        hourlyCosts.put(ParkingSpaceType.CAR, 20.0);
        hourlyCosts.put(ParkingSpaceType.LARGE, 30.0);
        hourlyCosts.put(ParkingSpaceType.EBIKE, 25.0);
        hourlyCosts.put(ParkingSpaceType.BIKE, 10.0);
        hourlyCosts.put(ParkingSpaceType.ABLED, 25.0);
    }

    @Override
    public Double getPrice(ParkingSpaceType parkingSpaceType) {
        return hourlyCosts.get(parkingSpaceType);
    }
}
