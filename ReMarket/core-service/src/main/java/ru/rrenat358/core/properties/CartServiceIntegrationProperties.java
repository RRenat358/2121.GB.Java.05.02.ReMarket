package ru.rrenat358.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.Valid;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.cart-service")
@Data
public class CartServiceIntegrationProperties {

    private String url;

    @Valid
    private CartServiceIntegrationTimeoutsProperties timeouts;


}
