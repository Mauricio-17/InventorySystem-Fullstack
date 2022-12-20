package com.mauricio.inventory.employee;

import com.mauricio.inventory.area.Area;
import com.mauricio.inventory.area.AreaRepository;
import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import com.mauricio.inventory.role.RoleRepository;
import com.mauricio.inventory.views.CompletedEmployee;
import com.mauricio.inventory.views.CompletedEmployeeRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.AllArgsConstructor;
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

    public void dataValidation(Employee employee){
        Optional<Area> area = areaRepository.findById(employee.getArea().getId());
        if(area.isEmpty()){
            throw new BadRequestException("Dato del Área inválido");
        }
        if(employeeRepository.existsEmail(employee.getEmail())){
            throw new BadRequestException("La contraseña o el email ya se encuentran registrados");
        }
    }

    public List<Employee> getAllItems(){
        return employeeRepository.findAll();
    }

    public List<CompletedEmployee> getAllCompletedEmployees(){
        return (List<CompletedEmployee>) completedEmployeeRepository.findAll();
    }

    public Employee getItem(Long id){
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Empleado con el ID %s no encontrada", id))
        );
        return foundEmployee;
    }
    public void addItem(Employee employee, Long roleId){
        // Hashing the password
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, employee.getPassword().getBytes());
        employee.setPassword(hash);

        dataValidation(employee);
        roleRepository.findById(roleId).map(role -> {
           employee.setRole(role);
           return employeeRepository.save(employee);
        }).orElseThrow(() ->
                new BadRequestException("Dato del Área inválido")
        );
    }

    public void updateItem(Employee employee, Long id){
        dataValidation(employee);
        employeeRepository.findById(id).map(emp -> {
            emp.setName(employee.getName());
            emp.setLastName(employee.getLastName());
            emp.setStatus(employee.getStatus());
            emp.setPassword(employee.getPassword());
            emp.setEmail(employee.getEmail());
            emp.setArea(employee.getArea());
            emp.setRole(employee.getRole());
            return employeeRepository.save(emp);
        }).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Empleado con el ID %s no encontrada", id))
        );
    }

    public void removeItem(Long id){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isEmpty()){
            throw new ResourceNotFoundException(String.format("Empleado con el ID %s no encontrada", id));
        }
        employeeRepository.delete(foundEmployee.get());
    }
}
