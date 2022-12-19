package com.mauricio.inventory.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauricio.inventory.AuditModel;
import com.mauricio.inventory.area.Area;
import com.mauricio.inventory.role.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AuditModel{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(
            strategy = IDENTITY
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
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String password;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Area area;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
