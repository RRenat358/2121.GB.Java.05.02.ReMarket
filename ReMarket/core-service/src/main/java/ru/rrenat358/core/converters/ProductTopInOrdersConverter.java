package ru.rrenat358.core.converters;

import org.springframework.stereotype.Component;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.core.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductTopInOrdersConverter {

    //============================================================
    public Product dtoToEntity(ProductTopInOrdersDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getPrice(),
                productDto.getGroupProduct());
    }

    public ProductTopInOrdersDto entityToDto(Product product) {
        return new ProductTopInOrdersDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getGroupProduct());
    }

    //============================================================
    public List<Product> dtoToEntityList(List<ProductTopInOrdersDto> productDtoList) {
        List<Product> productList = productDtoList
                .stream()
                .map(productDto -> {
                    Product product = new Product();
                    product.setId(productDto.getId());
                    product.setTitle(productDto.getTitle());
                    product.setPrice(productDto.getPrice());
                    product.setGroupProduct(productDto.getGroupProduct());
                    return product;
                })
                .collect(Collectors.toList());
        return productList;
    }

    public List<ProductTopInOrdersDto> entityToDtoList(List<Product> productList) {
        List<ProductTopInOrdersDto> productDtoList = productList
                .stream()
                .map(product -> {
                    ProductTopInOrdersDto productDto = new ProductTopInOrdersDto();
                    productDto.setId(product.getId());
                    productDto.setTitle(product.getTitle());
                    productDto.setPrice(product.getPrice());
                    return productDto;
                })
                .collect(Collectors.toList());
        return productDtoList;
    }

    //============================================================


}
