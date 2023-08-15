package ru.rrenat358.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

//@Data
@Schema(description = "Модель топ-N-продуктов по корзинам всех пользователей")
public class ProductTopInCartDto {
    @Schema(description = "ID продукта", required = true, example = "1")
    private Long id;

    @Schema(description = "Название продукта", required = true, maxLength = 255, minLength = 3, example = "Коробка конфет")
    private String title;

    @Schema(description = "Цена продукта", required = true, example = "120.90")
    private BigDecimal price;

    @Schema(description = "Количество по корзинам всех юзеров", required = true, example = "5")
    private Integer countInCart;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCountInCart() {
        return countInCart;
    }

    public void setCountInCart(Integer countInCart) {
        this.countInCart = countInCart;
    }


    public ProductTopInCartDto() {
    }

    public ProductTopInCartDto(Long id, String title, BigDecimal price, Integer countInCart) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.countInCart = countInCart;
    }



}
