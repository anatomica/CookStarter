package ru.anatomica.cookstarter.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;

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

    @Column(name = "telephone")
    private String telephone;

    public Restaurant(String name, String description, String address, String telephone) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.telephone = telephone;
    }

}
