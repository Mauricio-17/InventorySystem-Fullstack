package com.mauricio.inventory.transaction;

import com.mauricio.inventory.exceptions.BadRequestException;
import com.mauricio.inventory.exceptions.ResourceNotFoundException;
import com.mauricio.inventory.location.Location;
import com.mauricio.inventory.location.LocationRepository;
import com.mauricio.inventory.shelf.Shelf;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private TransactionRepository transactionRepository;

    private LocationRepository locationRepository;

    public void dataValidation(Transaction transaction){
        Optional<Location> source = locationRepository.findById(transaction.getSource().getId());
        Optional<Location> destination = locationRepository.findById(transaction.getDestination().getId());
        if(source.isEmpty() || destination.isEmpty()){
            throw new BadRequestException("Dato de la ubicación fuente o destino inválido");
        }
    }

    public List<Transaction> getAllItems(){
        return transactionRepository.findAll();
    }

    public void addItem(Transaction transaction){
        dataValidation(transaction);
        transactionRepository.save(transaction);
    }

    public void removeItem(Long id){
        Optional<Transaction> foundTransaction = transactionRepository.findById(id);
        if (foundTransaction.isEmpty()){
            throw new ResourceNotFoundException(String.format("Transacción con el ID %s no encontrado", id));
        }
        transactionRepository.delete(foundTransaction.get());
    }
}
