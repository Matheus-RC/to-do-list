package com.example.todolist.core.sevice;

import com.example.todolist.core.business.validation.NotaValidation;
import com.example.todolist.core.persistence.NotaRepository;
import com.example.todolist.domain.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    NotaRepository notaRepository;

    @Autowired
    NotaValidation notaValidation;

    public void saveNota(Nota nota){
        notaValidation.validNotaContent(nota.getDescricao());
        notaRepository.save(nota);
    }

    public Optional<Nota> findNota(Long id){
        notaValidation.validaIdExist(id);
        return notaRepository.findById(id);
    }

    public List<Nota> getAllNota(){
        return notaRepository.findAll();
    }

    public void updatenota(Nota nota){
        notaValidation.validaIdExist(nota.getId_nota());
        notaValidation.validNotaContent(nota.getDescricao());
        notaRepository.save(nota);
    }

    public void deleteNota(Long id){
        notaValidation.validaIdExist(id);
        notaRepository.deleteById(id);
    }

}
