package com.ms.accounttransactions_back.domain.validator;

import com.ms.accounttransactions_back.domain.enums.Gender;

/**
 * @author : Freddy Torres
 * file : Validator
 * @since : 26/4/2025, sáb
 **/

public interface Validator {
    void validateEmail(String email);
    void validatePassword(String password);
    void validateName(String name);
    void validateLastName(String lasName);
    void validateGender(Gender gender);
    void validateAge(Integer age);
    void validateAddress(String address);
    void validatePhone(String phone);
}
