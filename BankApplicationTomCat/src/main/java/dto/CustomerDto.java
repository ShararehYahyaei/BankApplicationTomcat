package dto;

import entity.AccountType;
import lombok.Data;

@Data
public class CustomerDto {
    private String fullName;
    private String lastName;
    private String email;
    private String phone;
    private AccountType accountType;
    private Long balance;
    private String accountNumber;
    private  String code;
    private boolean active = false;



}
