package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidation {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public void validCategoriaName(String name){
        if(name == null || name.isEmpty()) {
            throw new CategoriaException("O nome da categoria não pode ser vazio!");
        }
        if(name.length() < 3) {
            throw new CategoriaException("O nome da categoria precisa ter no minímo 3 caracteres!");
        }
    }

    public void validNameExist(Categoria categoria){
        if(categoriaRepository.findExistNameCategoria(categoria.getNome(), categoria.getId_categoria()).size() > 0){
            throw new CategoriaException("Nome da categoria já existe!");
        }
    }

    public void validaIdExist(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new CategoriaException("Id da categoria não existe!");
        }
    }


}
