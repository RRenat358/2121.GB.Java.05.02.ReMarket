package ru.rrenat358.stats.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.stats.exceptions.CoreServiceIntegrationException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient coreServiceWebClient;

    public Integer getNumberOfOrdersByCurrentUser(String username) {
        Integer numberOfOrders = coreServiceWebClient.get()
                .uri("/api/v1/orders/number-of-orders")
                .header("username", username)
                .retrieve()
//                .onStatus(
//                        httpStatus -> httpStatus.is4xxClientError(), // HttpStatus::is4xxClientError
//                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
//                                body -> {
//                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_NOT_FOUND.name())) {
//                                        return new CoreServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина не найдена");
//                                    }
//                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name())) {
//                                        return new CoreServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина сломана");
//                                    }
//                                    return new CoreServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: причина неизвестна");
//                                }
//                        )
//                )
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err400 - Некорректный запрос к сервису продуктов")))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err500 - Сервис продуктов не работает")))
                .bodyToMono(Integer.class)
                .block();
        return numberOfOrders;
    }



    //============================================================
    public Optional<OrderDto> getAllOrdersByCurrentUser(String username) {
        Optional orderDtoList =
                coreServiceWebClient.get()
                        .uri("/api/v1/orders")
                        .header("username", username)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err400 - Некорректный запрос к сервису продуктов")))
                        .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err500 - Сервис продуктов не работает")))
//                        .bodyToMono(OrderDto.class)
                        .bodyToMono( Optional.class)
                        .block();
        return orderDtoList;
    }

    //============================================================
    public Optional<ProductTopInOrdersDto> topProductsByAllOrders(Integer limit) {
        Optional orderDtoList =
                coreServiceWebClient.get()
                        .uri("/api/v1/orders/top-products-in-all-orders/" + limit)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err400 - Некорректный запрос к сервису продуктов")))
                        .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CoreServiceIntegrationException("err500 - Сервис продуктов не работает")))
//                        .bodyToMono(OrderDto.class)
                        .bodyToMono( Optional.class)
                        .block();
        return orderDtoList;
    }





}
