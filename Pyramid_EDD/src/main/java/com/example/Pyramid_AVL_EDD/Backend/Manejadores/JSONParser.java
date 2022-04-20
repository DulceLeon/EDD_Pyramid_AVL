/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.Manejadores;

import com.example.Pyramid_AVL_EDD.Backend.EDD.ListaEnlazada;
import com.example.Pyramid_AVL_EDD.Backend.Objetos.Carta;
import org.json.JSONObject;

/**
 *
 * @author phily
 */
public class JSONParser {
    //será útil para getLevel y tb cuando soliciten 
    //el AVL en un cierto orden
    public String toJSON(ListaEnlazada<Carta> cartas){       
        JSONObject jsonObject = new JSONObject();
        
        for (int actual = 0; actual < cartas.size(); actual++) {
            jsonObject.put(String.valueOf(actual),
             cartas.getElement(actual).getName());//no hay necesidad de manejar que un nodo posiblemente sea null, puesto que yo xD, mando los índices, no el usuario...
        }
        
        return jsonObject.toString();
    }
    
}
