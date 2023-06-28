package ru.rrenat358.cart.models;

import lombok.Data;
import ru.rrenat358.api.core.ProductDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto productDto) {
        if (add(productDto.getId())) {
            return;
        }
        items.add(new CartItem(productDto));
        recalculate();
    }

    public boolean add(Long id) {
        for (CartItem o : items) {
            if (o.getProductId().equals(id)) {
                o.changeQuantity(+1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decrementProduct(Long id) {
        Iterator<CartItem> iter = items.iterator();
        while (iter.hasNext()) {
            CartItem cartItem = iter.next();
            if (cartItem.getProductId().equals(id)) {
                cartItem.changeQuantity(-1);
                if (cartItem.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void removeProduct(Long id) {
        items.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem o : items) {
            totalPrice += o.getPrice();
        }
    }

    public void merge(Cart another) {
        for (CartItem anotherItem : another.items) {
            boolean merged = false;
            for (CartItem myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }



}
