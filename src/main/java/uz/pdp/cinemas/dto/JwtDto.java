package uz.pdp.cinemas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class JwtDto {
    @NotNull
    private final String accessToken;
    private final LocalDateTime timestamp;
    public JwtDto(String accessToken){
        this.accessToken = accessToken;
        this.timestamp = LocalDateTime.now();
    }
}
