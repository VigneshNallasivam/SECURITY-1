package com.spring.basics.service;

import com.spring.basics.exception.DepartmentException;
import com.spring.basics.model.Department;
import com.spring.basics.repository.DepartmentRepository;
import com.spring.basics.repository.DepartmentRepository2;
import com.spring.basics.repository.EmployeeRepository;
import com.spring.basics.response.ResponsePercent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService
{
    @Autowired
    DepartmentRepository repo;

    @Autowired
    DepartmentRepository2 repo2;

    @Autowired
    EmployeeRepository employeeRepository;

    public Department create(Department department)
    {
        return repo.save(department);
    }

    public Department update(int empId,Department department)
    {
        Department department1 = repo.findById(empId).get();
        if(repo.findById(empId).isPresent())
        {
            department1.setDeptName(department.getDeptName());
            department1.setDeptStrength(department.getDeptStrength());
            department1.setDeptLevel(department.getDeptLevel());
            repo.save(department1);
            return department1;
        }
        else
        {
            throw new DepartmentException("ID_NOT_FOUND");
        }
    }

    public List<Department> read()
    {
        List<Department> list = repo.findAll();
        if(repo.findAll().isEmpty())
        {
            throw new DepartmentException("OOPS..! Empty DATA-BASE...");
        }
        else
        {
            return list;
        }
    }

    public void delete(int empId)
    {
        Department department = repo.findById(empId).get();
        if(repo.findById(empId).isPresent())
        {
            repo.deleteById(empId);
        }
        else
        {
            throw new DepartmentException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }
    public List<Department> getByDeptLevelAsc()
    {
        return repo2.findDepartmentLevelByAsc();
    }
    public List<Department> getByDeptLevelDesc()
    {
        return repo2.findDepartmentLevelByDesc();
    }
    public List<Department> findByLevelGreaterThan(String deptLevel)
    {
        return repo.findByLevelGreaterThan(deptLevel);
    }

    public List<Department> getDeptName(@Param("n") String deptName)
    {
        return repo.getDeptName(deptName);
    }

    public List<Department> getDeptNameOrDeptLevel(String deptName,String deptLevel)
    {
        return repo.getDeptNameOrDeptLevel(deptName,deptLevel);
    }

    public List<Department> findDepartmentSalaryByDesc()
    {
        return repo.findDepartmentSalaryByDesc();
    }

//    public List<PercentageResponse> findDeptStrengthAtEveryLevel()
//    {
//        List<PercentageResponse> list = repo.percentageOFEmployeeInDepartment();
//        System.out.println(list);
//        return repo.percentageOFEmployeeInDepartment();
//    }
    public List<ResponsePercent> findDeptStrengthAtEveryLevel()
    {
        return repo.percentageOFEmployeeInDepartment();
    }


//    public Map<String,Long> findDeptStrengthAtEveryLevel()
//    {
//        Map<String,Long> map = new HashMap<>();
//        Long empCount = employeeRepository.getEmployeeCount();
//        List<Department> departments = repo.
//        map.put(departments, empCount);
//        return map;
//    }


}
