package com.example.todolist.core.persistence;

import com.example.todolist.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c WHERE c.nome = :nome")
    List<Categoria> findCategoriaByName(@Param("nome") String nome);

    @Query("SELECT c FROM Categoria c WHERE c.nome = :nome and c.id_categoria <> :id")
    List<Categoria> findExistNameCategoria(@Param("nome") String nome, @Param("id") Long id);
}
