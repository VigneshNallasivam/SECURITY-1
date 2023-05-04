package com.spring.basics.repository;

import com.spring.basics.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
    @Query(value = "select * from company.employees where employees.name=:name",nativeQuery = true)
    Optional<Employee> findByName(String name);
    Boolean existsByName(String name);

    Boolean existsByMail(String mail);


}
