package com.mauricio.inventory.equipment;

import com.mauricio.inventory.category.Category;
import com.mauricio.inventory.views.CompletedEquipment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@AllArgsConstructor
public class EquipmentController {

    private EquipmentService equipmentService;

    @GetMapping
    public List<Equipment> getAllEquipments(@RequestHeader(value = "Authorization") String token){
        return equipmentService.getAllItems(token);
    }

    @GetMapping("/view")
    public Page<CompletedEquipment> getAllCompletedEquipments(@RequestHeader(value = "Authorization") String token, Pageable page){
        return equipmentService.getAllCompletedEquipments(token, page);
    }

    @GetMapping("/{id}")
    public Equipment getEquipment(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        return equipmentService.getItem(id, token);
    }

    @PostMapping
    public ResponseEntity<Void> addEquipment(@Valid @RequestBody Equipment equipment, @RequestHeader(value = "Authorization") String token){
        equipmentService.addItem(equipment, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEquipment(@Valid @RequestBody Equipment equipment, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        equipmentService.updateItem(equipment, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        equipmentService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
