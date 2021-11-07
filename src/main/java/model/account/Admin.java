package model.account;

import lombok.Getter;
import lombok.Setter;
import service.AdminService;
import service.ParkingLotService;

@Getter
@Setter
public class Admin extends Account{

    private String name;
    private String email;
    private String phoneNumber;
    private Address addres;

    ParkingLotService parkingLotService;

    public Admin() {
        parkingLotService = new ParkingLotService();
    }


}
