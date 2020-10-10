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
@Table(name = "restaurants")
@Data
@Getter
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    public Restaurant(String name, String description, String address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }

}
