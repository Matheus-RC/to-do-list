package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.business.excepition.PrioridadeException;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.core.persistence.PrioridadeRepository;
import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Prioridade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrioridadeValidation {
    @Autowired
    private PrioridadeRepository prioridadeRepository;


    public void validPrioridadeName(String name){
        if(name == null || name.isEmpty()) {
            throw new PrioridadeException("O nome da prioridade não pode ser vazio!");
        }
        if(name.length() < 3) {
            throw new PrioridadeException("O nome da prioridade precisa ter no minímo 3 caracteres!");
        }
    }

    public void validNameExist(Prioridade prioridade){
        if(prioridadeRepository.findExistNamePrioridade(prioridade.getName(), prioridade.getId_prioridade()).size() > 0){
            throw new CategoriaException("Nome da prioridade já existe!");
        }
    }

    public void validaIdExist(Long id){
        if(!prioridadeRepository.existsById(id)){
            throw new CategoriaException("Id da prioridade não existe!");
        }
    }


}
