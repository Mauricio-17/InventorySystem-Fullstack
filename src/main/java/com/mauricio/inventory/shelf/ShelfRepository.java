package com.mauricio.inventory.shelf;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {
}
