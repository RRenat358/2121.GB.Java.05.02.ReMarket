package ru.rrenat358.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.api.exceptions.CartServiceAppError;
import ru.rrenat358.api.exceptions.ResourceNotFoundException;
import ru.rrenat358.core.converters.ProductConverter;
import ru.rrenat358.core.entities.Product;
import ru.rrenat358.core.services.ProductsService;
import ru.rrenat358.core.validators.ProductValidator;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "01. Продукты", description = "Методы работы с продуктами")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;



    //============================================================
    // GET

    @GetMapping
    @Operation(
            summary = "Запрос на получение страницы продуктов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    public Page<ProductDto> findByFilter(
            @RequestParam (name = "p", defaultValue = "1", required = true)
            @Parameter(description = "номер старицы листинга продуктов") Integer page,

            @RequestParam (name = "titlePart", required = false)
            @Parameter(description = "фильтр по названию продукта") String titlePart,

            @RequestParam (name = "minPrice", required = false)
            @Parameter(description = "фильтр по мин цене") Integer minPrice,

            @RequestParam (name = "maxPrice", required = false)
            @Parameter(description = "фильтр по макс цене") Integer maxPrice,

            @RequestParam (name = "группа", required = false)
            @Parameter(description = "фильтр по группе") String groupPart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productsService.findByFilter(page, titlePart, minPrice, maxPrice, groupPart)
                .map(product -> productConverter.entityToDto(product));
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "получение продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка пользователя", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = CartServiceAppError.class))
                    )
            }
    )
    public ProductDto findById(@PathVariable @Parameter(description = "id продукта", required = true) Long id) {
        Product product = productsService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден для ID : " + id));
        return productConverter.entityToDto(product);
    }


    //============================================================
    // POST

    @PostMapping
    @Operation(
            summary = "сохранить новый продукт в БД",
            hidden = true
    )
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.saveProduct(product);
        return productConverter.entityToDto(product);
    }

    //============================================================
    // PATCH

    @PatchMapping("/change-price-to-delta")
    @Operation(summary = "изменение цены продукта на дельту")
    public void changePriceToDelta(
            @RequestParam Long id,
            @RequestParam @Parameter(description = "число на которое нужно изменить цену +/-", required = true) Integer delta) {
        productsService.changePriceToDelta(id, delta);
    }

    @PatchMapping("/change-price")
    @Operation(
            summary = "замена цены продукта на другую",
            hidden = true
    )
    public void changePrice(@RequestParam Long id, @RequestParam Integer newPrice) {
        productsService.changePrice(id, newPrice);
    }

    //============================================================
    // PUT

    //============================================================
    @PutMapping
    @Operation(summary = "изменение значения полей продукта")
    public ProductDto updateProduct(
            @RequestBody @Parameter(description = "передать модель = ProductDto") ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.updateProduct(product);
        return productConverter.entityToDto(product);
    }
    // OR --------------------
//    @PutMapping
//    public ProductDto updateProduct2(@RequestBody ProductDto productDto) {
//        productValidator.validate(productDto);
//        Product product = productsService.update(productDto);
//        return productConverter.entityToDto(product);
//    }
    //============================================================


    //============================================================
    // DELETE

    @DeleteMapping("/{id}")
    @Operation(summary = "удаление из (!)БД продукта по id")
    public void deleteById(@PathVariable @Parameter(description = "id продукта") Long id) {
        productsService.deleteById(id);
    }


}
