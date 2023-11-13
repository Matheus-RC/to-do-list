package com.example.todolist.core.business.validation;


import com.example.todolist.core.business.excepition.TaskException;
import com.example.todolist.core.persistence.TaskRepository;
import com.example.todolist.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskValidationTest {

    @InjectMocks
    TaskValidation taskValidation;

    @Mock
    TaskRepository taskRepository;

    @Mock
    Task taskMock;

    @BeforeEach
    public void setUp(){
        taskMock = new Task(1L,"test",null,null,null,null,null,null, null, null);
    }

    @Test
    public void testValidTaskNameEmpty(){
        String name = "";
        TaskException exception = assertThrows(TaskException.class, () -> taskValidation.validTaskName(name));
        assertEquals("O nome da tarefa não pode ser vazio!", exception.getMessage());
    }

    @Test
    public void testValidTaskNameShort(){
        String name = "ab";
        TaskException exception = assertThrows(TaskException.class, () -> taskValidation.validTaskName(name));
        assertEquals("O nome da tarefa precisa ter no minímo 3 caracteres!", exception.getMessage());
    }

    @Test
    public void testValidTaskNameValid(){
        taskValidation.validTaskName(taskMock.getName());
    }

    @Test
    public void testValidaIdDontExist(){
        when(taskRepository.existsById(taskMock.getId_task())).thenReturn(false);
        TaskException exception = assertThrows(TaskException.class, () -> taskValidation.validaIdExist(taskMock.getId_task()));
        assertEquals("Id da tarefa não existe!", exception.getMessage());

    }

    @Test
    public void testValidaIdExist(){
        when(taskRepository.existsById(taskMock.getId_task())).thenReturn(true);
        taskValidation.validaIdExist(taskMock.getId_task());
    }

}
