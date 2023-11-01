package com.example.todolist.rest;

import com.example.todolist.core.business.excepition.NotaException;
import com.example.todolist.core.persistence.NotaRepository;
import com.example.todolist.core.sevice.NotaService;
import com.example.todolist.domain.Nota;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/nota")
public class NotaController {

    @Autowired
    NotaService notaService = new NotaService();

    @GetMapping("/{id}")
    public ResponseEntity<?> getNota(@PathVariable Long id){
        try{
            Optional<Nota> nota = notaService.findNota(id);
            return ResponseEntity.ok(nota.get());
        }catch(NotaException e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Nota>> getAllNota(){
        return ResponseEntity.ok(notaService.getAllNota());
    }

    @PostMapping
    public ResponseEntity<?> createNota(@RequestBody Nota nota){
        try{
            notaService.saveNota(nota);
            return ResponseEntity.ok("Nota criada com sucesso!");
        }catch(NotaException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateNota(@RequestBody Nota nota){
        try{
            notaService.saveNota(nota);
            return ResponseEntity.ok("Nota atualizada com sucesso!");
        }catch(NotaException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<?> deleteNota(@PathVariable Long id){
        try{
            notaService.deleteNota(id);
            return ResponseEntity.ok("Nota excluída com sucesso!");
        }catch(NotaException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
