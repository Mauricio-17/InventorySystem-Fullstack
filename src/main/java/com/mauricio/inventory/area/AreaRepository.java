package com.mauricio.inventory.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AreaRepository extends JpaRepository<Area, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM area a " +
            "WHERE a.name = ?1"
    )
    Boolean existsName(String name);
}
