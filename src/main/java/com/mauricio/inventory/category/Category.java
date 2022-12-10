package com.mauricio.inventory.category;

import com.mauricio.inventory.equipment.Equipment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @Size(max = 80)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @Size(max = 200)
    private String description;
    @OneToMany(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Equipment> equipments;

    public Category(String name, String description, List<Equipment> equipments) {
        this.name = name;
        this.description = description;
        this.equipments = equipments;
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

    public List<Equipment> getEquipments() {
        return equipments;
    }
    public void setEquipments(List<Equipment> list){
        for(Equipment eq : list){
           this.equipments.add(eq);
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
