package com.mauricio.inventory.typeowner;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "type_owner")
public class TypeOwner {

    @Id
    @SequenceGenerator(
            name = "type_sequence",
            sequenceName = "type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "type_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, unique = true)
    private String name;
    @Size(max = 200)
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
