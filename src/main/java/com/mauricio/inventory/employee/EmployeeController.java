package com.mauricio.inventory.employee;

import com.mauricio.inventory.category.Category;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllItems();
    }

    @PostMapping("/role/{roleId}/employee")
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody Employee employee,
                                      @PathVariable(value = "roleId") Long roleId){
        employeeService.addItem(employee, roleId);
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
