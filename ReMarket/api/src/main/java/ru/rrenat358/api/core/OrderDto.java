package ru.rrenat358.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель содержания заказа")
public class OrderDto {

    @Schema(description = "id заказа", required = true, example = "1")
    private Long id;

    @Schema(description = "Имя пользователя который оформляет заказ", required = true, example = "Пётр")
    private String username;

    @Schema(description = "Стоимость заказа общая", required = true, example = "500.60")
    private BigDecimal totalPrice;

    @Schema(description = "Адрес доставки", required = true, example = "ул. Вишнёвый пер. 3")
    private String address;

    @Schema(description = "Телефон для связи с клиентом (в любом формате)", required = true, example = "8 967 067 0112")
    private String phone;

    @Schema(description = "Список элементов входящих в заказ", required = true, example = "[ ItemObject01, ItemObject02, ItemObject03, ... ]")
    private List<OrderItemDto> itemList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDto> itemList) {
        this.itemList = itemList;
    }

    public OrderDto() {
    }

    public OrderDto(Long id, String username, BigDecimal totalPrice, String address, String phone, List<OrderItemDto> itemList) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
        this.itemList = itemList;
    }


}
