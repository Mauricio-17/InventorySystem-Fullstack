package com.mauricio.inventory.area;

import com.mauricio.inventory.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

//@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(
            strategy = IDENTITY
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

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

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

    public Set<Employee> getEmployees() {
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
