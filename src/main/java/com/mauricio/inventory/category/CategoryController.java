package com.mauricio.inventory.category;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories(@RequestHeader(value = "Authorization") String token){
        return categoryService.getAllItems(token);
    }
    @PostMapping
    public ResponseEntity<Void> addCategory(@Valid @RequestBody Category category, @RequestHeader(value = "Authorization") String token){
        categoryService.addItem(category, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody Category category, @PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        categoryService.updateItem(category, id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "id") Long id,  @RequestHeader(value = "Authorization") String token){
        categoryService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
