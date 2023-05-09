package com.spring.basics.repository;

import com.spring.basics.model.Department;
import com.spring.basics.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Integer>
{
    @Query(value = "SELECT s FROM Salary s WHERE s.salary BETWEEN ?1 AND ?2")
    List<Salary> salaryBetween(String start,String end);



}
