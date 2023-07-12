package ru.rrenat358.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.cart.exceptions.CoreServiceIntegrationException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductsServiceIntegration {

    private final WebClient coreServiceWebClient;


    public Optional<ProductDto> findById(Long id) {
        ProductDto productDto =
                coreServiceWebClient.get()
                        .uri("/api/v1/products/" + id)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err400 - Некорректный запрос к сервису продуктов")))
                        .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err500 - Сервис продуктов не работает")))
//                        .bodyToMono(OrderDto.class)
                        .bodyToMono( ProductDto.class)
                        .block();
//        return productDto;
        return Optional.ofNullable(productDto);
    }




}
