package com.example.todolist.core.business.validation;

import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.core.persistence.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NotaValidation {
    @Autowired
    private NotaRepository notaRepository;

    public boolean ExistNotaById(Long id, StringBuilder errorMenssage){
        if(notaRepository.existsById(id)){
            errorMenssage.append("Nota não existe");
            return false;
        }else{
            errorMenssage.append("NOta já existe");
            return true;
        }
    }

}
