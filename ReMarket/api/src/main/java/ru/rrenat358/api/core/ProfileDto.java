package ru.rrenat358.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProfileDto {

    @Schema(description = "Имя пользователя", example = "Пётр")
    private String username;
//    private String password;
//    private String email;

//    private String role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileDto() {
    }

    public ProfileDto(String username) {
        this.username = username;
    }
}
