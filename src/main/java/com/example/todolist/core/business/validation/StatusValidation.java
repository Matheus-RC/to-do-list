package com.example.todolist.core.business.validation;

import com.example.todolist.core.persistence.PrioridadeRepository;
import com.example.todolist.core.persistence.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusValidation {
    @Autowired
    private StatusRepository statusRepository;
    public boolean isValidStatusName(String nome, StringBuilder errorMenssage){
        if(nome == null || nome.isEmpty()) {
            errorMenssage.append("O nome do status não pode ser vazio!");
            return false;
        }
        if(nome.length() < 3) {
            errorMenssage.append("O nome do status precisa ter no minimo 3 caracteres!");
            return false;
        }
        if(statusRepository.findStatusByName(nome).size() > 0){
            errorMenssage.append("O nome do status já existe!");
            return false;
        }
        return true;
    }

}
