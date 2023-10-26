package com.example.todolist.core.persistence;

import com.example.todolist.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.name LIKE %:keyword%")
    List<Task> findTasksByNameContaining(@Param("keyword") String keyword);
}
