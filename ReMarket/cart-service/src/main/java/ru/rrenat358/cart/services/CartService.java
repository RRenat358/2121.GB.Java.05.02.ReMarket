package ru.rrenat358.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.api.exceptions.ResourceNotFoundException;
import ru.rrenat358.cart.integrations.ProductsServiceIntegration;
import ru.rrenat358.cart.models.Cart;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductsServiceIntegration productsServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    private HashMap<Long, Integer> countProductIdAllUser = new HashMap<>();


    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey) {
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public void addToCart(String cartKey, Long productId) {
        ProductDto productDto = productsServiceIntegration.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
        execute(cartKey, c -> {
            c.add(productDto);
        });
        countProductIdAllUser.merge(productId, 1, (x, y) -> x + y);
//        System.out.println("=================");
//        System.out.println(countProductIdAllUser);
//        System.out.println(topProductId(countProductIdAllUser, 3));
//        System.out.println("-----------------");
//        topProductsByAllUsers();
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clear);
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c -> c.removeProduct(productId));
    }

    public void decrementItem(String cartKey, Long productId) {
        execute(cartKey, c -> c.decrementProduct(productId));
    }

    public void merge(String userCartKey, String guestCartKey) {
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }


/*
    public LinkedHashMap<ProductDto, Integer> topProductsByAllUsers(Integer limit) {
        LinkedHashMap<ProductDto, Integer> topProduct = new LinkedHashMap<>();
        topProductId(countProductIdAllUser, limit)
                .forEach((productId, count) -> {
                    ProductDto productDto = productsServiceIntegration.findById(productId)
                            .orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
                    topProduct.put(productDto, count);
//                    System.out.println("productDto: " + productDto + " count:" + count);
                });
//        System.out.println(topProduct);
        return topProduct;
    }
*/


    public ProductDto topProductsByAllUsers(Integer limit) {
        Set<ProductDto> topProduct = new HashSet<>();
        topProductId(countProductIdAllUser, limit)
                .forEach((productId, count) -> {
                    ProductDto productDto = productsServiceIntegration.findById(productId)
                            .orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
                    topProduct.add(productDto);
//                    System.out.println("productDto: " + productDto + " count:" + count);
                });
//        System.out.println(topProduct);
        return  topProduct.stream().findFirst().get();
    }

    private LinkedHashMap<Long, Integer> topProductId(HashMap<Long, Integer> countProduct, int limit) {
        LinkedHashMap<Long, Integer> topProductId = countProduct.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return topProductId;
    }


}