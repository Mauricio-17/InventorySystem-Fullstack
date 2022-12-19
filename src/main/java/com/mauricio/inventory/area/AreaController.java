package com.mauricio.inventory.area;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/area")
@AllArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping
    public List<Area> getAllAreas(){
        return areaService.getAllItems();
    }

    @PostMapping
    public ResponseEntity<Void> addArea(@Valid @RequestBody Area area){
        areaService.addItem(area);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArea(@Valid @RequestBody Area area, @PathVariable(value = "id") Long id){
        areaService.updateItem(area, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable("id") Long id){
        areaService.removeArea(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
