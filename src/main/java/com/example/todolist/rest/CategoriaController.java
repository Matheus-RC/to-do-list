package com.example.todolist.rest;

import com.example.todolist.core.sevice.CategoriaService;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
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

}
