package ru.rrenat358.cart.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rrenat358.api.carts.CartDto;
import ru.rrenat358.api.dto.StringResponse;
import ru.rrenat358.cart.converters.CartConverter;
import ru.rrenat358.cart.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "01. Корзина", description = "Методы работы с корзинами")
@RequiredArgsConstructor
public class CartsController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/{uuid}")
    @Operation(summary = "получить корзину")
    public CartDto getCart(
            @RequestHeader(required = false) @Parameter(description = "Имя пользователя") String username,
            @PathVariable @Parameter(description = "uuid корзины") String uuid){
        return cartConverter.modelToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
    }

    @GetMapping("/generate")
    @Operation(summary = "генерация новой корзины")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    @Operation(summary = "добавление Продукта по id в Корзину по её uuid")
    public void add(
            @RequestHeader(required = false) @Parameter(description = "Имя пользователя") String username,
            @PathVariable @Parameter(description = "uuid корзины") String uuid,
            @PathVariable @Parameter(description = "id продукта") Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    @Operation(summary = "уменьшение количества Продукта по id в Корзине по её uuid")
    public void decrement(
            @RequestHeader(required = false) @Parameter(description = "Имя пользователя") String username,
            @PathVariable @Parameter(description = "uuid корзины") String uuid,
            @PathVariable @Parameter(description = "id продукта") Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    @Operation(summary = "удаление Продукта по id из Корзины по её uuid")
    public void remove(
            @RequestHeader(required = false) @Parameter(description = "Имя пользователя") String username,
            @PathVariable @Parameter(description = "uuid корзины") String uuid,
            @PathVariable @Parameter(description = "id продукта") Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    @Operation(summary = "очистка корзины от элементов, но не удаление самой корзины")
    public void clear(
            @RequestHeader(required = false) @Parameter(description = "Имя пользователя") String username,
            @PathVariable @Parameter(description = "uuid корзины") String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    @Operation(summary = "соединение корзины анонимного пользователя и зарегистрированного")
    public void merge(
            @RequestHeader(required = false) @Parameter(description = "Имя пользователя") String username,
            @PathVariable @Parameter(description = "uuid корзины") String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    @GetMapping("/top-products-in-all-carts/{limit}")
    @Operation(summary = "топ-продуктов добавляемых в корзину всеми пользователями")
    public Object topProductsByAllCarts(
            @PathVariable @Parameter(description = "Лимит вывода топ-N-продуктов") Integer limit) {
        return cartService.topProductsByAllCarts(limit);
    }


    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
