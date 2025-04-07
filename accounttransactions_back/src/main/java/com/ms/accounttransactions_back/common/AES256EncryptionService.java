package com.ms.accounttransactions_back.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;

/**
 * @author : Freddy Torres
 * file :  AES256EncryptionService
 * @since : 3/4/2025, jue
 **/

@Component
public class AES256EncryptionService {
    @Value("${aes.secret.key}")
    private String secretKeyBase64;

    public SecretKey getSecretKeyFromProperties() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
        return new javax.crypto.spec.SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
    public String encrypt(String data) throws Exception {
        SecretKey secretKey = getSecretKeyFromProperties();
        byte[] iv = new byte[16];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }
    public String decrypt(String encryptedData) throws Exception {
        SecretKey secretKey = getSecretKeyFromProperties();
        byte[] iv = new byte[16];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decodedEncryptedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedEncryptedData);
        return new String(decryptedData);
    }
}

