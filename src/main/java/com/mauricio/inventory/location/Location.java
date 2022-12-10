package com.mauricio.inventory.location;

import com.mauricio.inventory.shelf.Shelf;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @SequenceGenerator(
            name = "location_sequence",
            sequenceName = "location_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "location_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @NotBlank
    @Column(name = "shelf_row", nullable = false, unique = true)
    private Byte row;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @ManyToOne
    @JoinColumn(
            name = "shelf_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "shelf_location_fk"
            )
    )
    private Shelf shelf;


    public Long getId() {
        return id;
    }

    public byte getRow() {
        return row;
    }

    public void setRow(byte row) {
        this.row = row;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
}
