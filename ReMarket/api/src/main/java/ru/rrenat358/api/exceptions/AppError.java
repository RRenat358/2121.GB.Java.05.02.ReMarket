package ru.rrenat358.api.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

public class AppError {

    @Schema(description = "Код ошибки", example = "CART_NOT_FOUND")
    private String code;

    @Schema(description = "ЧПУ сообщение", example = "Корзина не найдена")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppError() {
    }

    public AppError(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
