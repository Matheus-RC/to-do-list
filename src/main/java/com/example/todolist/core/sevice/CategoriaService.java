package com.example.todolist.core.sevice;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.business.validation.CategoriaValidation;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaValidation categoriaValidation;


    public Categoria saveCategoria (Categoria categoria){
        categoriaValidation.validCategoriaName(categoria.getNome());
        categoriaValidation.validNameExist(categoria);
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> findCategoria (Long id){
        categoriaValidation.validaIdExist(id);
        return categoriaRepository.findById(id);
    }

    public List<Categoria> getAllCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria updateCategoria (Categoria categoria){
        categoriaValidation.validaIdExist(categoria.getId_categoria());
        categoriaValidation.validCategoriaName(categoria.getNome());
        categoriaValidation.validNameExist(categoria);
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id){
        categoriaValidation.validaIdExist(id);
        categoriaRepository.deleteById(id);
    }

}
