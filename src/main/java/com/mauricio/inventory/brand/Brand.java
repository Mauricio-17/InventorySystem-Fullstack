package com.mauricio.inventory.brand;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @SequenceGenerator(
            name = "brand_sequence",
            sequenceName = "brand_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "brand_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Size(max = 80)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @Size(max = 15)
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
