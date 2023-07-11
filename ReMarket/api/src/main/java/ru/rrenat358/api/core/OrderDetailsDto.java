package ru.rrenat358.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель заказа при оформлении")
public class OrderDetailsDto {

    @Schema(description = "Адрес доставки", required = true, example = "ул. Вишнёвый пер. 3")
    private String address;

    @Schema(description = "Телефон для связи с клиентом (в любом формате)", required = true, example = "8 967 067 0112")
    private String phone;


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

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }
}
