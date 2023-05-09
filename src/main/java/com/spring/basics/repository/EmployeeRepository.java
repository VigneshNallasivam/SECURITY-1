package com.spring.basics.repository;

import com.spring.basics.model.Department;
import com.spring.basics.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
    Optional<Employee> findByName(String name);
    Boolean existsByName(String name);

    Boolean existsByMail(String mail);
    @Query(value = "SELECT e FROM Employee e WHERE e.age >= ?1")
    List<Employee> findByAgeGreaterThan(String age);


}
