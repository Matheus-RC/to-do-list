package com.example.todolist.domain;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Status {
    private String status;
}
