package ru.rrenat358.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Модель пары Логин/Пароль")
@Data
public class JwtRequest {

    @Schema(description = "Имя пользователя", example = "Пётр")
    private String username;

    @Schema(description = "Пароль пользователя", example = "pr1234$%")
    private String password;
}
