package com.mauricio.inventory.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauricio.inventory.AuditModel;
import com.mauricio.inventory.area.Area;
import com.mauricio.inventory.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
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

    @Column(nullable = false, unique = true)
    private String password;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Area area;



}
