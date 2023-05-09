package com.spring.basics.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

//   Without Using Mapped By,Always Use JASON back & Manage Reference when using Bi-directional Mapping
//   @OneToOne(cascade = CascadeType.ALL)
//   @JoinColumn(name = "salary_id",referencedColumnName = "salaryId")
//   private Salary salaried;

    @JsonManagedReference
    @OneToOne(mappedBy = "depart",cascade = CascadeType.ALL)
    private Salary salaried;



}
