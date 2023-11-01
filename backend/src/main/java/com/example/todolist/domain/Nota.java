package com.example.todolist.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_nota;
    private String descricao;

    @ManyToOne
    @JoinColumn(name ="id_task")
    private Task task;
}
