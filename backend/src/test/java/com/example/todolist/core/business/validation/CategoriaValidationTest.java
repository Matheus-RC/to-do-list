package com.example.todolist.core.business.validation;


import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.core.sevice.CategoriaService;
import com.example.todolist.domain.Categoria;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaValidationTest {

    @InjectMocks
    CategoriaValidation categoriaValidation;
    @Mock
    CategoriaRepository categoriaRepository;
    @Mock
    Categoria categoriaMock;

    @BeforeEach
    public void setUp(){
        categoriaMock = new Categoria(1L,"Teste", new ArrayList<>());
    }
    @Test
    public void testValidCategoriaNameisValid(){
        String name = "Teste";
        categoriaValidation.validCategoriaName(categoriaMock.getNome());

    }

    @Test
    public void testValidationNameEmpty(){
        String name = "";
        CategoriaException exception = assertThrows(CategoriaException.class, () -> categoriaValidation.validCategoriaName(name));
        assertEquals("O nome da categoria não pode ser vazio!", exception.getMessage());
    }

    @Test
    public void testValidNameShort(){
        String name = "ab";
        CategoriaException exception = assertThrows(CategoriaException.class, () -> categoriaValidation.validCategoriaName(name));
        assertEquals("O nome da categoria precisa ter no minímo 3 caracteres!", exception.getMessage());

    }
    @Test
    public void testValidNameValid(){
        categoriaValidation.validCategoriaName(categoriaMock.getNome());
    }


    @Test
    public void testValidNameExistWithAnotherId(){
        when(categoriaRepository.findExistNameWithIdCategoria(categoriaMock.getNome(), categoriaMock.getId_categoria()))
                .thenReturn(Collections.singletonList(new Categoria(2L, "Teste2", new ArrayList<>())));

        CategoriaException exception = assertThrows(CategoriaException.class, () -> categoriaValidation.validNameExistWithAnotherId(categoriaMock));
        assertEquals("Nome da categoria já existe!", exception.getMessage());

    }

    @Test
    public void testValidNameDontExistWithAnotherId(){
        when(categoriaRepository.findExistNameWithIdCategoria(categoriaMock.getNome(), categoriaMock.getId_categoria()))
                .thenReturn(new ArrayList<>());

        categoriaValidation.validNameExistWithAnotherId(categoriaMock);
    }

    @Test
    public void testValidNameExist(){
        when(categoriaRepository.findCategoriaByName(categoriaMock.getNome())).thenReturn(Collections.singletonList(categoriaMock));

        CategoriaException exception = assertThrows(CategoriaException.class, () -> categoriaValidation.validNameExist(categoriaMock));
        assertEquals("Nome da categoria já existe!", exception.getMessage());
    }

    @Test
    public void testValidNameDontExist(){
        when(categoriaRepository.findCategoriaByName(categoriaMock.getNome())).thenReturn(new ArrayList<>());

       categoriaValidation.validNameExist(categoriaMock);
    }

    @Test
    public void testValidIdExist(){
        when(categoriaRepository.existsById(categoriaMock.getId_categoria())).thenReturn(false);

        CategoriaException exception = assertThrows(CategoriaException.class, () -> categoriaValidation.validaIdExist(categoriaMock.getId_categoria()));
        assertEquals("Id da categoria não existe!", exception.getMessage());
    }

    @Test
    public void testValidIdDontExist(){
        when(categoriaRepository.existsById(categoriaMock.getId_categoria())).thenReturn(true);

        categoriaValidation.validaIdExist(categoriaMock.getId_categoria());
    }

}
