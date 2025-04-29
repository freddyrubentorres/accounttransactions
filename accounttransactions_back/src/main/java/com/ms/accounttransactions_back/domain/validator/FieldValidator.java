package com.ms.accounttransactions_back.domain.validator;

import com.ms.accounttransactions_back.domain.enums.Gender;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author : Freddy Torres
 * file : FieldValidator
 * @since : 26/4/2025, sáb
 **/

@Component
public class FieldValidator implements Validator {
    @Override
    public void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email es requerido");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Email debe ser valido");
        }
    }

    @Override
    public void validatePassword(String inputPassword) {
        if (inputPassword == null || inputPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password es requerido");
        }
        String validationPattern = "^(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]{8,}$";
        if (!Pattern.matches(validationPattern, inputPassword)) {
            throw new IllegalArgumentException("Password debe tener al menos 8 caracteres, contener un numero y un caracter especial");
        }
    }

    @Override
    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
        if (name.length() > 25) {
            throw new IllegalArgumentException("El nombre no puede tener más de 25 caracteres");
        }
    }

    @Override
    public void validateLastName(String lasName) {
        if (lasName == null || lasName.trim().isEmpty()) {
            throw new IllegalArgumentException("El Apellido es requerido");
        }
        if (lasName.length() > 25) {
            throw new IllegalArgumentException("El Apellido no puede tener más de 25 caracteres");
        }
    }

    @Override
    public void validateGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Genero es requerido");
        }
    }
    @Override
    public void validateAge(Integer age) {
        if (age == null) {
            throw new IllegalArgumentException("Edad es requerido");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Edad debe ser un numero positivo");
        }
        if (age < 13) {
            throw new IllegalArgumentException("Edad debe ser mayor a 13 anios");
        }
    }
    @Override
    public void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Direccion es requerido");
        }
        int length = address.trim().length();
        if (length < 10 || length > 50) {
            throw new IllegalArgumentException("Direccion debe tener entre 10 y 50 caracteres");
        }
    }
    @Override
    public void validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefono es requerido");
        }
        String phoneRegex = "^\\d{7}$|^\\d{10}$";
        if (!Pattern.matches(phoneRegex, phone)) {
            throw new IllegalArgumentException("Telefono debe tener 7 o 10 digitos");
        }
    }
}
