package ru.rrenat358.stats.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
public class CartServiceIntegrationTimeoutsProperties {

    private Integer connection;
    private Integer read;
    private Integer write;

}
