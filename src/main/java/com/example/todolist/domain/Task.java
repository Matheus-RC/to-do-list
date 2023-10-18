package com.example.todolist.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task;
    private String descricao;
    private Date data_criacao;
    private Date data_conclusao;
    private Date data_vencimento;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> nota;
    @OneToOne
    private Categoria categoria;
    @OneToOne
    private Prioridade prioridade;
    @OneToOne
    private Status status;

}
