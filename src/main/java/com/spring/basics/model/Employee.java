package com.spring.basics.model;

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
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private int empId;
    private String name;
    private String gender;
    private String age;
    private String address;
    private String mobile;
    private String mail;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "emp_roles", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Employee(String name,String mail,String password)
    {
        this.name=name;
        this.mail=mail;
        this.password=password;
    }
}
