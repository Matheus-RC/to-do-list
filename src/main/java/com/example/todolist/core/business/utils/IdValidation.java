package com.example.todolist.core.business.utils;

 public class IdValidation {

    public boolean validaId(Long id, StringBuilder errorMansage){
        if(id == null){
            errorMansage.append("O id está indefinido");
            return false;
        }else{
            return true;
        }
    }

 }
