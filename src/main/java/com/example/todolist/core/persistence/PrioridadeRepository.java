package com.example.todolist.core.persistence;

import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Prioridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrioridadeRepository extends JpaRepository<Prioridade, Long> {
    @Query("SELECT c FROM Prioridade c WHERE c.name = :name")
    List<Prioridade> findPrioridadeByName(@Param("name") String name);

    @Query("SELECT c FROM Prioridade c WHERE c.name = :name and c.id_prioridade <> :id")
    List<Categoria> findExistNamePrioridade(@Param("name") String name, @Param("id") Long id);
}
