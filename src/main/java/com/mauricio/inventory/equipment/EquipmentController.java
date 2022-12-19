package com.mauricio.inventory.equipment;

import com.mauricio.inventory.category.Category;
import lombok.AllArgsConstructor;
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
    public List<Equipment> getAllEquipments(){
        return equipmentService.getAllItems();
    }

    @PostMapping
    public ResponseEntity<Void> addEquipment(@Valid @RequestBody Equipment equipment){
        equipmentService.addItem(equipment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEquipment(@Valid @RequestBody Equipment equipment, @PathVariable(value = "id") Long id){
        equipmentService.updateItem(equipment, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable(value = "id") Long id){
        equipmentService.removeItem(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
