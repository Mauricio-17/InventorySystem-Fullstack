package com.mauricio.inventory.role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "role_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, unique = true)
    private String name;
    @Size(max = 200)
    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId(){
        return this.id;
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
