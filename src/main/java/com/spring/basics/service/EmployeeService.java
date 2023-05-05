package com.spring.basics.service;

import com.spring.basics.exception.EmployeeException;
import com.spring.basics.model.Department;
import com.spring.basics.model.Employee;
import com.spring.basics.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService
{
    @Autowired
    EmployeeRepository repo;
    public Employee create(Employee employee)
    {
        return repo.save(employee);
    }

    public Employee update(int empId,Employee employee)
    {
        Employee employee1 = repo.findById(empId).get();
        if(repo.findById(empId).isPresent())
        {
            employee1.setName(employee.getName());
            employee1.setGender(employee.getGender());
            employee1.setAge(employee.getAge());
            employee1.setAddress(employee.getAddress());
            employee1.setMobile(employee.getMobile());
            employee1.setMail(employee.getMail());
            employee1.setPassword(employee.getPassword());
            repo.save(employee1);
            return employee1;
        }
        else
        {
            throw new EmployeeException("ID_NOT_FOUND");
        }
    }

    public List<Employee> read()
    {
        List<Employee> list = repo.findAll();
        if(repo.findAll().isEmpty())
        {
            throw new EmployeeException("OOPS..! Empty DATA-BASE...");
        }
        else
        {
            return list;
        }
    }

    public void delete(int empId)
    {
        Employee employee = repo.findById(empId).get();
        if(repo.findById(empId).isPresent())
        {
            repo.deleteById(empId);
        }
        else
        {
            throw new EmployeeException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }

    public Optional<Employee> getNames(String name)
    {
            Optional<Employee> models = repo.findByName(name);
        if(repo.findByName(name).isEmpty())
        {
            throw new EmployeeException("Name Not Found!!");
        }
        else {
            return models;
        }
    }

    public Employee getById(int empId)
    {
        Employee employee = repo.findById(empId).get();
        if(repo.findById(empId).isPresent())
        {
            return employee;
        }
        else
        {
            throw new EmployeeException("Id Not Found!!");
        }

    }
}
