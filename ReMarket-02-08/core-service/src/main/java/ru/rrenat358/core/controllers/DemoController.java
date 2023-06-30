package ru.rrenat358.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrenat358.api.dto.StringResponse;
import ru.rrenat358.core.integrations.CartServiceIntegration;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final CartServiceIntegration cartServiceIntegration;

    @GetMapping
    public StringResponse demo() {
        cartServiceIntegration.getUserCart("1");
        return new StringResponse("OK");
    }
}
