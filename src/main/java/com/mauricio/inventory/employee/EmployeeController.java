package com.mauricio.inventory.employee;

import com.mauricio.inventory.auth.JWTUtil;
import com.mauricio.inventory.views.CompletedEmployee;
import lombok.AllArgsConstructor;
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

    private final JWTUtil jwtUtil;

    private void tokenValidation(String token){
        String employeeId = jwtUtil.getKey(token);
        if(employeeId != null){
            // throw new ResourceNotAcceptableException("Sin Autorización");
        }

    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllItems();
    }

    /*@GetMapping("/email/{email}")
    public Employee getEmployeeByEmail(@PathVariable(value = "email") String email){
        return employeeService.getEmployeeByEmail(email);
    }*/

    @GetMapping("/{id}")
    public Employee getEquipment(@PathVariable(value = "id") Long id){
        return employeeService.getItem(id);
    }

    @GetMapping("/view")
    public List<CompletedEmployee> getAllCompletedEmployees(){
        return employeeService.getAllCompletedEmployees();
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody Employee employee){
        employeeService.addItem(employee);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable(value = "id") Long id){
        employeeService.updateItem(employee, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") Long id){
        employeeService.removeItem(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
