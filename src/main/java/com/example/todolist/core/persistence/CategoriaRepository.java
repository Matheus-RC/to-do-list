package com.example.todolist.core.persistence;

import com.example.todolist.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT nome FROM CATEGORIA c WHERE c.nome = :nome")
    List<Categoria> findCategoriaByName(String nome);
}
