package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserCreateDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        @NotBlank @Email String email
) {
}