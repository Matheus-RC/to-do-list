package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.business.excepition.TaskException;
import com.example.todolist.core.persistence.StatusRepository;
import com.example.todolist.core.persistence.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskValidation {
    @Autowired
    private TaskRepository taskRepository;

    public void validTaskName(String name){
        if(name == null || name.isEmpty()) {
            throw new TaskException("O nome da tarefa não pode ser vazio!");
        }
        if(name.length() < 3) {
            throw new TaskException("O nome da tarefa precisa ter no minímo 3 caracteres!");
        }
    }
    public void validaIdExist(Long id){
        if(!taskRepository.existsById(id)){
            throw new TaskException("Id da tarefa não existe!");
        }
    }

}
