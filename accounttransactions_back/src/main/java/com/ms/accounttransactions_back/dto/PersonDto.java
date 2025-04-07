package com.ms.accounttransactions_back.dto;

import com.ms.accounttransactions_back.model.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import jakarta.validation.constraints.*;

/**
 * @author : Freddy Torres
 * file :  PersonDto
 * @since : 3/4/2025, jue
 **/

@Setter
@Getter
@ToString
public class PersonDto {
    private Long personId;

    @NotNull(message = "{message.dto.ClientDto.name.NotNull}")
    @Size(max = 25, message = "{message.dto.ClientDto.name.Size}")
    private String name;

    @NotNull(message = "{message.dto.ClientDto.lastName.NotNull}")
    @Size(max = 25, message = "{message.dto.ClientDto.lastName.Size}")
    private String last_name;

    @NotNull(message = "{message.dto.ClientDto.gender.NotNull}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "{message.dto.ClientDto.age.NotNull}")
    @Positive(message = "{message.dto.ClientDto.age.Positive}")
    @Min(value = 13, message = "{message.dto.ClientDto.age.Min}")
    private Long age;

    @NotNull(message = "{message.dto.ClientDto.identification.NotNull}")
    @Pattern(regexp = "^[0-9]{10}$", message = "{message.dto.ClientDto.identification.Pattern}")
    private String identification;

    @NotBlank(message = "{message.dto.ClientDto.address.NotNull}")
    @Size(min = 10, max = 50, message = "{message.dto.ClientDto.address.Pattern}")
    private String address;

    @NotBlank(message = "{message.dto.ClientDto.phone.NotNull}")
    @Pattern(regexp = "^[0-9]{7}$|^[0-9]{10}$", message = "{message.dto.ClientDto.phone.Pattern}")
    private String phone;
}
