package com.spring.basics.controller;

import com.spring.basics.model.Employee;
import com.spring.basics.response.Response;
import com.spring.basics.response.ResponseHandler;
import com.spring.basics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")
public class EmployeeController
{
    @Autowired
    EmployeeService service;

    @GetMapping("/getEmployee")
    public String display(Employee employee)
    {
        return "<h1><b><i>Employee : </b></i></h1>"+employee.toString();
    }
    @PostMapping("/empPost")
    public ResponseEntity<Object> create(@RequestBody Employee employee)
    {
        Employee employee1 = service.create(employee);
        Response response = new Response("Data is Created..!!",employee1);
        return ResponseHandler.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,response);
    }

    @PutMapping("/empPut/{empId}")
    public ResponseEntity<Object> update(@PathVariable int empId,@RequestBody Employee employee)
    {
        Employee employee1 = service.update(empId,employee);
        Response response = new Response("Data is Updated..!!",employee1);
        return ResponseHandler.generateResponse("Data Updation = Success",true, HttpStatus.OK,response);
    }
    @GetMapping("/empGet")
    public ResponseEntity<Object> read()
    {
        List<Employee> employee = service.read();
        Response response = new Response("Data is Fetched/Readed..!!",employee);
        return ResponseHandler.generateResponse("Data Reading = Success",true, HttpStatus.FOUND,response);
    }
    @DeleteMapping("/empDelete/{empId}")
    public ResponseEntity<Object> delete(@PathVariable int empId)
    {
        service.delete(empId);
        Response response = new Response("Data is Deleted..!!","Success..!!");
        return ResponseHandler.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,response);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Response> getNames(@PathVariable String name)
    {
        Optional<Employee> models = service.getNames(name);
        Response responseDto = new Response("Name Obtained!",models);
        return new ResponseEntity<>(responseDto,HttpStatus.FOUND);
    }
    @GetMapping("/getById/{empId}")
    public ResponseEntity<Response> getNames(@PathVariable int empId)
    {
        Employee employee = service.getById(empId);
        Response responseDto = new Response("Name Obtained!",employee);
        return new ResponseEntity<>(responseDto,HttpStatus.FOUND);
    }

}
