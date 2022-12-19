package com.mauricio.inventory.location;

import com.mauricio.inventory.equipment.Equipment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/location")
@AllArgsConstructor
public class LocationController {

    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations(){
        return locationService.getAllItems();
    }

    @PostMapping
    public ResponseEntity<Void> addLocation(@Valid @RequestBody Location location){
        locationService.addItem(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLocation(@Valid @RequestBody Location location, @PathVariable(value = "id") Long id){
        locationService.updateItem(location, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable(value = "id") Long id){
        locationService.removeItem(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
