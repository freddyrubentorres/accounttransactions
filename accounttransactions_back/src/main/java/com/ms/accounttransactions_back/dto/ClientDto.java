package com.ms.accounttransactions_back.dto;

import com.ms.accounttransactions_back.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import jakarta.validation.constraints.*;

/**
 * @author : Freddy Torres
 * file :  ClientDto
 * @since : 3/4/2025, jue
 **/

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto extends PersonDto {

    private Long clientId;

    @NotBlank(message = "{message.dto.ClientDto.email.NotBlank}")
    @Email(message = "{message.dto.ClientDto.email.Format}")
    private String email;

    @NotBlank(message = "{message.dto.ClientDto.password.NotBlank}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]{8,}$",
            message = "{message.dto.ClientDto.password.Pattern}")
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;
}