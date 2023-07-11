package ru.rrenat358.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.Valid;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.core-service")
@Data
public class CoreServiceIntegrationProperties {

    private String url;

    @Valid
    private CoreServiceIntegrationTimeoutsProperties timeouts;



}
