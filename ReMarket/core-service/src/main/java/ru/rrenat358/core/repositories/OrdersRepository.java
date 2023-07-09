package ru.rrenat358.core.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.core.entities.Order;
import ru.rrenat358.core.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.username = ?1")
    List<Order> findAllOrdersByUsername(String userName);


    @Query("SELECT count(*) FROM Order o WHERE o.username = ?1")
    Integer getNumberOfOrdersByCurrentUser(String username);

/*
    @Query("""
            SELECT  p.title, COUNT(*) AS count
            FROM Product p
            INNER JOIN OrderItem oi
            ON p.id = oi.product.id
            GROUP BY p.title
            ORDER BY COUNT(*) DESC
            """)
    List<Product> topProductsByAllOrders(Pageable pageable);

*/

/*
    @Query("""
            SELECT  p.id, p.title, p.groupProduct, p.price, COUNT(*) AS count
            FROM Product p
            INNER JOIN OrderItem oi
            ON p.id = oi.product.id
            GROUP BY p.id
            ORDER BY COUNT(*) DESC
            """)
    List<Product> topProductsByAllOrders(Pageable pageable);
*/

/*
    @Query("""
            SELECT  p.id, p.title, p.price, p.groupProduct, COUNT(*) AS count
            FROM Product p
            INNER JOIN OrderItem oi
            ON p.id = oi.product.id
            GROUP BY p
            ORDER BY COUNT(*) DESC
            """)
    List<Object> topProductsByAllOrders(Pageable pageable);

*/
    @Query("""
            SELECT  p, COUNT(*) AS count
            FROM Product p
            INNER JOIN OrderItem oi
            ON p.id = oi.product.id
            GROUP BY p
            ORDER BY COUNT(*) DESC
            """)
    List<Product> topProductsByAllOrders(Pageable pageable);




}
