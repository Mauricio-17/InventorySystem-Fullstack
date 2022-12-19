package com.mauricio.inventory.owner;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/owner")
public class OwnerController {

    private OwnerService ownerService;

    @GetMapping
    public List<Owner> getAllOwners(){
        return ownerService.getAllOwners();
    }

    @PostMapping
    public ResponseEntity<Void> addOwner(@Valid @RequestBody Owner owner){
        ownerService.addItem(owner);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOwner(@Valid @RequestBody Owner owner, @PathVariable(value = "id") Long id){
        ownerService.updateItem(owner, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable(value = "id") Long id){
        ownerService.removeItem(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
