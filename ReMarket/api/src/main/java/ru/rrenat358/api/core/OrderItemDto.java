package ru.rrenat358.api.core;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;


@Schema(description = "Модель 1 элемента заказа")
public class OrderItemDto {

    @Schema(description = "id продукта", required = true, example = "3")
    private Long productId;

    @Schema(description = "название продукта", required = true, example = "Малина")
    private String productTitle;

    @Schema(description = "количество продуктов", required = true, example = "2")
    private int quantity;

    @Schema(description = "цена за 1 ед. продукта", required = true, example = "130.40")
    private BigDecimal pricePerProduct;

    @Schema(description = "сумма за произведение цена*количество", required = true, example = "260.80")
    private BigDecimal price;




    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderItemDto() {
    }

    public OrderItemDto(Long productId, String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
}
