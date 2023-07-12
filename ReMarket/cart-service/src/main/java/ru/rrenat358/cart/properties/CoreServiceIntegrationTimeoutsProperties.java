package ru.rrenat358.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
public class CoreServiceIntegrationTimeoutsProperties {

    private Integer connection;
    private Integer read;
    private Integer write;

}
