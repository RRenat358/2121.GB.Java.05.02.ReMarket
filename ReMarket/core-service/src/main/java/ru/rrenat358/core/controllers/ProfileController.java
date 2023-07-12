package ru.rrenat358.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrenat358.api.core.ProfileDto;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
@Tag(name = "20. Профиль пользователя", description = "Методы работы с профилем пользователя")
public class ProfileController {

    @GetMapping
    @Operation(
            summary = "получить информацию о пользователе"
    )
    public ProfileDto getCurrentUserInfo(@RequestHeader String username) {
//         User user = userService.findAllByUsername(principal.getName());
        return new ProfileDto(username);
    }


    //============================================================
    // saveNewUser
/*
    @GetMapping
    public ProfileDto getCurrentUserInfo(Principal principal) {
         Optional<User> user = userService.findByUsername(principal.getName());
        return new ProfileDto(
                user.get().getUsername(), user.get().getPassword(), user.get().getEmail()
        );
    }
*/





}
