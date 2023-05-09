package com.spring.basics.repository;

import com.spring.basics.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentRepository2 extends PagingAndSortingRepository<Department,Integer>
{
    @Query(value = "SELECT d from Department d order by d.deptLevel asc")
    List<Department> findDepartmentLevelByAsc();

    @Query(value = "SELECT d from Department d order by d.deptLevel desc")
    List<Department> findDepartmentLevelByDesc();


}
