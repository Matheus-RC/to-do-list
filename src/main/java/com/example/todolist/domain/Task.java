package com.example.todolist.domain;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Task {
    private String descricao;
    private Date data_criacao;
    private Date data_conclusao;
    private Date data_vencimento;

}
