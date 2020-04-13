package com.ejc.mirage.http;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import com.ejc.mirage.cassandra.Employee;
import com.ejc.mirage.cassandra.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostConstruct
    public void saveEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(123, "John Doe", "Delaware", "jdoe@xyz.com", 31));
        employees.add(new Employee(324, "Adam Smith", "North Carolina", "asmith@xyz.com", 43));
        employees.add(new Employee(355, "Kevin Dunner", "Virginia", "kdunner@xyz.com", 24));
        employees.add(new Employee(643, "Mike Lauren", "New York", "mlauren@xyz.com", 41));
        employeeService.initializeEmployees(employees);
    }

    @GetMapping("/list")
    public Flux<Employee> getAllEmployees() {
        Flux<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }
    
    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/filterByAge/{age}")
    public Flux<Employee> getEmployeesFilterByAge(@PathVariable int age) {
        return employeeService.getEmployeesFilterByAge(age);
    }

    @PostMapping("/add")
    public Mono<Employee> addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/deleteAll")
    public Mono<Void> deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }
}
