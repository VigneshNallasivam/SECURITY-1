package com.spring.basics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deptId;
    private String deptName;
    private String deptStrength;
    private String deptLevel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id",referencedColumnName = "emp_id")
    private Employee employee;
}
