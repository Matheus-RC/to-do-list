package com.example.todolist.core.business.excepition;

public class CategoriaException extends RuntimeException{

    public CategoriaException(StringBuilder erroMenssage){
        super(String.valueOf(erroMenssage));
    }

    public CategoriaException(String message){
        super(message);
    }

    public CategoriaException(String message, Throwable cause){
        super(message, cause);
    }
}
