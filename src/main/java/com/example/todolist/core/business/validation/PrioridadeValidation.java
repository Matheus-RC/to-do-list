package com.example.todolist.core.business.validation;

import com.example.todolist.core.persistence.PrioridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PrioridadeValidation {
    @Autowired
    private PrioridadeRepository prioridadeRepository;
    public boolean isValidPrioridadeName(String classificacao, StringBuilder errorMenssage){
        if(classificacao == null || classificacao.isEmpty()) {
            errorMenssage.append("A classificação da prioridade não pode ser vazia!");
            return false;
        }
        if(classificacao.length() < 3) {
            errorMenssage.append("A classificação da prioridade precisa ter no minimo 3 caracteres!");
            return false;
        }
        if(prioridadeRepository.findPrioridadeByName(classificacao).size() > 0){
            errorMenssage.append("Essa classificação da prioridade já existe!");
            return false;
        }
        return true;
    }


}
