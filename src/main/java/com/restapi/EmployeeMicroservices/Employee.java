package com.restapi.EmployeeMicroservices;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@FieldNameConstants
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empID;
    private String empName;
    private int empSal;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = PhoneNumber.Fields.employee, orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<PhoneNumber> phoneNumber;
}
