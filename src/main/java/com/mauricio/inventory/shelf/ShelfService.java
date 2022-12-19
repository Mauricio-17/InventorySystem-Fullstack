package com.mauricio.inventory.shelf;

import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShelfService {

    private ShelfRepository shelfRepository;

    public List<Shelf> getAllItems(){
        return shelfRepository.findAll();
    }

    public void addItem(Shelf shelf){
        String name = shelf.getName();
        if(shelfRepository.existsNameOrSerial(name, shelf.getSerial())){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",name));
        }
        shelfRepository.save(shelf);
    }

    public void updateItem(Shelf shelf, Long id){
        String name = shelf.getName();
        if(shelfRepository.existsNameOrSerial(name, shelf.getSerial())){
            throw new BadRequestException(String.format("El nombre {%s} ya existe",name));
        }
        shelfRepository.findById(id).map(shelf1 -> {
            shelf1.setName(shelf.getName());
            shelf1.setDescription(shelf.getDescription());
            shelf1.setSerial(shelf.getSerial());
            return shelfRepository.save(shelf1);
        }).orElseThrow(()->
                new ResourceNotFoundException(String.format("Estante con el ID %s no encontrada", id))
        );
    }

    public void removeItem(Long id){
        Optional<Shelf> foundShelf = shelfRepository.findById(id);
        if (foundShelf.isEmpty()){
            throw new ResourceNotFoundException(String.format("Estante con el ID %s no encontrado", id));
        }
        shelfRepository.delete(foundShelf.get());
    }
}