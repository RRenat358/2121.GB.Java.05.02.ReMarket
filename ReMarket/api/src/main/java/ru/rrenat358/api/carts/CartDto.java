package ru.rrenat358.api.carts;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель корзины")
public class CartDto {

    @Schema(description = "Список элементов входящих в заказ", example = "[ ItemObject01, ItemObject02, ItemObject03, ... ]")
    private List<CartItemDto> items;

    @Schema(description = "Стоимость заказа общая", required = true, example = "500.60")
    private BigDecimal totalPrice;



    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }

    public CartDto(List<CartItemDto> items, BigDecimal totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
