package com.mauricio.inventory.transaction;

import com.mauricio.inventory.AuditModel;
import com.mauricio.inventory.employee.Employee;
import com.mauricio.inventory.equipment.Equipment;
import com.mauricio.inventory.location.Location;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "transaction")
public class Transaction extends AuditModel {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "transaction_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @Size(max = 200)
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "equipment_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "equipment_transaction_fk"
            )
    )
    private Equipment equipment;
    @ManyToOne
    @JoinColumn(
            name = "employee_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "employee_transaction_fk"
            )
    )
    private Employee employee;
    @ManyToOne
    @JoinColumn(
            name = "source_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "source_transaction_fk"
            )
    )
    private Location source;
    @ManyToOne
    @JoinColumn(
            name = "destination_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "destination_transaction_fk"
            )
    )
    private Location destination;

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Location getSource() {
        return source;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }
}
