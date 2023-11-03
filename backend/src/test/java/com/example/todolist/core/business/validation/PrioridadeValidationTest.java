package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.PrioridadeException;
import com.example.todolist.core.persistence.PrioridadeRepository;
import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Prioridade;
import com.example.todolist.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrioridadeValidationTest {

    @InjectMocks
    PrioridadeValidation prioridadeValidation;

    @Mock
    PrioridadeRepository prioridadeRepository;

    @Mock
    Prioridade prioridadeMock;

    @BeforeEach
    public void setUp(){
        prioridadeMock = new Prioridade(1L,"Teste",null);
    }

    @Test
    public void TestValidPrioridadeNameEmpty(){
        String name = "";
        PrioridadeException exception = assertThrows(PrioridadeException.class, ()-> prioridadeValidation.validPrioridadeName(name));
        assertEquals("O nome da prioridade não pode ser vazio!",exception.getMessage());
    }

    @Test
    public void TestValidPrioridadeNameShort(){
        String name = "ab";
        PrioridadeException exception = assertThrows(PrioridadeException.class, ()-> prioridadeValidation.validPrioridadeName(name));
        assertEquals("O nome da prioridade precisa ter no minímo 3 caracteres!",exception.getMessage());
    }

    @Test
    public void TestValidNameExistWithAnotherId(){
        when(prioridadeRepository.findExistNameWithIdPrioridade(prioridadeMock.getName(), prioridadeMock.getId_prioridade())).
                thenReturn(Collections.singletonList(new Prioridade(2L,"Teste2", new ArrayList<>())));
        PrioridadeException exception = assertThrows(PrioridadeException.class, ()->prioridadeValidation.validNameExistWithAnotherId(prioridadeMock));
        assertEquals("Nome da prioridade já existe!",exception.getMessage());
    }

    @Test
    public void TestValidNameDontExistWithAnotherId(){
        when(prioridadeRepository.findExistNameWithIdPrioridade(prioridadeMock.getName(), prioridadeMock.getId_prioridade())).
                thenReturn(new ArrayList<>());
        prioridadeValidation.validNameExistWithAnotherId(prioridadeMock);
    }

    @Test
    public void TestValidNameExist(){
        when(prioridadeRepository.findPrioridadeByName(prioridadeMock.getName())).
                thenReturn(Collections.singletonList(new Prioridade(2L,"Teste", new ArrayList<>())));
        PrioridadeException exception = assertThrows(PrioridadeException.class, ()->prioridadeValidation.validNameExist(prioridadeMock));
        assertEquals("Nome da prioridade já existe!",exception.getMessage());
    }

    @Test
    public void TestValidNameDontExist(){
        when(prioridadeRepository.findExistNameWithIdPrioridade(prioridadeMock.getName(), prioridadeMock.getId_prioridade())).
                thenReturn(new ArrayList<>());
        prioridadeValidation.validNameExistWithAnotherId(prioridadeMock);
    }

    @Test
    public void TestvalidaIdExist(){
        when(prioridadeRepository.existsById(prioridadeMock.getId_prioridade())).
                thenReturn(false);
        PrioridadeException exception = assertThrows(PrioridadeException.class, ()->prioridadeValidation.validaIdExist(prioridadeMock.getId_prioridade()));
        assertEquals("Id da prioridade não existe!",exception.getMessage());
    }

    @Test
    public void TestvalidaIdDontExist(){
        when(prioridadeRepository.existsById(prioridadeMock.getId_prioridade())).
                thenReturn(true);
        prioridadeValidation.validaIdExist(prioridadeMock.getId_prioridade());
    }
}
