package com.mauricio.inventory.owner;

import com.mauricio.inventory.AuditModel;
import com.mauricio.inventory.equipment.Equipment;
import com.mauricio.inventory.typeowner.TypeOwner;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "owner")
public class Owner extends AuditModel {

    @Id
    @SequenceGenerator(
            name = "owner_sequence",
            sequenceName = "owner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "owner_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String name;
    @Size(max = 80)
    private String lastName;
    @Size(max = 15)
    private String cellphoneNumber;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Size(max = 10)
    private String dni;

    @ManyToOne
    @JoinColumn(
            name = "type_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "type_owner_fk"
            )
    )
    private TypeOwner typeOwner;
    @OneToMany(
            mappedBy = "owner",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Equipment> equipments;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public TypeOwner getTypeOwner() {
        return typeOwner;
    }

    public void setTypeOwner(TypeOwner typeOwner) {
        this.typeOwner = typeOwner;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        for(Equipment eq : equipments){
            this.equipments.add(eq);
        }
    }
}
