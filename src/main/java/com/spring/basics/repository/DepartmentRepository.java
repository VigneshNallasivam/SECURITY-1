package com.spring.basics.repository;

import com.spring.basics.model.Department;
import com.spring.basics.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>
{
    //Optional<Department> findByDeptNames(String deptName);
    Page<Department> findByDeptName(Pageable pageable, String deptName);

}
