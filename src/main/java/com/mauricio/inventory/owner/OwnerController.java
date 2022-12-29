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
    public List<Owner> getAllOwners(@RequestHeader(value = "Authorization") String token){
        return ownerService.getAllOwners(token);
    }

    @PostMapping
    public ResponseEntity<Void> addOwner(@Valid @RequestBody Owner owner, @RequestHeader(value = "Authorization") String token){
        ownerService.addItem(owner, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOwner(@Valid @RequestBody Owner owner, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        ownerService.updateItem(owner, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        ownerService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
