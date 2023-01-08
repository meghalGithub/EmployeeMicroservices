package com.restapi.EmployeeMicroservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select * from employee where emp_sal = (select Max(emp_sal) from employee)",nativeQuery = true)
    public List<Employee> getHighestSalaryEmployee();

    @Query(value = "select * from where emp_sal>50000)", nativeQuery = true)
    public List<Employee> salaryGreaterThan();
}
