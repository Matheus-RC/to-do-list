package com.example.todolist.core.business.validation;

import com.example.todolist.core.persistence.StatusRepository;
import com.example.todolist.core.persistence.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskValidation {
    @Autowired
    private TaskRepository taskRepository;

}
