package com.mauricio.inventory.area;

import com.mauricio.inventory.auth.JWTUtil;
import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import com.mauricio.inventory.exceptions.UnauthorizedRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AreaService {

    private AreaRepository areaRepository;
    private final JWTUtil jwtUtil;

    private String tokenValidation(String token){
        String employeeId = jwtUtil.getKey(token);
        if(employeeId == null || employeeId.equals("")){
            throw new UnauthorizedRequestException("Sin Autorización");
        }
        return jwtUtil.getValue(token);
    }

    public List<Area> getAllItems(String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }
        return areaRepository.findAll();
    }

    public void addItem(Area area, String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        String name = area.getName();
        if (areaRepository.existsName(name)){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",name));
        }
        areaRepository.save(area);
    }

    public Area getItem(Long id, String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        Area area = areaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Area con el ID %s no encontrada", id))
        );
        return area;
    }

    public void removeArea(Long id, String token) {
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        Area area = areaRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException(String.format("Area con el ID %s no encontrada", id))
        );
        areaRepository.delete(area);
    }

    public void updateItem(Area area, Long id, String token){
        String userRole = tokenValidation(token);
        if ( !userRole.equalsIgnoreCase("SUPER_ADMIN") ){
            throw new UnauthorizedRequestException("Sin Autorización");
        }

        String name = area.getName();
        if (areaRepository.existsName(name)){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",name));
        }
        Area foundArea = areaRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException(String.format("Area con el ID %s no encontrada", id))
        );
        foundArea.setName(area.getName());
        foundArea.setDescription(area.getDescription());
        areaRepository.save(foundArea);
    }

}
