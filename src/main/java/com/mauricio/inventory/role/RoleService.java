package com.mauricio.inventory.role;

import com.mauricio.inventory.auth.JWTUtil;
import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import com.mauricio.inventory.exceptions.UnauthorizedRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository roleRepository;
    private final JWTUtil jwtUtil;

    private String tokenValidation(String token){
        String employeeId = jwtUtil.getKey(token);
        if(employeeId == null || employeeId.equals("")){
            throw new UnauthorizedRequestException("Sin Autorización");
        }
        return jwtUtil.getValue(token);
    }

    public List<Role> getAllRoles( String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        return roleRepository.findAll();
    }

    public void addItem(Role role, String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        String name = role.getName();
        if (roleRepository.existsName(name)){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",name));
        }
        roleRepository.save(role);
    }

    public void updateItem(Role foundRole, Long id, String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        if (roleRepository.existsName(foundRole.getName())){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",foundRole.getName()));
        }
        roleRepository.findById(id).map(role -> {
            role.setName(foundRole.getName());
            role.setDescription(foundRole.getDescription());
            return roleRepository.save(role);
        }).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Rol con el ID %s no encontrada", id))
        );
    }

    public void removeItem(Long id, String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        Role foundRole = roleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Rol con el ID %s no encontrada", id))
        );
        roleRepository.delete(foundRole);
    }
}
