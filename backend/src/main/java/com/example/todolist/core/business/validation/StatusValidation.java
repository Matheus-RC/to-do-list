package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.PrioridadeException;
import com.example.todolist.core.business.excepition.StatusException;
import com.example.todolist.core.persistence.PrioridadeRepository;
import com.example.todolist.core.persistence.StatusRepository;
import com.example.todolist.domain.Prioridade;
import com.example.todolist.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusValidation {
    @Autowired
    private StatusRepository statusRepository;
    public void isValidStatusName(String nome){
        if(nome == null || nome.isEmpty()) {
            throw  new StatusException("O nome do status não pode ser vazio!");
        }
        if(nome.length() < 3) {
            throw  new StatusException("O nome do status precisa ter no minimo 3 caracteres!");
        }
        if(statusRepository.findStatusByName(nome).size() > 0){
            throw  new StatusException("O nome do status já existe!");
        }
    }

    public void validStatusName(String name){
        if(name == null || name.isEmpty()) {
            throw new StatusException("O nome do status não pode ser vazio!");
        }
        if(name.length() < 3) {
            throw new StatusException("O nome do status precisa ter no minímo 3 caracteres!");
        }
    }

    public void validNameExistWithAnotherId(Status status){
        if(statusRepository.findExistNameWithIdStatus(status.getName(), status.getId_status()).size() > 0){
            throw new StatusException("Nome do status já existe!");
        }
    }
    public void validNameExist(Status status){
        if(statusRepository.findStatusByName(status.getName()).size() > 0){
            throw new StatusException("Nome do status já existe!");
        }
    }

    public void validaIdExist(Long id){
        if(!statusRepository.existsById(id)){
            throw new StatusException("Id do status não existe!");
        }
    }

}
