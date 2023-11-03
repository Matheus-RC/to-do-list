package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.PrioridadeException;
import com.example.todolist.core.business.excepition.StatusException;
import com.example.todolist.core.persistence.PrioridadeRepository;
import com.example.todolist.core.persistence.StatusRepository;
import com.example.todolist.domain.Prioridade;
import com.example.todolist.domain.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatusValidationTest {
    @InjectMocks
    StatusValidation statusValidation;

    @Mock
    StatusRepository statusRepository;

    @Mock
    Status statusMock;

    @BeforeEach
    public void setUp(){
        statusMock = new Status(1L,"Teste",null);
    }

    @Test
    public void TestValidStatusNameEmpty(){
        String name = "";
        StatusException exception = assertThrows(StatusException.class, ()-> statusValidation.validStatusName(name));
        assertEquals("O nome do status não pode ser vazio!",exception.getMessage());
    }

    @Test
    public void TestValidStatusNameShort(){
        String name = "ab";
        StatusException exception = assertThrows(StatusException.class, ()-> statusValidation.validStatusName(name));
        assertEquals("O nome do status precisa ter no minímo 3 caracteres!",exception.getMessage());
    }

    @Test
    public void TestValidNameExistWithAnotherId(){
        when(statusRepository.findExistNameWithIdStatus(statusMock.getName(), statusMock.getId_status())).
                thenReturn(Collections.singletonList(new Status(2L,"Teste2", new ArrayList<>())));
        StatusException exception = assertThrows(StatusException.class, ()-> statusValidation.validNameExistWithAnotherId(statusMock));
        assertEquals("Nome do status já existe!",exception.getMessage());
    }

    @Test
    public void TestValidNameDontExistWithAnotherId(){
        when(statusRepository.findExistNameWithIdStatus(statusMock.getName(), statusMock.getId_status())).
                thenReturn(new ArrayList<>());
        statusValidation.validNameExistWithAnotherId(statusMock);
    }

    @Test
    public void TestValidNameExist(){
        when(statusRepository.findStatusByName(statusMock.getName())).
                thenReturn(Collections.singletonList(new Status(2L,"Teste", new ArrayList<>())));
        StatusException exception = assertThrows(StatusException.class, ()-> statusValidation.validNameExist(statusMock));
        assertEquals("Nome do status já existe!",exception.getMessage());
    }

    @Test
    public void TestValidNameDontExist(){
        when(statusRepository.findStatusByName(statusMock.getName())).
                thenReturn( new ArrayList<>());
         statusValidation.validNameExist(statusMock);
    }

    @Test
    public void TestValidaIdExist(){
        when(statusRepository.existsById(statusMock.getId_status())).
                thenReturn(false);
        StatusException exception = assertThrows(StatusException.class, ()->statusValidation.validaIdExist(statusMock.getId_status()));
        assertEquals("Id do status não existe!",exception.getMessage());
    }

    @Test
    public void TestValidaIdDontExist(){
        when(statusRepository.existsById(statusMock.getId_status())).
                thenReturn(true);
        statusValidation.validaIdExist(statusMock.getId_status());
    }

}
