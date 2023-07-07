package ru.rrenat358.stats.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rrenat358.api.carts.CartDto;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.exceptions.CartServiceAppError;
import ru.rrenat358.stats.exceptions.CoreServiceIntegrationException;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient coreServiceWebClient;




/*
    public Integer getNumberOfOrdersByCurrentUser(String username) {
        Integer numberOfOrders = coreServiceWebClient.get()
                .uri("/api/v1/orders/number-of-orders")
                .header("username", username)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        return numberOfOrders;
    }
*/


/*
    public CartDto getUserCart(String username) {
        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                // .bodyValue(body) // for POST
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(), // HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
                                body -> {
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_NOT_FOUND.name())) {
                                        return new CoreServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина не найдена");
                                    }
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name())) {
                                        return new CoreServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина сломана");
                                    }
                                    return new CoreServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: причина неизвестна");
                                }
                        )
                )
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CoreServiceIntegrationException("Выполнен некорректный запрос к сервису корзин")))
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CoreServiceIntegrationException("Сервис корзин сломался")))
                .bodyToMono(CartDto.class)
                .block();
        return cart;
    }
*/
    public Integer getNumberOfOrdersByCurrentUser(String username) {
        Integer numberOfOrders = coreServiceWebClient.get()
                .uri("/api/v1/orders/number-of-orders")
                .header("username", username)
                // .bodyValue(body) // for POST
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



}
