package com.example.todolist.core.business.excepition;

public class NotaException extends RuntimeException{

    public NotaException(StringBuilder erroMenssage){
        super(String.valueOf(erroMenssage));
    }

    public NotaException(String message){
        super(message);
    }

    public NotaException(String message, Throwable cause){
        super(message, cause);
    }
}
