package ru.anatomica.cookstarter.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "productsMenu")
@Data
@Getter
@NoArgsConstructor
public class ProductMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "logo_id")
    private Long logoId;

//    @ManyToOne
//    @JoinColumn(name = "category" )
//    private Category category;

//    @ManyToMany
//    @JoinTable(
//            name = "products_categories",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private List<Category> categories;

    public ProductMenu(String title, String description, BigDecimal price, Long restaurantId, Long logoId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.logoId = logoId;
    }

//    public ProductMenu(String title, String description, BigDecimal price, Category category) {
//        this.title = title;
//        this.description = description;
//        this.price = price;
//        this.category = category;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductMenu product = (ProductMenu) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
