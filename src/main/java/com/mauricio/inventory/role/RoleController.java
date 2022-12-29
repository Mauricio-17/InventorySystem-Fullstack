package com.mauricio.inventory.role;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles(@RequestHeader(value = "Authorization") String token){
        return roleService.getAllRoles(token);
    }

    @PostMapping
    public ResponseEntity<Void> addRole(@Valid @RequestBody Role role, @RequestHeader(value = "Authorization") String token){
        roleService.addItem(role, token);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRole(@Valid @RequestBody Role role, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        roleService.updateItem(role, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        roleService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
