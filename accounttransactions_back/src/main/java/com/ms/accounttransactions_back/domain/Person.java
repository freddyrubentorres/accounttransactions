package com.ms.accounttransactions_back.domain;


import com.ms.accounttransactions_back.domain.enums.Gender;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * @author : Freddy Torres
 * file : Person
 * @since : 24/4/2025, jue
 **/

@Data
public class Person {

    @NotNull(message = "{message.domain.person.name.NotNull}")
    @Size(max = 25, message = "{message.domain.person.name.Size}")
    private String name;

    @NotNull(message = "{message.domain.person.lastName.NotNull}")
    @Size(max = 25, message = "{message.domain.lastName.name.Size}")
    private String lastName;

    @NotNull(message = "{message.domain.person.gender.NotNull}")
    private Gender gender;

    @NotNull(message = "{message.domain.person.age.NotNull}")
    @Positive(message = "{message.domain.person.age.Positive}")
    @Min(value = 13, message = "{message.domain.person.age.Min}")
    private Integer age;

    @NotNull(message = "{message.domain.person.identification.NotNull}")
    @Pattern(regexp = "^\\d{10}$", message = "{message.domain.person.identification.Pattern}")
    private String identification;

    @NotBlank(message = "{message.domain.person.address.NotNull}")
    @Size(min = 10, max = 50, message = "{message.domain.person.address.Pattern}")
    private String address;

    @NotBlank(message = "{message.domain.person.phone.NotNull}")
    @Pattern(regexp = "^\\d{7}$|^\\d{10}$", message = "{message.domain.person.phone.Pattern}")
    private String phone;

    private Boolean status;
}
