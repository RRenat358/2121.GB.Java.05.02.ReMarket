package ru.rrenat358.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rrenat358.api.carts.CartDto;
import ru.rrenat358.api.core.OrderDetailsDto;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.api.core.ProductDtoTopInCart;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.core.entities.Order;
import ru.rrenat358.core.entities.OrderItem;
import ru.rrenat358.core.entities.Product;
import ru.rrenat358.core.integrations.CartServiceIntegration;
import ru.rrenat358.core.repositories.OrdersRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductsService productsService;
    private final OrdersRepository ordersRepository;
    private final CartServiceIntegration cartServiceIntegration;


    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {
        Order order = new Order();
//        String cartKey = cartService.getCartUuidFromSuffix(username.getUsername());
        CartDto currentCart = cartServiceIntegration.getUserCart(username);

        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());

//        List<OrderItemDto> orderItemList = currentCart.getItemList()
//                        .stream().toList();

        List<OrderItem> orderItemList = currentCart.getItems().stream()
                .map(orderItemDto -> { // orderItemDto =? o
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(orderItemDto.getQuantity());
                    orderItem.setPrice(orderItemDto.getPrice());
                    orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
                    orderItem.setProduct(productsService.findById(orderItemDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Продукт не найден")));
                    return orderItem;
                }).collect(Collectors.toList());

        order.setItems(orderItemList);
        ordersRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
    }

    public List<Order> getAllOrdersByCurrentUser(String username) {
        return ordersRepository.findAllOrdersByUsername(username);
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public Integer getNumberOfOrdersByCurrentUser(String username) {
        return ordersRepository.getNumberOfOrdersByCurrentUser(username);
    }


    //============================================================
    public List<ProductTopInOrdersDto> topProductsByAllOrders(Integer topLimit) {
        List<Product> productList = ordersRepository.topProductsByAllOrders(Pageable.ofSize(topLimit));
        List<ProductTopInOrdersDto> productDtoList = entityToDtoList(productList);
        return productDtoList;
    }

    public List<ProductTopInOrdersDto> entityToDtoList(List<Product> productList) {
        List<ProductTopInOrdersDto> productDtoList = productList
                .stream()
                .map(product -> {
                    ProductTopInOrdersDto productDto = new ProductTopInOrdersDto();
                    productDto.setId(product.getId());
                    productDto.setTitle(product.getTitle());
                    productDto.setPrice(product.getPrice());
                    productDto.setGroupProduct(product.getGroupProduct());
                    return productDto;
                })
                .collect(Collectors.toList());
        return productDtoList;
    }

    //============================================================
    public List<Integer> topProductsCountByAllOrders(Integer topLimit) {
        return ordersRepository.topProductsByAllOrdersCount(Pageable.ofSize(topLimit));
    }

    //============================================================
    public LinkedHashMap<List<Integer>, List<ProductTopInOrdersDto>> topProductsWithCountByAllOrders(Integer topLimit) {
        List<ProductTopInOrdersDto> productTopInOrdersDtos = topProductsByAllOrders(topLimit);
        List<Integer> countList = topProductsCountByAllOrders(topLimit);
        LinkedHashMap<List<Integer>, List<ProductTopInOrdersDto>> map = new LinkedHashMap<>();
        map.put(countList, productTopInOrdersDtos);
        return map;
    }


}