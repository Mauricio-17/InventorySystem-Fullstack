package com.mauricio.inventory.auth;

import com.mauricio.inventory.employee.Employee;
import com.mauricio.inventory.employee.EmployeeService;
import com.mauricio.inventory.views.CompletedEmployee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private JWTUtil jwtUtil;
    private EmployeeService employeeService;

    @PostMapping("/login")
    public String login (@RequestBody Employee employee){
        CompletedEmployee foundEmployee = employeeService.getEmployeeByCredentials(employee);
        String jwt = jwtUtil.create(String.valueOf(foundEmployee.getEmployeeId()), foundEmployee.getNameRole());
        return jwt;
    }
}
