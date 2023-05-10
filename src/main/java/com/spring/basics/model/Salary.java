package com.spring.basics.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "salaries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "salary_id")
    private int salaryId;
    private String accNo;
    private String salaryBankAccName;
    private String salaryBankIfsc;
    private String salaryPackage;
    private String salaryDate;
    private double salary;
    private String EPF;/**
                         Employee Provident Fund
                         CALCULATION:-
                         EPF = 12/100 * Employee Salary,
                         FOR Eg:-If Rs.10,000 is Employee Salary ,then EPF = Rs.1200**/

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id",referencedColumnName = "deptId")
    @JsonBackReference
    private Department depart;


}
