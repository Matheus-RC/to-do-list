package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.utils.IdValidation;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidation {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private IdValidation idValidation = new IdValidation();

    public boolean isValidCategoriaName(String name, StringBuilder errorMenssage){
        if(name == null || name.isEmpty()) {
            errorMenssage.append("O nome da categoria não pode ser vazio!");
            return false;
        }
        if(name.length() < 3) {
            errorMenssage.append("O nome da categoria precisa ter no minimo 3 caracteres!");
            return false;
        }
        return true;
    }

    public boolean validaNomeExiste(Categoria categoria, StringBuilder errorMenssage){
        String name = categoria.getNome();
        Long id = categoria.getId_categoria();
        if(categoria.getId_categoria() == null){
           if(categoriaRepository.findCategoriaByName(name).size() > 0){
               errorMenssage.append("Nome da categoria já existe!");
               return false;
           }
           return true;
        }else{
            if(categoriaRepository.findExistNameCategoria(name, id).size() > 0){
                errorMenssage.append("Nome da categoria já existe!");
                return false;
            }
            return true;
        }
    }

    public boolean validaIdExist(Categoria caegoria,StringBuilder errorMenssage){

    }

    public boolean validaCreateCategoria(Categoria categoria,StringBuilder errorMenssage){
        String name = categoria.getNome();
        if(!isValidCategoriaName(name,errorMenssage)){
            return false;
        }else{
            if(validaNomeExiste(categoria, errorMenssage)){
                return false;
            }
        }
        return true;
    }

    public boolean validaUpdateCategoria(Categoria categoria, StringBuilder errorMenssage){
        if(idValidation.validaId(categoria.getId_categoria(), errorMenssage)){
            return false;
        }else {
            if(!categoriaRepository.existsById(categoria.getId_categoria())){
                errorMenssage.append("Categoria não existe!");
                return false;
            }else {
                if(!isValidCategoriaName(categoria.getNome(), errorMenssage)){
                    return false;
                }else {
                    if (validaNomeExiste(categoria, errorMenssage)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }


}
