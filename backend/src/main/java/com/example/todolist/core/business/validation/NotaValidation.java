package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.business.excepition.NotaException;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.core.persistence.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotaValidation {
    @Autowired
    private NotaRepository notaRepository;

    public void validNotaContent(String descricao){
        if(descricao == null){
            new NotaException("A nota não pode ser vazia!");
        }
        if(descricao.length() < 3){
            new NotaException("A nota deve conter mais do que 3 caracteres!");
        }
    }
    public void validaIdExist(Long id){
        if(!notaRepository.existsById(id)){
            throw new CategoriaException("Id da nota não existe!");
        }
    }



}
