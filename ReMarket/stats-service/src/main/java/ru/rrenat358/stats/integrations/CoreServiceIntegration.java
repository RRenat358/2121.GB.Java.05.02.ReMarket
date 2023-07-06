package ru.rrenat358.stats.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.rrenat358.api.carts.CartDto;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.exceptions.CartServiceAppError;
import ru.rrenat358.stats.exceptions.CoreServiceIntegrationException;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient coreServiceWebClient;

/*
    public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
*/

    public void createOrder(String username) {
        coreServiceWebClient.get()
                .uri("/api/v1/orders")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }




/*
    public CartDto getUserCart(String username) {
        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
        return cart;
    }
*/

/*
    public OrderDto getOrderById(String username) {
        OrderDto orderDto = coreServiceWebClient.get()
                .uri("/api/v1/orders/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(OrderDto.class)
                .block();
        return orderDto;
    }
*/

    public Integer getNumberOfOrdersByCurrentUser(String username) {
        Integer numberOfOrders = coreServiceWebClient.get()
                .uri("/api/v1/orders/number-of-orders")
                .header("username", username)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        return numberOfOrders;
    }


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



}
