package com.ms.accounttransactions_back.adapter.out.messages;


import com.ms.accounttransactions_back.application.port.out.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file : PropertiesMessageService
 * @since : 23/4/2025, mié
 **/

@Component
@RequiredArgsConstructor
public class PropertiesMessageService implements MessageService {
    private final Environment env;

    @Override
    public String get(String key) {
        return env.getProperty(key, "Mensaje no encontrado: " + key);
    }
}
