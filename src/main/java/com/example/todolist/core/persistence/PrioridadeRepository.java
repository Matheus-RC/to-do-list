package com.example.todolist.core.persistence;

import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Prioridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrioridadeRepository extends JpaRepository<Prioridade, Long> {
    @Query("SELECT classificacao FROM Prioridade c WHERE c.classificacao = :classificacao")
    List<Categoria> findPrioridadeByName(String classificacao);
}
