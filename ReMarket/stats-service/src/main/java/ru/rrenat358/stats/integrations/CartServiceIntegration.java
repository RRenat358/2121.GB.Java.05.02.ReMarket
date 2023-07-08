package ru.rrenat358.stats.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import ru.rrenat358.api.carts.CartDto;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.api.core.ProductDtoTopInCart;
import ru.rrenat358.api.exceptions.CartServiceAppError;
import ru.rrenat358.stats.exceptions.CartServiceIntegrationException;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public Optional<ProductDtoTopInCart> topProductsByAllUsers(Integer topLimit) {
        Optional productDto = cartServiceWebClient.get()
                .uri("/api/v1/cart/top-products-by-all-users/" + topLimit)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(), // HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
                                body -> {
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_NOT_FOUND.name())) {
                                        return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина не найдена");
                                    }
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name())) {
                                        return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина сломана");
                                    }
                                    return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: причина неизвестна");
                                }
                        )
                )
                .bodyToMono(Optional.class)
                .block();
        return productDto;
    }


}
