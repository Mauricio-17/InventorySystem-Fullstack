package com.mauricio.inventory.employee;

import com.mauricio.inventory.area.Area;
import com.mauricio.inventory.area.AreaRepository;
import com.mauricio.inventory.auth.JWTUtil;
import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import com.mauricio.inventory.exceptions.UnauthorizedRequestException;
import com.mauricio.inventory.role.Role;
import com.mauricio.inventory.role.RoleRepository;
import com.mauricio.inventory.views.CompletedEmployee;
import com.mauricio.inventory.views.CompletedEmployeeRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AreaRepository areaRepository;
    private final RoleRepository roleRepository;
    private final CompletedEmployeeRepository completedEmployeeRepository;

    private final JWTUtil jwtUtil;
    private String tokenValidation(String token){
        String employeeId = jwtUtil.getKey(token);
        System.out.println(jwtUtil.getValue(token));
        System.out.println(employeeId);
        if(employeeId == null || employeeId.equals("")){
            throw new UnauthorizedRequestException("Sin Autorización");
        }
        return jwtUtil.getValue(token);
    }

    public void dataValidation(Employee employee){
        Optional<Area> area = areaRepository.findById(employee.getArea().getId());
        if(area.isEmpty()){
            throw new BadRequestException("Dato del Área inválido");
        }
        Optional<Role> role = roleRepository.findById(employee.getRole().getId());
        if(role.isEmpty()){
            throw new BadRequestException("Dato del Rol inválido");
        }
    }

    public List<Employee> getAllItems(String token){
        tokenValidation(token);
        return employeeRepository.findAll();
    }

    public CompletedEmployee getEmployeeByCredentials(Employee employee){
        String email = employee.getEmail();
        Optional<CompletedEmployee> emp = Optional.ofNullable(completedEmployeeRepository.findByEmail(email));
        if (emp.isPresent()){
            String hashedPassword = emp.get().getPassword();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            // Revisando si ambas contraseñas coinciden
            if(argon2.verify(hashedPassword, employee.getPassword().getBytes())){
                return emp.get();
            }
        }
        throw new ResourceNotFoundException("Email o contraseña inválido");
    }

    public Page<CompletedEmployee> getAllCompletedEmployees(String token, Pageable page){
        tokenValidation(token);
        return completedEmployeeRepository.findAll(page);
    }

    public Employee getItem(Long id, String token){
        tokenValidation(token);
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Empleado con el ID %s no encontrada", id))
        );
        return foundEmployee;
    }
    public void addItem(Employee employee, String token){
        String role = tokenValidation(token);
        if(employeeRepository.existsEmail(employee.getEmail())){
            throw new BadRequestException("La contraseña o el email ya se encuentran registrados");
        }
        if ( !role.equalsIgnoreCase("SUPER_ADMIN")){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        // Hashing the password
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, employee.getPassword().getBytes());
        employee.setPassword(hash);

        dataValidation(employee);
        employeeRepository.save(employee);
    }

    public void updateItem(Employee employee, Long id, String token){
        tokenValidation(token);
        dataValidation(employee);
        employeeRepository.findById(id).map(emp -> {
            emp.setName(employee.getName());
            emp.setLastName(employee.getLastName());
            emp.setStatus(employee.getStatus());
            emp.setEmail(employee.getEmail());
            emp.setArea(employee.getArea());
            emp.setRole(employee.getRole());
            return employeeRepository.save(emp);
        }).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Empleado con el ID %s no encontrada", id))
        );
    }

    public void removeItem(Long id, String token){
        tokenValidation(token);
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isEmpty()){
            throw new ResourceNotFoundException(String.format("Empleado con el ID %s no encontrada", id));
        }
        employeeRepository.delete(foundEmployee.get());
    }
}
