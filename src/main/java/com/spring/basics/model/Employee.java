package com.spring.basics.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private int empId;
    private String name;
    private String gender;
    private String age;
    private String domain;
    private String designation;
    private String address;
    private String mobile;
    private String mail;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "emp_roles", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id",referencedColumnName = "deptId")
    @JsonBackReference
    private Department department;

    public Employee(String name,String mail,String password)
    {
        this.name=name;
        this.mail=mail;
        this.password=password;
    }
}
