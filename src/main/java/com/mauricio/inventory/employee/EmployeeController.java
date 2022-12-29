package com.mauricio.inventory.employee;

import com.mauricio.inventory.auth.JWTUtil;
import com.mauricio.inventory.views.CompletedEmployee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public List<Employee> getAllEmployees(@RequestHeader(value = "Authorization") String token){
        return employeeService.getAllItems(token);
    }

    /*@GetMapping("/email/{email}")
    public Employee getEmployeeByEmail(@PathVariable(value = "email") String email){
        return employeeService.getEmployeeByEmail(email);
    }*/

    @GetMapping("/{id}")
    public Employee getEquipment(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        return employeeService.getItem(id, token);
    }

    @GetMapping("/view")
    public Page<CompletedEmployee> getAllCompletedEmployees(@RequestHeader(value = "Authorization") String token, Pageable page){
        return employeeService.getAllCompletedEmployees(token, page);
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody Employee employee, @RequestHeader(value = "Authorization") String token){
        employeeService.addItem(employee, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        employeeService.updateItem(employee, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        employeeService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
