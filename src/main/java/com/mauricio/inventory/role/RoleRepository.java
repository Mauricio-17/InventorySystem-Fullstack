package com.mauricio.inventory.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    @Query("" +
            "SELECT CASE WHEN COUNT(r) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Role r " +
            "WHERE r.name = ?1"
    )
    Boolean existsName(String name);

}
