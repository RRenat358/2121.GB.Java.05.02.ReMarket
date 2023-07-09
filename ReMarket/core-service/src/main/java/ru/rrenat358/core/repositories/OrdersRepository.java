package ru.rrenat358.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rrenat358.core.entities.Order;
import ru.rrenat358.core.entities.Product;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.username = ?1")
    List<Order> findAllOrdersByUsername(String userName);


    @Query("SELECT count(*) FROM Order o WHERE o.username = ?1")
    Integer getNumberOfOrdersByCurrentUser(String username);

//    @Query("SELECT product_id FROM orders JOIN order_items ON orders.product_id = order_items.id")
//    List<Order> topItemsByAllOrders(String username);


    @Query("""
            SELECT p.title
            FROM Product p
            JOIN OrderItem oi
            ON p.id = oi.product.id
            """)
    List<String> getAllOrders5();


}
