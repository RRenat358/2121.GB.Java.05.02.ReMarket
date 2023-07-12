package ru.rrenat358.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class StringResponse {

    @Schema(description = "Строка ответа")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringResponse(String value) {
        this.value = value;
    }

    public StringResponse() {
    }
}
