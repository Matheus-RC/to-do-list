package com.example.todolist.core.business;

public class CategoriaNotFoundExcepition extends RuntimeException{

    public CategoriaNotFoundExcepition(){
        super();
    }

    public CategoriaNotFoundExcepition(String message){
        super(message);
    }

    public CategoriaNotFoundExcepition(String message, Throwable cause){
        super(message, cause);
    }
}
