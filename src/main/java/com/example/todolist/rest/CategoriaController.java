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
    CategoriaService categoriaService = new CategoriaService();



    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id){
        Optional<Categoria> categoriaOptional = categoriaService.findCategoria(id);
        if(categoriaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(categoriaOptional.get());
        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategotia(){
        return ResponseEntity.ok(categoriaService.getAllCategoria());
    }

    @PostMapping
    public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria){
        Categoria categoriaNew = categoriaService.saveCategoria(categoria);
        if(categoriaNew != null){
            return ResponseEntity.ok(categoriaNew);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria){
        Categoria categoriaUpdate = categoriaService.updateCategoria(categoria);
        if(categoriaUpdate != null){
            return ResponseEntity.ok(categoriaUpdate);
        }else{
            return ResponseEntity.notFound().build();
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
            return new ResponseEntity<>("Ocorreu um erro ao excluir a categoria", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
