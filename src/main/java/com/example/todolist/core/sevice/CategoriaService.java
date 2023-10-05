package com.example.todolist.core.sevice;

import com.example.todolist.core.business.CategoriaNotFoundExcepition;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria saveCategoria (Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> findCategoria (Long id){
        return categoriaRepository.findById(id);
    }

    public List<Categoria> getAllCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria updateCategoria (Categoria categoria){
        if(categoriaRepository.existsById(categoria.getId_categoria())){
            return categoriaRepository.save(categoria);
        }else{
            throw new CategoriaNotFoundExcepition("Categoria não existe");
        }
    }


}
