package com.example.todolist.core.persistence;

import com.example.todolist.domain.Categoria;
import com.example.todolist.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("SELECT name FROM Status c WHERE c.name = :name")
    List<Categoria> findStatusByName(String name);

    @Query("SELECT c FROM Status c WHERE c.name = :name and c.id_status <> :id")
    List<Categoria> findExistNameWithIdStatus(@Param("name") String name, @Param("id") Long id);
}
