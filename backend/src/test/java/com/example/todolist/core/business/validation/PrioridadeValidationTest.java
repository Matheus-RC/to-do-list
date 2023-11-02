package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.PrioridadeException;
import com.example.todolist.core.persistence.PrioridadeRepository;
import com.example.todolist.domain.Prioridade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void validPrioridadeNameEmpty(){
        String name = "";
        PrioridadeException exception = assertThrows(PrioridadeException.class, ()-> prioridadeValidation.validPrioridadeName(name));
        assertEquals("O nome da prioridade não pode ser vazio!",exception.getMessage());
    }

}
