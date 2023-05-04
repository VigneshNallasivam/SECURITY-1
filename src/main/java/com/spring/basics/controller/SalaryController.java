package com.spring.basics.controller;

import com.spring.basics.model.Salary;
import com.spring.basics.response.Response;
import com.spring.basics.response.ResponseHandler;
import com.spring.basics.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController
{
    @Autowired
    SalaryService service;

    @GetMapping("/getSalary")
    public String display(Salary salary)
    {
        return "<h1><b><i>Salary : </b></i></h1>"+salary.toString();
    }
    @PostMapping("/salaryPost")
    public ResponseEntity<Object> create(@RequestBody Salary salary)
    {
        Salary salary1 = service.create(salary);
        Response response = new Response("Data is Created..!!",salary1);
        return ResponseHandler.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,response);
    }

    @PutMapping("/salaryPut/{salaryId}")
    public ResponseEntity<Object> update(@PathVariable int salaryId,@RequestBody Salary salary)
    {
        Salary salary1 = service.update(salaryId,salary);
        Response response = new Response("Data is Updated..!!",salary1);
        return ResponseHandler.generateResponse("Data Updation = Success",true, HttpStatus.OK,response);
    }
    @GetMapping("/salaryGet")
    public ResponseEntity<Object> read()
    {
        List<Salary> salary = service.read();
        Response response = new Response("Data is Fetched/Readed..!!",salary);
        return ResponseHandler.generateResponse("Data Reading = Success",true, HttpStatus.FOUND,response);
    }
    @DeleteMapping("/salaryDelete/{empId}")
    public ResponseEntity<Object> delete(@PathVariable int salaryId)
    {
        service.delete(salaryId);
        Response response = new Response("Data is Deleted..!!","Success..!!");
        return ResponseHandler.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,response);
    }

}
