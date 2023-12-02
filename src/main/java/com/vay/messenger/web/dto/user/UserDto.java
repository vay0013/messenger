package com.vay.messenger.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    @NotNull(message = "Id must be not null")
    private Long id;

    @Length(max = 255, message = "Username length must be smaller than 255 symbols")
    @NotNull(message = "Name must be not null")
    private String name;

    @Email
    @NotNull(message = "Email must be not null")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null")
    private String passwordConfirmation;
}
