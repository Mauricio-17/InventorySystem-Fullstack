package com.mauricio.inventory.brand;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "brand")
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Size(max = 80)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @Size(max = 30, message = "Nombre muy largo para un país")
    private String country;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
