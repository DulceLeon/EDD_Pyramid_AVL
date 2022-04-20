/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.Manejadores;

/**
 *
 * @author phily
 */
public class ManejadorDeErrores {
    private String error = "";
    
    public void addError(String nuevoError){
        this.error += nuevoError+"\n";
    }
    
    public void resetError(){
        this.error = "";
    }
    
    public String getError(){
        return this.error;
    }
}
