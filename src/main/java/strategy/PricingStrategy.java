package strategy;

import model.enums.ParkingSpaceType;

public interface PricingStrategy {
    Double getPrice(ParkingSpaceType parkingSpaceType);
}
