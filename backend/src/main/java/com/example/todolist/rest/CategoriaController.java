package com.example.todolist.rest;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.sevice.CategoriaService;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;



    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoria(@PathVariable Long id){
        try{
            Optional<Categoria> categoriaOptional = categoriaService.findCategoria(id);
            return ResponseEntity.ok(categoriaOptional.get());
        }catch (CategoriaException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategotia(){
        return ResponseEntity.ok(categoriaService.getAllCategoria());
    }

    @PostMapping
    public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria){
        try{
            categoriaService.saveCategoria(categoria);
            return new ResponseEntity<>("Categoria salva com sucesso", HttpStatus.OK);
        }catch (CategoriaException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCategoria(@RequestBody Categoria categoria){
        try{
            categoriaService.updateCategoria(categoria);
            return new ResponseEntity<>("Categoria atualizada com sucesso!", HttpStatus.OK);
        }catch (CategoriaException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
       try{
           categoriaService.deleteCategoria(id);
           return new ResponseEntity<>("Categoria excluída com sucesso", HttpStatus.OK);
       }catch (CategoriaException e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
