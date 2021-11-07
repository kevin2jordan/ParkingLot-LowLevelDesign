import model.*;
import model.ParkingLot;
import model.account.Admin;
import model.enums.ParkingSpaceType;
import model.enums.VehicleType;
import model.parkingspot.BikeParkingSpace;
import model.parkingspot.CarParkingSpace;
import model.parkingspot.ParkingSpace;
import model.vehicle.Bike;
import model.vehicle.Car;
import model.vehicle.Van;
import model.vehicle.Vehicle;

public class ParkingLotApplication {

    public static void main(String[] args) throws RuntimeException {

        //Admin tests
        Admin adminAccount = new Admin();
        ParkingLot parkingLot = ParkingLot.INSTANCE;
        String parkingLotId = parkingLot.getParkingLotId();
        Address address = Address.builder().street("BG Road")
                .city("Bangalore")
                .state("Karnataka")
                .country("India")
                .zipCode("560075")
                .build();

        parkingLot.setAddress(address);

        adminAccount.getParkingLotService().addParkingLot(parkingLot);

        /**                   Admin Usecase      **/

        // Case 1 - should be able to add parking floor case
        adminAccount.getParkingLotService().addParkingFloorToParkingLot(parkingLotId, new ParkingFloor("1"));

        // Case 2 - should be able to add parking floor case
        adminAccount.getParkingLotService().addParkingFloorToParkingLot(parkingLotId, new ParkingFloor("2"));

        // Case 3 - should be able to add entrance panel
        EntryGate entryGate = new EntryGate("1");
        adminAccount.getParkingLotService().addEntryGateToAParkingLot(parkingLotId, entryGate);

        // Case 4 - should be able to add exit panel
        ExitGate exitGate = new ExitGate("1");
        adminAccount.getParkingLotService().addExitGateToAParkingLot(parkingLotId, exitGate);

        String floorId = parkingLot.getParkingFloors().get(0).getParkingFloorId();

        // case 5 - should be able to add car parking spot
        ParkingSpace carParkingSpace1 = new CarParkingSpace("c1");
        adminAccount.getParkingLotService().addParkingSpaceToParkingFloorOfaParkingLot(parkingLotId, floorId, carParkingSpace1);

        // case 6 - should be able to add bike parking spot
        ParkingSpace bikeParkingSpace = new BikeParkingSpace("b1");
        adminAccount.getParkingLotService().addParkingSpaceToParkingFloorOfaParkingLot(parkingLotId, floorId, bikeParkingSpace);

        // case 7 - should be able to add car parking spot
        ParkingSpace carParkingSpace2 = new CarParkingSpace("c2");
        adminAccount.getParkingLotService().addParkingSpaceToParkingFloorOfaParkingLot(parkingLotId, floorId, carParkingSpace2);

        /**       End of Admin usecase **/

                /** Other usecases   **/
        // case 1 - check for availability of parking lot - TRUE
        System.out.println("Test case 1 " + ParkingLot.INSTANCE.canPark(VehicleType.CAR));

        // case 2 - check for availability of parking lot - FALSE
        System.out.println("Test case 2 " + ParkingLot.INSTANCE.canPark(VehicleType.EBIKE));

        // case 3 - check for availability of parking lot - FALSE
        System.out.println("Test case 3 " + ParkingLot.INSTANCE.canPark(VehicleType.VAN));

        // case 4 - Check if parkinglot is full
        System.out.println("Test case 4 " + ParkingLot.INSTANCE.isFull());

        // case 5 - get parking spot
        Vehicle vehicle = new Car("KA05MR2311");
        ParkingSpace availableSpot = ParkingLot.INSTANCE.getParkingSpace(vehicle.getVehicleType());
        System.out.println("Test case 5 " + availableSpot.getParkingSpaceType());
        System.out.println("Test case 5 " + availableSpot.getParkingSpaceId());

        // case 6 - should not be able to get spot
        Vehicle van = new Van("KA01MR7804");
        ParkingSpace vanSpot = ParkingLot.INSTANCE.getParkingSpace(van.getVehicleType());
        System.out.println("Test case 6 " + (null == vanSpot));

        // case 7 - Entrance Panel - 1
        System.out.println("Test case 7 " + ParkingLot.INSTANCE.getEntryGates().size());

        //  case - 8 - Should be able to get parking ticket
        ParkingTicket carParkingTicket = entryGate.getParkingTicket(vehicle);
        System.out.println("Test case 8 " + carParkingTicket.getParkingSpaceId());

        adminAccount.getParkingLotService().addParkingSpaceToParkingFloorOfaParkingLot(parkingLotId, floorId, carParkingSpace1);
        // case - 9 - Should be able to get parking ticket
        Vehicle car = new Car("KA02MR6355");
        ParkingTicket parkingTicket1 = entryGate.getParkingTicket(car);
        System.out.println("Test case 9 " + parkingTicket1.getParkingTicketId());

        // case 10 - Should not be able to get ticket
        try {
            ParkingTicket tkt = entryGate.getParkingTicket(new Car("ka04rb8458"));
            System.out.println("Test case 10 " + (null == tkt));
        }catch (Exception e) {
            System.out.println("Test case `10 " + e.getMessage());
        }


        // case 11 - Should be able to get ticket
        ParkingTicket biketTicket = entryGate.getParkingTicket(new Bike("ka01ee4901"));
        System.out.println("Test case 11 " + biketTicket.getParkingSpaceId());

        // case 12 - vacate parking spot
        biketTicket = exitGate.scanAndVacateParkingLot(biketTicket);
        System.out.println("Test case 12 " + biketTicket.getTotalCost());
        System.out.println("Test case 12 " + (biketTicket.getTotalCost() > 0));

        //  case 13 - park on vacated spot
        ParkingTicket biketTicket1 = entryGate.getParkingTicket(new Bike("ka01ee7791"));
        System.out.println("Test case 13 " + biketTicket1.getParkingSpaceId());

        // case 14 - park when spot is not available
        try {
            ParkingTicket unavaialbeBikeTicket =
                    entryGate.getParkingTicket(new Bike("ka01ee4455"));
            System.out.println("Test case 14 " + (null == unavaialbeBikeTicket));
        } catch (Exception e){
            System.out.println("Test case 14 " + e.getMessage());
        }

        // cast 15 - vacate car
        carParkingTicket = exitGate.scanAndVacateParkingLot(carParkingTicket);
        System.out.println("Test case 15 " + carParkingTicket.getTotalCost());
        System.out.println("Test case 15 " + (carParkingTicket.getTotalCost() > 0));

        // case 16 - Now should be able to park car
        System.out.println("Test case 16 " + ParkingLot.INSTANCE.canPark(VehicleType.CAR));

        // case 17 - Should be able to vacate parked vehicle
        parkingTicket1 = exitGate.scanAndVacateParkingLot(parkingTicket1);
        System.out.println("Test case 17 " + parkingTicket1.getTotalCost());
        System.out.println("Test case 17 " + (parkingTicket1.getTotalCost() > 0));

        // case 18 - check for slots count
        System.out.println("Test case 18 " + ParkingLot.INSTANCE.getParkingFloors()
                .get(0).getParkingSpaces().get(ParkingSpaceType.CAR).size());

        // case 19 - Payment
        Payment payment = new Payment(parkingTicket1.getParkingTicketId(), parkingTicket1.getTotalCost());
        payment.makePayment();
        System.out.println("Test case 19 " + payment.getPaymentStatus());

        //case 20 - vacate motorbike spot
        biketTicket = exitGate.scanAndVacateParkingLot(biketTicket);
        System.out.println("Test case 20 " + ParkingLot.INSTANCE.getParkingFloors()
                .get(0).getParkingSpaces().get(ParkingSpaceType.BIKE).size());
        System.out.println("Test case 20 " + biketTicket.getTotalCost());
    }
}
