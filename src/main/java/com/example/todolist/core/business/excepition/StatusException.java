package com.example.todolist.core.business.excepition;

public class StatusException extends RuntimeException{
    public StatusException(StringBuilder erroMenssage){
        super();
    }

    public StatusException(String message){
        super(message);
    }

    public StatusException(String message, Throwable cause){
        super(message, cause);
    }
}
