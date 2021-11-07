package service;

import exception.UserAlreadyExistException;
import exception.UserNotFoundException;
import lombok.NonNull;
import model.account.Admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AdminService {

    private static Map<String, Admin> adminMap = new HashMap<>();

    public void addAdmin(@NonNull final Admin admin) {
        if(adminMap.containsKey(admin.getAccountId())){
            throw new UserAlreadyExistException("Admin already exist");
        }
        adminMap.put(admin.getAccountId(), admin);
    }

    public Admin getAdminByEmail(@NonNull final String email) {
       Optional<Admin> admin = adminMap.values()
               .stream().
               filter(admin1 -> admin1.getEmail().equalsIgnoreCase(email))
               .findFirst();
       if(admin.isPresent()){
           return admin.get();
       } else {
           throw new UserNotFoundException("Search with valid email.");
       }
    }
}
