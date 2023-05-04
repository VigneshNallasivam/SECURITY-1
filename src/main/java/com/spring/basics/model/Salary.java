package com.spring.basics.model;

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
    private int salaryId;
    private String salary;
}
