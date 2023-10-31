package com.example.todolist.core.sevice;

import com.example.todolist.core.business.validation.TaskValidation;
import com.example.todolist.core.persistence.TaskRepository;
import com.example.todolist.domain.Status;
import com.example.todolist.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskValidation taskValidation;


    public void saveTask (Task task){
        taskValidation.validTaskName(task.getName());
        taskRepository.save(task);
    }

    public Optional<Task> findTask (Long id){
        taskValidation.validaIdExist(id);
        return taskRepository.findById(id);
    }

    public List<Task> findTaskByNameContaining(String keyword){
        return taskRepository.findTasksByNameContaining(keyword);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void updateTask (Task task){
        taskValidation.validaIdExist(task.getId_task());
        taskValidation.validTaskName(task.getName());
        taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskValidation.validaIdExist(id);
        taskRepository.deleteById(id);
    }
}
