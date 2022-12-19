package com.mauricio.inventory.shelf;

import com.mauricio.inventory.location.Location;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "shelf")
@AllArgsConstructor
@NoArgsConstructor
public class Shelf {

    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    @Column(
            name = "id"
    )
    private Long id;
    @Size(max = 20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String serial;
    @Size(max = 50)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @Size(max = 200)
    private String description;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "shelf"
    )
    private List<Location> locations = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public List<Location> getLocations() {
        return locations;
    }
}
