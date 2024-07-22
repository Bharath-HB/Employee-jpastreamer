package com.ust.Employee_jpastreamer.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.repository.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service

public class EmployeeService {
    @Autowired
    private Employeerepo employeeRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> groupbyEmployeeByPaymentTier() {
        return employeeRepository.findAll();
    }

    public List<Employee> saveEmployee(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

    public List<Employee> findByAgeBetween(int minAge, int maxAge) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getAge() >= minAge && employee.getAge() <= maxAge)
                .collect(Collectors.toList());

    }

    public Long findByGender(String gender) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .count();
    }

    public List<Employee> findByDoj(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .collect(Collectors.toList());
    }

    public Map<String, Long> countGenderByYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
    }

    public Map<String, List<Employee>> groupByEducation() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getEducation));

        }

    public List<Employee> findByGenderEducationExperience(String gender, String education, int experience) {
        return jpaStreamer.stream(Employee.class)
               .filter(employee -> employee.getGender().equalsIgnoreCase(gender) && employee.getEducation().equalsIgnoreCase(education) && employee.getExperienceInCurrentDomain() >= experience)
               .collect(Collectors.toList());
    }
}


