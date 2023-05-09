package com.spring.basics.service;

import com.spring.basics.exception.SalaryException;
import com.spring.basics.model.Department;
import com.spring.basics.model.Salary;
import com.spring.basics.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class SalaryService
{
    @Autowired
    SalaryRepository repo;
    public Salary create(Salary salary)
    {
        return repo.save(salary);
    }

    public Salary update(int salaryId,Salary salary)
    {
        Salary salary1 = repo.findById(salaryId).get();
        if(repo.findById(salaryId).isPresent())
        {
            salary1.setSalary(salary.getSalary());
            repo.save(salary1);
            return salary1;
        }
        else
        {
            throw new SalaryException("ID_NOT_FOUND");
        }
    }

    public List<Salary> read()
    {
        List<Salary> list = repo.findAll();
        if(repo.findAll().isEmpty())
        {
            throw new SalaryException("OOPS..! Empty DATA-BASE...");
        }
        else
        {
            return list;
        }
    }

    public void delete(int salaryId)
    {
        Salary salary = repo.findById(salaryId).get();
        if(repo.findById(salaryId).isPresent())
        {
            repo.deleteById(salaryId);
        }
        else
        {
            throw new SalaryException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }

    public List<Salary> salaryBetween(String start,String end)
    {
        return repo.salaryBetween(start,end);
    }


}
