package com.mauricio.inventory.transaction;

import com.mauricio.inventory.typeowner.TypeOwner;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions(@RequestHeader(value = "Authorization") String token){
        return transactionService.getAllItems(token);
    }

    @PostMapping
    public ResponseEntity<Void> addTransaction(@Valid @RequestBody Transaction transaction, @RequestHeader(value = "Authorization") String token){
        transactionService.addItem(transaction, token);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization") String token){
        transactionService.removeItem(id, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
