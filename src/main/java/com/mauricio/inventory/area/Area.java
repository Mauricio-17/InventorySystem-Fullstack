package com.mauricio.inventory.area;

import com.mauricio.inventory.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "area")
public class Area {

    @Id
    @SequenceGenerator(
            name = "area_sequence",
            sequenceName = "area_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "area_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @NotBlank
    @Size(max = 70)
    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "area"
    )
    private List<Employee> employees = new ArrayList<>();

    public Area(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    public List<Employee> getEmployees() {
        return employees;
    }


    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
