package com.example.todolist.core.persistence;

import com.example.todolist.core.business.validation.CategoriaValidation;
import com.example.todolist.core.business.validation.NotaValidation;
import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
