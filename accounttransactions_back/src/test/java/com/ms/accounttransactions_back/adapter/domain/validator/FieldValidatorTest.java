package com.ms.accounttransactions_back.adapter.domain.validator;

import com.ms.accounttransactions_back.domain.enums.Gender;
import com.ms.accounttransactions_back.domain.validator.FieldValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldValidatorTest {
    private FieldValidator validator;

    @BeforeEach
    void setUp() {
        validator = new FieldValidator();
    }

    @Test
    void testValidateEmail_ValidEmail() {
        // Then
        assertDoesNotThrow(() -> validator.validateEmail("test@example.com"));
    }

    @Test
    void testValidateEmail_InvalidEmail() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validateEmail("test.com"));
        assertThrows(IllegalArgumentException.class, () -> validator.validateEmail(""));
        assertThrows(IllegalArgumentException.class, () -> validator.validateEmail(null));
    }

    @Test
    void testValidatePassword_ValidPassword() {
        // Then
        assertDoesNotThrow(() -> validator.validatePassword("Abcd123!"));
    }

    @Test
    void testValidatePassword_InvalidPassword() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validatePassword("short"));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePassword("NoSpecial123"));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePassword(""));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePassword(null));
    }

    @Test
    void testValidateName_Valid() {
        // Then
        assertDoesNotThrow(() -> validator.validateName("Juan"));
    }

    @Test
    void testValidateName_Invalid() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validateName(null));
        assertThrows(IllegalArgumentException.class, () -> validator.validateName(""));
        assertThrows(IllegalArgumentException.class, () -> validator.validateName("NombreDemasiadoLargoParaAceptarValidacion"));
    }

    @Test
    void testValidateLastName_Valid() {
        // Then
        assertDoesNotThrow(() -> validator.validateLastName("Pérez"));
    }

    @Test
    void testValidateLastName_Invalid() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validateLastName(null));
        assertThrows(IllegalArgumentException.class, () -> validator.validateLastName(""));
        assertThrows(IllegalArgumentException.class, () -> validator.validateLastName("ApellidoQueSuperaLos25CaracteresPermitidos"));
    }

    @Test
    void testValidateGender_Valid() {
        // Then
        assertDoesNotThrow(() -> validator.validateGender(Gender.M));
    }

    @Test
    void testValidateGender_Invalid() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validateGender(null));
    }

    @Test
    void testValidateAge_Valid() {
        // Then
        assertDoesNotThrow(() -> validator.validateAge(20));
    }

    @Test
    void testValidateAge_Invalid() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validateAge(null));
        assertThrows(IllegalArgumentException.class, () -> validator.validateAge(0));
        assertThrows(IllegalArgumentException.class, () -> validator.validateAge(12));
    }

    @Test
    void testValidateAddress_Valid() {
        // Then
        assertDoesNotThrow(() -> validator.validateAddress("Calle Falsa 123, Springfield"));
    }

    @Test
    void testValidateAddress_Invalid() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validateAddress(null));
        assertThrows(IllegalArgumentException.class, () -> validator.validateAddress(""));
        assertThrows(IllegalArgumentException.class, () -> validator.validateAddress("Corta"));
        assertThrows(IllegalArgumentException.class, () -> validator.validateAddress("Una direccion demasiado larga que sobrepasa los cincuenta caracteres permitidos"));
    }

    @Test
    void testValidatePhone_Valid() {
        // Then
        assertDoesNotThrow(() -> validator.validatePhone("1234567"));
        assertDoesNotThrow(() -> validator.validatePhone("1234567890"));
    }

    @Test
    void testValidatePhone_Invalid() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> validator.validatePhone(null));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePhone(""));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePhone("123"));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePhone("abcdefghij"));
    }
}
