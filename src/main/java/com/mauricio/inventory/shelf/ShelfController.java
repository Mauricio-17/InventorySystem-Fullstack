package com.mauricio.inventory.shelf;

import com.mauricio.inventory.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shelf")
@AllArgsConstructor
public class ShelfController {

    private ShelfService shelfService;

    @GetMapping
    public List<Shelf> getAllShelves(@RequestHeader(value = "Authorization") String token){
        return shelfService.getAllItems(token);
    }

    @GetMapping("/location/{id}")
    public Shelf getShelfByLocationId(@PathVariable(value = "id") Long locationId, @RequestHeader(value = "Authorization") String token){
        return shelfService.getShelfByLocationId(locationId, token);
    }

    @PostMapping
    public ResponseEntity<Void> addShelf(@Valid @RequestBody Shelf shelf, @RequestHeader(value = "Authorization") String token){
        shelfService.addItem(shelf, token);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateShelf(@Valid @RequestBody Shelf shelf, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        shelfService.updateItem(shelf, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelf(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        shelfService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
