package com.ms.accounttransactions_back.adapter.config;

import org.springframework.context.annotation.*;

/**
 * @author : Freddy Torres
 * file : PropertiesConfig
 * @since : 23/4/2025, mié
 **/


@Configuration
@PropertySource("classpath:messages.properties")
@PropertySource("classpath:endpoints.properties")
public class PropertiesConfig {
}
