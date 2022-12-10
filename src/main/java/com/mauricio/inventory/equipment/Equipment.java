package com.mauricio.inventory.equipment;

import com.mauricio.inventory.AuditModel;
import com.mauricio.inventory.brand.Brand;
import com.mauricio.inventory.category.Category;
import com.mauricio.inventory.location.Location;
import com.mauricio.inventory.owner.Owner;
import com.mauricio.inventory.shelf.Shelf;
import com.mauricio.inventory.transaction.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "equipment")
public class Equipment extends AuditModel {

    @Id
    @SequenceGenerator(
            name = "equipment_sequence",
            sequenceName = "equipment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "equipment_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @Size(max = 15)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String sku;
    @Size(max = 15)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @NotBlank
    @Column(nullable = false)
    private int stock;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @ManyToOne
    @JoinColumn(
            name = "owner_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "owner_equipment_fk"
            )
    )
    private Owner owner;
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "category_equipment_fk"
            )
    )
    private Category category;
    @ManyToOne
    @JoinColumn(
            name = "brand_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "brand_equipment_fk"
            )
    )
    private Brand brand;
    @ManyToOne
    @JoinColumn(
            name = "location_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "location_equipment_fk"
            )
    )
    private Location location;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "equipment"
    )
    private List<Transaction> transactions;


    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Location getLocation() {
        return location;
    }

    public void setShelf(Location location) {
        this.location = location;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
