package com.example.todolist.core.sevice;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.business.validation.CategoriaValidation;
import com.example.todolist.core.persistence.CategoriaRepository;
import com.example.todolist.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaValidation categoriaValidation;


    public Categoria saveCategoria (Categoria categoria){
        StringBuilder erroMenssage = new StringBuilder();
       if(!categoriaValidation.isValidCategoriaName(categoria.getNome(), erroMenssage)){
            throw new CategoriaException(erroMenssage);
        }
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> findCategoria (Long id){
        if(categoriaRepository.existsById(id)){
            return categoriaRepository.findById(id);
        }else{
            throw new CategoriaException("Categoria não existe");
        }
    }

    public List<Categoria> getAllCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria updateCategoria (Categoria categoria){
        StringBuilder erroMenssage = new StringBuilder();
        if(!categoriaValidation.validaUpdateCategoria(categoria, erroMenssage)){
            throw new CategoriaException(erroMenssage);
        }else{
            if(!categoriaValidation.isValidCategoriaName(categoria.getNome(), erroMenssage)) {
                throw new CategoriaException(erroMenssage);
            }else{
                return categoriaRepository.save(categoria);
            }
        }

    }

    public void deleteCategoria(Long id){
        if(categoriaRepository.existsById(id)){
           categoriaRepository.deleteById(id);
        }else{
            throw new CategoriaException("Categoria não existe");
        }
    }


}
