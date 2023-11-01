package com.example.todolist.core.sevice;

import com.example.todolist.core.business.validation.StatusValidation;
import com.example.todolist.core.persistence.StatusRepository;
import com.example.todolist.domain.Prioridade;
import com.example.todolist.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private StatusValidation statusValidation;


    public void saveStatus (Status status){
        statusValidation.validStatusName(status.getName());
        statusValidation.validNameExist(status);
        statusRepository.save(status);
    }

    public Optional<Status> findStatus (Long id){
        statusValidation.validaIdExist(id);
        return statusRepository.findById(id);
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public void updateStatus (Status status){
        statusValidation.validaIdExist(status.getId_status());
        statusValidation.validStatusName(status.getName());
        statusValidation.validNameExistWithAnotherId(status);
        statusRepository.save(status);
    }

    public void deleteStatus(Long id){
        statusValidation.validaIdExist(id);
        statusRepository.deleteById(id);
    }

}
