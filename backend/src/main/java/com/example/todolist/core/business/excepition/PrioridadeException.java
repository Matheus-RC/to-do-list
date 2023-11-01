package com.example.todolist.core.business.excepition;

public class PrioridadeException extends RuntimeException{
    public PrioridadeException(StringBuilder erroMenssage){
        super(String.valueOf(erroMenssage));
    }

    public PrioridadeException(String message){
        super(message);
    }

    public PrioridadeException(String message, Throwable cause){
        super(message, cause);
    }
}
