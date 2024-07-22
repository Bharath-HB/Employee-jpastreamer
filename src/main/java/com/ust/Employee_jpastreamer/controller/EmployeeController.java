package com.ust.Employee_jpastreamer.controller;

import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupbyEmployeeByCity(){
        return employeeService.groupbyEmployeeByCity();
    }
    @GetMapping("/findall")
    public List<Employee> groupbyEmployeeByPaymentTier(){
        return employeeService.groupbyEmployeeByPaymentTier();
    }

    @GetMapping("/age/{minAge}/{maxAge}")
    public List<Employee> findByAgeBetween(@PathVariable int minAge, @PathVariable int maxAge){
        return employeeService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/count/{gender}")
    public Long findByGender(@PathVariable String gender){
        return employeeService.findByGender(gender);
    }

    @GetMapping("/doj/{year}")
    public List<Employee> findByDoj(@PathVariable int year){
        return employeeService.findByDoj(year);
    }

    @GetMapping("/countGender/{year}")
    public Map<String, Long> countGenderByYear(@PathVariable int year){
        return employeeService.countGenderByYear(year);
    }

    @GetMapping("/groupByEducation")
    public Map<String, List<Employee>> groupByEducation(){
        return employeeService.groupByEducation();
    }

    @GetMapping("/filterByDetail/{gender}/{education}/{experience}")
    public List<Employee> filterByDetail(@RequestParam String gender,@RequestParam String education,@RequestParam int experience){
        return employeeService.findByGenderEducationExperience(gender,education,experience);
    }
}