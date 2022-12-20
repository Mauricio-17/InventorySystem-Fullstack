package com.mauricio.inventory.location;

import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import com.mauricio.inventory.shelf.Shelf;
import com.mauricio.inventory.shelf.ShelfRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private ShelfRepository shelfRepository;

    public void dataValidation(Location location){
        Optional<Shelf> shelf = shelfRepository.findById(location.getShelf().getId());
        if(shelf.isEmpty()){
            throw new BadRequestException("Dato del Rol inválido");
        }
        // this is for validating that a row doesn't encounter at the same shelf
        List<Location> foundLocations = shelf.get().getLocations();
        for (Location loc: foundLocations) {
            if(Objects.equals(loc.getRow(), location.getRow())){
                throw new BadRequestException("La fila ya se encuentra registrada en el estante "+ shelf.get().getName());
            }
        }
    }

    public List<Location> getAllItems(){
        return locationRepository.findAll();
    }

    public List<Location> getItemsByForeignId(Long shelfId){
        Optional<Shelf> foundShelf = shelfRepository.findById(shelfId);
        if (foundShelf.isPresent() && !foundShelf.get().getLocations().isEmpty()){
            return foundShelf.get().getLocations();
        }
        return new ArrayList<>();
    }

    public void addItem(Location location){
        this.dataValidation(location);
        try {
            locationRepository.save(location);
        }catch (DataIntegrityViolationException exception){
            exception.getCause();
        }

    }

    public void updateItem(Location location, Long id){
        dataValidation(location);
        locationRepository.findById(id).map(loc -> {
           loc.setRow(location.getRow());
           loc.setStatus(location.getStatus());
           loc.setShelf(location.getShelf());
           return locationRepository.save(loc);
        }).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Ubicación con el ID %s no encontrada", id))
        );
    }

    public void removeItem(Long id){
        Optional<Location> foundLocation = locationRepository.findById(id);
        if (foundLocation.isEmpty()){
            throw new ResourceNotFoundException(String.format("Ubicación con el ID %s no encontrado", id));
        }
        locationRepository.delete(foundLocation.get());
    }
}
