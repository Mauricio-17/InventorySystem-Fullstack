package com.mauricio.inventory.typeowner;

import com.mauricio.inventory.shelf.Shelf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/type")
public class TypeController {

    private TypeService typeService;

    @GetMapping
    public List<TypeOwner> getAllTypes(@RequestHeader(value = "Authorization") String token){
        return typeService.getAllTypes(token);
    }

    @PostMapping
    public ResponseEntity<Void> addTypeOwner(@Valid @RequestBody TypeOwner typeOwner, @RequestHeader(value = "Authorization") String token){
        typeService.addType(typeOwner, token);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateType(@Valid @RequestBody TypeOwner type, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        typeService.updateItem(type, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        typeService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
