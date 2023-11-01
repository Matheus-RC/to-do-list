package com.example.todolist.core.business.excepition;

public class StatusException extends RuntimeException{
    public StatusException(StringBuilder erroMenssage){
        super(String.valueOf(erroMenssage));
    }

    public StatusException(String message){
        super(message);
    }

    public StatusException(String message, Throwable cause){
        super(message, cause);
    }
}
