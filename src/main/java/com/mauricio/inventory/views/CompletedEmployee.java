package com.mauricio.inventory.views;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Immutable
@Table(name = "view_employee")
@Subselect("select uuid() as id, ve.* from view_employee ve")
@Getter
@Setter
public class CompletedEmployee implements Serializable {

    @Id
    private String id;

    private Long employeeId;
    private String name;
    private String lastName;
    private String email;
    private String status;
    private String nameArea;
    private String nameRole;
    private Date createdAt;
    private Date updatedAt;

}
