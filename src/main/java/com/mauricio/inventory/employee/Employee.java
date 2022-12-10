package com.mauricio.inventory.employee;

import com.mauricio.inventory.AuditModel;
import com.mauricio.inventory.area.Area;
import com.mauricio.inventory.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "employee")
public class Employee extends AuditModel {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "employee_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;
    @Size(max = 80)
    @NotBlank
    @Column(nullable = false)
    private String name;
    @Size(max = 80)
    private String lastName;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Email(message = "Invalid email")
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String password;


    @ManyToOne
    @JoinColumn(
            name = "role_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "role_employee_fk"
            )
    )
    private Role role;
    @ManyToOne
    @JoinColumn(
            name = "area_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "area_employee_fk"
            )
    )
    private Area area;

    public Employee(String name, String lastName, Status status, String email, String password,
                    Role role, Area area)
    {
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.email = email;
        this.password = password;
        this.role = role;
        this.area = area;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
