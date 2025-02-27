package dto;

import entity.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private String fullName;
    private String lastName;
    private String email;
    private String phone;
    private AccountType accountType;
    private Long balance;
    private String accountNumber;
    private String code;
    private String userName;
    private String password;
    private String customerNumber;


}
