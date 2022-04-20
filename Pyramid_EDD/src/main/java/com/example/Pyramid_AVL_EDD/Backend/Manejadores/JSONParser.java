/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.Manejadores;

import com.example.Pyramid_AVL_EDD.Backend.EDD.ListaEnlazada;
import com.example.Pyramid_AVL_EDD.Backend.Objetos.Carta;

/**
 *
 * @author phily
 */
public class JSONParser {
    
    //será útil para getLevel y tb cuando soliciten 
    //el AVL en un cierto orden
    public String toJSON(ListaEnlazada<Carta> cartas){
        
        String JSON = "{\\u000A";
        int cartaActual;
        
        for (cartaActual = 0; cartaActual < (cartas.size()-1); cartaActual++) {
            JSON += '\u0022'+cartaActual+'\u0022'+":" +'\u0022'+
                    cartas.getElement(cartaActual).getName()+'\u0022'+",";
        }        
        
        JSON += '\u0022'+cartaActual+'\u0022'+": "+'\u0022'+cartas.getElement(cartaActual).getName()+'\u0022';
        return (JSON+"\\u000A}");//se supone no tendría que haber cabida de errores 
    }
    
}
