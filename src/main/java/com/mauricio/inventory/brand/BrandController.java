package com.mauricio.inventory.brand;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
@AllArgsConstructor
public class BrandController {

    private BrandService brandService;

    @GetMapping
    public List<Brand> getAllBrands( @RequestHeader(value = "Authorization") String token){
        return brandService.getAllItems(token);
    }

    @PostMapping
    public ResponseEntity<Void> addBrand(@Valid @RequestBody Brand brand,  @RequestHeader(value = "Authorization") String token){
        brandService.addItem(brand, token);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBrand(@Valid @RequestBody Brand brand, @PathVariable(value = "id") Long id,  @RequestHeader(value = "Authorization") String token){
        brandService.updateItem(brand, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBrand(@PathVariable(value = "id") Long id,  @RequestHeader(value = "Authorization") String token){
        brandService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
