package com.example.todolist.core.business.excepition;

public class TaskException extends RuntimeException{
    public TaskException(StringBuilder erroMenssage){
        super(String.valueOf(erroMenssage));
    }

    public TaskException(String message){
        super(message);
    }

    public TaskException(String message, Throwable cause){
        super(message, cause);
    }
}
