package ru.rrenat358.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

//@Data
@Schema(description = "Модель топ продуктов по всем заказам всех юзеров")
public class ProductTopInOrdersDto {
    @Schema(description = "ID продукта", required = true, example = "1")
    private Long id;

    @Schema(description = "Название продукта", required = true, maxLength = 255, minLength = 3, example = "Яблоки")
    private String title;

    @Schema(description = "Цена продукта", required = true, example = "50.90")
    private BigDecimal price;

    @Schema(description = "Группа продуктов", required = true, maxLength = 255, minLength = 3, example = "Фрукты")
    private String groupProduct;

    @Schema(description = "Количество по заказам всех юзеров", required = true, example = "5")
    private Integer countInOrders;



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

    public String getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(String groupProduct) {
        this.groupProduct = groupProduct;
    }

    public Integer getCountInOrders() {
        return countInOrders;
    }

    public void setCountInOrders(Integer countInOrders) {
        this.countInOrders = countInOrders;
    }

    public ProductTopInOrdersDto(Long id, String title, BigDecimal price, String groupProduct, Integer countInOrders) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.groupProduct = groupProduct;
        this.countInOrders = countInOrders;
    }

/*
public ProductTopInOrdersDto(Long id, String title, BigDecimal price, String groupProduct) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.groupProduct = groupProduct;
}
*/

    public ProductTopInOrdersDto() {
    }



}
