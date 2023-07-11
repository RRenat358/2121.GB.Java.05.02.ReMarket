package ru.rrenat358.core.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rrenat358.core.entities.Order;
import ru.rrenat358.core.entities.Product;
import java.util.*;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.username = ?1")
    List<Order> findAllOrdersByUsername(String userName);


    @Query("SELECT count(*) FROM Order o WHERE o.username = ?1")
    Integer getNumberOfOrdersByCurrentUser(String username);


//    @QueryHints(
//            @QueryHint(name = "org.hibernate.cacheable", value = "false")
//    )
    @Query("""
            SELECT p, COUNT(*)
            FROM Product p
            INNER JOIN OrderItem oi
            ON p.id = oi.product.id
            GROUP BY oi.product.id
            ORDER BY COUNT(*) DESC
            """)
    List<Product> topProductsByAllOrders(Pageable pageable);



    @Query("""
            SELECT COUNT(*)
            FROM Product p
            INNER JOIN OrderItem oi
            ON p.id = oi.product.id
            GROUP BY oi.product.id
            ORDER BY COUNT(*) DESC
            """)
    List<Integer> topProductsByAllOrdersCount(Pageable pageable);





}
