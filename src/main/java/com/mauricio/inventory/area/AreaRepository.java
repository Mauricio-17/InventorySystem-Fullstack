package com.mauricio.inventory.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(a) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Area a " +
            "WHERE a.name = ?1"
    )
    Boolean existsName(String name);


}
