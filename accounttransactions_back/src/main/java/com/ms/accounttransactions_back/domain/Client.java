package com.ms.accounttransactions_back.domain;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * @author : Freddy Torres
 * file : Client
 * @since : 24/4/2025, jue
 **/

@Getter
@Setter
public class Client extends Person {

    @NotBlank(message = "{message.domain.client.email.NotBlank}")
    @Email(message = "{message.domain.client.email.Format}")
    private String email;

    @NotBlank(message = "{message.domain.client.password.NotBlank}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]{8,}$",
            message = "{message.domain.client.password.Pattern}")
    private String password;

}
