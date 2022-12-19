package com.mauricio.inventory.area;

import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AreaService {

    private AreaRepository areaRepository;

    public List<Area> getAllItems(){
        return areaRepository.findAll();
    }

    public void addItem(Area area){
        String name = area.getName();
        if (areaRepository.existsName(name)){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",name));
        }
        areaRepository.save(area);
    }

    public Area getItem(Long id){
        Area area = areaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Area con el ID %s no encontrada", id))
        );
        return area;
    }

    public void removeArea(Long id) {
        Area area = areaRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException(String.format("Area con el ID %s no encontrada", id))
        );
        areaRepository.delete(area);
    }

    public void updateItem(Area area, Long id){
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
