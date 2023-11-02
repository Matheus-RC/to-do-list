package com.example.todolist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task;
    private String name;
    private String descricao;
    private Date data_criacao;
    private Date data_conclusao;
    private Date data_vencimento;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> nota;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Prioridade prioridade;
    @ManyToOne
    private Status status;

    @PrePersist
    public void prePersist() {
        this.data_criacao = new Date();
    }

}
