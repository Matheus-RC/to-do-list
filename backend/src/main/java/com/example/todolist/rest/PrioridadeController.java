package com.example.todolist.rest;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.business.excepition.PrioridadeException;
import com.example.todolist.core.sevice.CategoriaService;
import com.example.todolist.core.sevice.PrioridadeService;
import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Prioridade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/prioridade")
public class PrioridadeController {
    @Autowired
    PrioridadeService prioridadeService;



    @GetMapping("/{id}")
    public ResponseEntity<?> getPrioridade(@PathVariable Long id){
        try{
            Optional<Prioridade> prioridadeOptional = prioridadeService.findPrioridade(id);
            return ResponseEntity.ok(prioridadeOptional.get());
        }catch (PrioridadeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Prioridade>> getAllPrioridade(){
        return ResponseEntity.ok(prioridadeService.getAllPrioridade());
    }

    @PostMapping
    public ResponseEntity<?> createPrioridade(@RequestBody Prioridade prioridade){
        try{
            prioridadeService.savePrioridade(prioridade);
            return new ResponseEntity<>("Prioridade salva com sucesso", HttpStatus.OK);
        }catch (PrioridadeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePrioridade(@RequestBody Prioridade prioridade){
        try{
            prioridadeService.updatePrioridade(prioridade);
            return new ResponseEntity<>("Prioridade atualizada com sucesso!", HttpStatus.OK);
        }catch (PrioridadeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrioridade(@PathVariable Long id){
        try{
            prioridadeService.deletePrioridade(id);
            return new ResponseEntity<>("Prioridade excluída com sucesso", HttpStatus.OK);
        }catch (CategoriaException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
