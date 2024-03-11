package uz.pdp.cinemas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    @NotNull
    private Long id;
    @NotBlank @Email
    private String email;
    @NotNull @Pattern(regexp = "")
    private String password;
    private String confirmPassword;
}
