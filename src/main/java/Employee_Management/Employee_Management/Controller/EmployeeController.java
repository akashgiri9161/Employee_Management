package Employee_Management.Employee_Management.Controller;

import Employee_Management.Employee_Management.Entity.EmployeeEntity;
import Employee_Management.Employee_Management.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);

        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + " not found");
        }
    }


    @PostMapping
    public ResponseEntity<EmployeeEntity> saveEmployee(@RequestBody EmployeeEntity employee) {
        EmployeeEntity savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity updatedEmployee) {
        Optional<EmployeeEntity> existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee.isPresent()) {
            // Set the ID of the existing employee to the updatedEmployee
            updatedEmployee.setId(id);

            // Update the existing employee with the new information
            EmployeeEntity savedEmployee = employeeService.saveEmployee(updatedEmployee);
            return ResponseEntity.ok("Employee data updated successfully");
        } else {
            // Employee with the specified ID not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + " not found");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Optional<EmployeeEntity> existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee.isPresent()) {
            // Employee found, delete it
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee Deleted Successfully");
        } else {
            // Employee with the specified ID not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + " not found");
        }
    }

}