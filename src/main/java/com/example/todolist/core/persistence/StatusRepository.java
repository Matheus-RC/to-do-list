package com.example.todolist.core.persistence;

import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("SELECT nome FROM Status c WHERE c.nome = :nome")
    List<Categoria> findStatusByName(String nome);
}
