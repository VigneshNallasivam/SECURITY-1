package com.spring.basics.controller;

import com.spring.basics.model.Department;
import com.spring.basics.response.Response;
import com.spring.basics.response.ResponseHandler;
import com.spring.basics.response.ResponsePercent;
import com.spring.basics.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentController
{
    @Autowired
    DepartmentService service;

    @GetMapping("/getDepartment")
    public String display(Department department)
    {
        return "<h1><b><i>Department : </b></i></h1>"+department.toString();
    }
    @PostMapping("/deptPost")
    public ResponseEntity<Object> create(@RequestBody Department department)
    {
        Department department1 = service.create(department);
        Response response = new Response("Data is Created..!!",department1);
        return ResponseHandler.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,response);
    }

    @PutMapping("/deptPut/{deptId}")
    public ResponseEntity<Object> update(@PathVariable int deptId,@RequestBody Department department)
    {
        Department department1 = service.update(deptId,department);
        Response response = new Response("Data is Updated..!!",department1);
        return ResponseHandler.generateResponse("Data Updation = Success",true, HttpStatus.OK,response);
    }
    @GetMapping("/deptGet")
    public ResponseEntity<Object> read()
    {
        List<Department> department = service.read();
        Response response = new Response("Data is Fetched/Readed..!!",department);
        return ResponseHandler.generateResponse("Data Reading = Success",true, HttpStatus.FOUND,response);
    }
    @DeleteMapping("/deptDelete/{deptId}")
    public ResponseEntity<Object> delete(@PathVariable int deptId)
    {
        service.delete(deptId);
        Response response = new Response("Data is Deleted..!!","Success..!!");
        return ResponseHandler.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,response);
    }

    @GetMapping("/deptSortByLevelAsc")
    public List<Department> getByLevelAsc()
    {
        return service.getByDeptLevelAsc();
    }
    @GetMapping("/deptSortByLevelDesc")
    public List<Department> getByLevelDesc()
    {
        return service.getByDeptLevelDesc();
    }
    @GetMapping("/getByDeptLevelGreaterThan")
    public List<Department> getByDeptLevel(@RequestParam String deptLevel)
    {
        return service.findByLevelGreaterThan(deptLevel);
    }
    @GetMapping("/getByDeptName/{deptName}")
    public List<Department> getByDeptName(@PathVariable String deptName)
    {
        return service.getDeptName(deptName);
    }

    @GetMapping("/getByDeptNameOrLevel/{deptName}/{deptLevel}")
    public List<Department> getByDeptNameOrLevels(@PathVariable String deptName,@PathVariable String deptLevel)
    {
        return service.getDeptNameOrDeptLevel(deptName,deptLevel);
    }

    @GetMapping("/getByDeptSalaryLevel")
    public List<Department> getByDeptSalaryLevels()
    {
        return service.findDepartmentSalaryByDesc();
    }
    @GetMapping("/getDeptStrengthAtEachLevel")
    public List<ResponsePercent> getDeptStrength()
    {
        return service.findDeptStrengthAtEveryLevel();
    }


}
