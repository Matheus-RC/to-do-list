package com.example.todolist.core.business.validation;

import com.example.todolist.core.business.excepition.NotaException;
import com.example.todolist.core.persistence.NotaRepository;
import com.example.todolist.domain.Nota;
import com.example.todolist.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotaValidationTest {

    @InjectMocks
    NotaValidation notaValidation;

    @Mock
    NotaRepository notaRepository;

    @Mock
    Nota notaMock;

    @BeforeEach
    public void setUp(){
        notaMock = new Nota(1L,"Teste",null);
    }

    @Test
    public void testValidNotaContentEmpty(){
        String descricao = "";
        NotaException exception = assertThrows(NotaException.class, ()-> notaValidation.validNotaContent(descricao));
        assertEquals("A nota não pode ser vazia!", exception.getMessage());
    }

    @Test
    public void testValidNotaContentShort(){
        String descricao = "ab";
        NotaException exception = assertThrows(NotaException.class, ()-> notaValidation.validNotaContent(descricao));
        assertEquals("A nota deve conter mais do que 3 caracteres!", exception.getMessage());
    }

    @Test
    public void testValidNotaContentValid(){
        notaValidation.validNotaContent(notaMock.getDescricao());
    }

    @Test
    public void testValidIdExist(){
        when(notaRepository.existsById(notaMock.getId_nota())).thenReturn(false);
        NotaException exception = assertThrows(NotaException.class,()-> notaValidation.validaIdExist(notaMock.getId_nota()));
        assertEquals("Id da nota não existe!", exception.getMessage());
    }

    @Test
    public void testValidIdDontExist(){
        when(notaRepository.existsById(notaMock.getId_nota())).thenReturn(true);
        notaValidation.validaIdExist(notaMock.getId_nota());
    }

}
