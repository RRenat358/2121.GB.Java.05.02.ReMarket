package ru.rrenat358.api.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

//@Data
@Schema(description = "Модель продукта")
public class ProductDto {

    @Schema(description = "ID продукта", required = true, example = "1")
    private Long id;

    @Schema(description = "Название продукта", required = true, maxLength = 255, minLength = 3, example = "Манго")
    private String title;

    @Schema(description = "Цена продукта", required = true, example = "120.90")
    private BigDecimal price;

    @Schema(description = "Группа продукта", required = true, example = "орехи")
    private String groupProduct;


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


    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal price, String groupProduct) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.groupProduct = groupProduct;
    }



}
