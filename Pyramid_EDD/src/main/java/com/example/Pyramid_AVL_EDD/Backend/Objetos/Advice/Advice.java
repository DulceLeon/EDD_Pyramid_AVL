/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.Objetos.Advice;

import org.springframework.http.HttpStatus;

/**
 *
 * @author phily
 */

public class Advice {
    private HttpStatus status;
    private String message;    
    private String description;
    
    public Advice(){}
    
    private Advice(HttpStatus status, String message, String description){
        this.status = status;
        this.message = message;
        this.description = description;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }
            
    public Advice getAdvice(Type adviceType, String description){//lo que concatene el error, será la descripción...
        switch(adviceType){
            case OK:
                return new Advice(HttpStatus.OK, adviceType.getMessage(), description);
            case CANT_FIND: 
                return new Advice(HttpStatus.NOT_FOUND, adviceType.getMessage(), description);                
            case IMPOSSIBLE_REMOVE: case DUPLICATE_CARD:
                return new Advice(HttpStatus.NOT_ACCEPTABLE, adviceType.getMessage(), description);                
            case NODE_WITH_CHILDREN:
                return new Advice(HttpStatus.CONFLICT, adviceType.getMessage(), description);                
        }
        
        return new Advice(HttpStatus.BAD_REQUEST, adviceType.getMessage(), description);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
    
    
}
