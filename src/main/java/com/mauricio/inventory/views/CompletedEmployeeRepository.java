package com.mauricio.inventory.views;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CompletedEmployeeRepository extends
        JpaRepository<CompletedEmployee, String>
{
    CompletedEmployee findByEmail(String email);
}
