package model.account;

import lombok.Getter;
import model.enums.AccountStatus;

@Getter
public class Account {

    private String accountId;
    private String userName;
    private String password;
    private AccountStatus accountStatus;
}
