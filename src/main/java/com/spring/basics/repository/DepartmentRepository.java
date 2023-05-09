package com.spring.basics.repository;

import com.spring.basics.model.Department;
import com.spring.basics.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>
{

    Page<Department> findByDeptName(Pageable pageable, String deptName);
    @Query(value = "SELECT d FROM Department d WHERE d.deptLevel > ?1")
    List<Department> findByLevelGreaterThan(String deptLevel);
//    @Query(value = "SELECT d.deptName FROM Department d "
//            + " JOIN d.salaries e where e.salary = (SELECT MAX(e2.salary) FROM Department e2) ")
//    Optional<Department> maxSalary(double salary);


//    @Query(value = "SELECT d from Department d where d.deptName=:n")
//    List<Department> getDeptName(@Param("n") String deptName);

    @Query(value = "SELECT d from Department d where d.deptName= ?1")
    List<Department> getDeptName(String deptName);

    @Query(value = "SELECT d from Department d where d.deptName=:n or d.deptLevel=:e")
    List<Department> getDeptNameOrDeptLevel(@Param("n") String deptName,@Param("e")String deptLevel);

    @Query(value = "SELECT d from Department d order by d.salaried.salary desc")
    List<Department> findDepartmentSalaryByDesc();

}
