package com.restapi.EmployeeMicroservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
    public class EmployeeController {
        @Autowired
        private EmployeeRepository employeeRepository;

        @GetMapping("/get_msg/{userName}")
        public String getMsg(@PathVariable String userName){
            Date date = new Date();
            String msg = null;
            SimpleDateFormat time =  new SimpleDateFormat("hh:mm aa");
            String currentTime = time.format(date);

            SimpleDateFormat hour = new SimpleDateFormat("hh");
            SimpleDateFormat ampm = new SimpleDateFormat("aa");
            int hh = Integer.parseInt(hour.format(date));
            String aa = ampm.format(date);

            if ((hh >= 4 && hh < 12) && (aa.equals("AM"))) {
                msg = "Good Morning ";
            }
            else if ((hh >= 12 || hh < 4) && (aa.equals("PM"))) {
                msg = "Good Afternoon ";
            }
            else if ((hh >= 4 && hh < 8) && (aa.equals("PM"))) {
                msg = "Good Evening";
            }
            else if ((hh >= 8 || hh < 4) && (aa.equals("PM")) || (hh >= 8 || hh < 4) && (aa.equals("AM"))) {
                msg = "Good Night";
            }
            return msg + " " + userName + "\nTime : " + currentTime;
         }

        @PostMapping("/create_employee")
        public Employee creatEmployee(@RequestBody Employee employee){
            return employeeRepository.save(employee);
        }

        @GetMapping("/find_employee_by_id/{empID}")
        public String getEmployeeById(@PathVariable int empID){

            // 2nd approach with cache
            Employee employee = CacheManager.cache.get(empID);
            if(employee!=null)
                return employee.toString();
            else
                return "Employee is not available for this ID -> "+empID;

            // Using this we can target specific primary key ID
            // Optional is used to avoid null pointer exception
            // 1st approach without cache

//            Optional<Employee> employee = employeeRepository.findById(empID);
//            if(employee.isPresent())
//                return employee.get().toString();
//            else
//                return "Employee is not available for this "+empID+"ID";
        }

        @GetMapping("/find_all_employees")
        public List<Employee> getAllEmployees(){

          //1. without cache
          return employeeRepository.findAll();

         //2. with cache
         // return new ArrayList<>(CacheManager.cache.values());
         // return CacheManager.cache.values().stream().collect(Collections.list());
         // return CacheManager.cache.values().stream().collect(Collections.tolist());
        }

        @PutMapping("/update_employee/{empID}")
        public String updateEmployee(@PathVariable int empID, @RequestBody Employee employee){

            Optional<Employee> oldEmployee = employeeRepository.findById(empID);
            if(oldEmployee.isPresent()) {
                // This type of update is called as "Transformation"
                oldEmployee.get().setEmpName(employee.getEmpName());
                oldEmployee.get().setEmpSal(employee.getEmpSal());
                return employeeRepository.save(oldEmployee.get()).toString();

            /* This is not recommended, it will update all fields
            and if record not present, it will create new record*/
            // return employeeRepository.save(employee).toString();
            }
            else{
                return "Can not find Employee with this "+empID+" ID to update";
            }
        }

        @DeleteMapping("/delete_employee/{empID}")
        public String deleteEmployee(@PathVariable int empID){
            Optional<Employee> employee = employeeRepository.findById(empID);
            if(employee.isPresent()){
                employeeRepository.deleteById(empID);
                return "Employee with "+empID+" is deleted successfully from the DB";
            }
            else{
                return "Employee with "+empID+" is not present in the DB so unable to delete it";
            }
        }

       @GetMapping("/get_highest_salary_employee")
       public List<Employee> getHighestSalaryEmployee(){
            return employeeRepository.getHighestSalaryEmployee();
       }

    @GetMapping("/salary_greater_than")
    public List<Employee> salaryGreaterThan(){
            return employeeRepository.getHighestSalaryEmployee();
    }

    }