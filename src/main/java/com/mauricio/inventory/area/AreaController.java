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
    public List<Area> getAllAreas(@RequestHeader(value = "Authorization") String token){
        return areaService.getAllItems(token);
    }

    @PostMapping
    public ResponseEntity<Void> addArea(@Valid @RequestBody Area area, @RequestHeader(value = "Authorization") String token){
        areaService.addItem(area, token);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArea(@Valid @RequestBody Area area, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        areaService.updateItem(area, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable("id") Long id, @RequestHeader(value = "Authorization") String token){
        areaService.removeArea(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
