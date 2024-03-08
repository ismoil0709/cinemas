package uz.pdp.cinemas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    @NotNull @Email
    private String email;
    @NotNull
    private String password;
}
