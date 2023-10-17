package com.example.todolist.core.sevice;

import com.example.todolist.core.business.validation.CategoriaValidation;
import com.example.todolist.core.business.validation.PrioridadeValidation;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.core.persistence.PrioridadeRepository;
import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Prioridade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrioridadeService {
    @Autowired
    private PrioridadeRepository prioridadeRepository;
    @Autowired
    private PrioridadeValidation prioridadeValidation;


    public void savePrioridade (Prioridade prioridade){
        prioridadeValidation.validPrioridadeName(prioridade.getName());
        prioridadeValidation.validNameExist(prioridade);
        prioridadeRepository.save(prioridade);
    }

    public Optional<Prioridade> findPrioridade (Long id){
        prioridadeValidation.validaIdExist(id);
        return prioridadeRepository.findById(id);
    }

    public List<Prioridade> getAllPrioridade() {
        return prioridadeRepository.findAll();
    }

    public void updatePrioridade (Prioridade prioridade){
        prioridadeValidation.validaIdExist(prioridade.getId_prioridade());
        prioridadeValidation.validPrioridadeName(prioridade.getName());
        prioridadeValidation.validNameExist(prioridade);
        prioridadeRepository.save(prioridade);
    }

    public void deletePrioridade(Long id){
        prioridadeValidation.validaIdExist(id);
        prioridadeRepository.deleteById(id);
    }
}
