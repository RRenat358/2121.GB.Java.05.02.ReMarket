package ru.rrenat358.core.validators;

import org.springframework.stereotype.Component;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.core.exceptions.FieldValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductValidator {

    public void validate(ProductDto productDto) {

        List<String> errorMessages = new ArrayList<>();

        //todo !null
        if (productDto.getTitle().isBlank()) {
            errorMessages.add("Имя отсутствует !!!");
        }
//        if (productDto.getPrice() < 0) {
        if (productDto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errorMessages.add("Цена < 0 !!!");
        }


        if (!errorMessages.isEmpty()) {
            throw new FieldValidationException(errorMessages);
        }


    }



}
