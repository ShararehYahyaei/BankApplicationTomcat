package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoggedInDto {
    private String username;
    private String password;
}
