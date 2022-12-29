package com.mauricio.inventory.typeowner;

import com.mauricio.inventory.auth.JWTUtil;
import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import com.mauricio.inventory.exceptions.UnauthorizedRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TypeService {

    private TypeOwnerRepository typeOwnerRepository;
    private final JWTUtil jwtUtil;
    private String tokenValidation(String token){
        String employeeId = jwtUtil.getKey(token);
        if(employeeId == null || employeeId.equals("")){
            throw new UnauthorizedRequestException("Sin Autorización");
        }
        return jwtUtil.getValue(token);
    }

    public List<TypeOwner> getAllTypes(String token){
        tokenValidation(token);
        return typeOwnerRepository.findAll();
    }

    public void addType(TypeOwner typeOwner, String token){
        tokenValidation(token);

        String name = typeOwner.getName();
        if (typeOwnerRepository.existsName(name)){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",name));
        }
        typeOwnerRepository.save(typeOwner);
    }

    public void updateItem(TypeOwner typeOwner, Long id, String token){
        tokenValidation(token);

        typeOwnerRepository.findById(id).map( type -> {
            type.setName(typeOwner.getName());
            type.setDescription(typeOwner.getDescription());
            return typeOwnerRepository.save(type);
        }).orElseThrow(() ->
                new ResourceNotFoundException(String.format("El tipo de propietario con el ID %s no encontrada", id))
        );
    }

    public void removeItem(Long id, String token){
        tokenValidation(token);

        Optional<TypeOwner> foundOwner = typeOwnerRepository.findById(id);
        if (foundOwner.isEmpty()){
            throw new ResourceNotFoundException(String.format("Tipo de propietario con el " +
                    "ID %s no encontrado", id));
        }
        typeOwnerRepository.delete(foundOwner.get());
    }
}
