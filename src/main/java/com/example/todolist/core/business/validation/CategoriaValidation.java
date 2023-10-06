package com.example.todolist.core.business.validation;

import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoriaValidation {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public boolean isValidCategoriaName(String name, StringBuilder errorMenssage){
        if(name == null || name.isEmpty()) {
            errorMenssage.append("O nome da categoria não pode ser vazio!");
            return false;
        }
        if(name.length() < 3) {
            errorMenssage.append("O nome da categoria precisa ter no minimo 3 caracteres!");
            return false;
        }
        if(categoriaRepository.findCategoriaByName(name).size() > 0){
            errorMenssage.append("Categoria já existe!");
            return false;
        }
        return true;
    }

    public boolean ExistCategoria(Categoria categoria, StringBuilder errorMenssage){
        if(categoriaRepository.existsById(categoria.getId_categoria())){
            errorMenssage.append("Categoria não existe");
            return false;
        }else{
            errorMenssage.append("Categoria já existe");
            return true;
        }
    }

    public boolean ExistCategoriaById(Long id, StringBuilder errorMenssage){
        if(categoriaRepository.existsById(id)){
            errorMenssage.append("Categoria não existe");
            return false;
        }else{
            errorMenssage.append("Categoria já existe");
            return true;
        }
    }

}
