package com.example.todolist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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
