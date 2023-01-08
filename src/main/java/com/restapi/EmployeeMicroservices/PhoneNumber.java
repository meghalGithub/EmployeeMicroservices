package com.restapi.EmployeeMicroservices;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Data
@FieldNameConstants
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneId;
    private long mobileNumber;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="empid", nullable = false)
    private Employee employee;
}
