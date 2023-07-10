package ru.rrenat358.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.security.RolesAllowed;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "proteins")
    private String proteins;

    @Column(name = "fats")
    private String fats;

    @Column(name = "carbohydrates")
    private String carbohydrates;

    @Column(name = "calories")
    private String calories;

//    todo хранить в БД  со @OneToMany/@ManyToOne
    @Column(name = "group_product")
    private String groupProduct;


    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(Long id, String title, BigDecimal price, String groupProduct) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.groupProduct = groupProduct;
    }

    public Product(Long id, String title, BigDecimal price, String proteins, String fats, String carbohydrates, String calories, String groupProduct, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.groupProduct = groupProduct;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
