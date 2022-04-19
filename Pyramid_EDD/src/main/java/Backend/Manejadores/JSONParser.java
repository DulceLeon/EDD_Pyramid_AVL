/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Manejadores;

import Backend.EDD.ListaEnlazada;
import Backend.Objetos.Carta;

/**
 *
 * @author phily
 */
public class JSONParser {
    
    //será útil para getLevel y tb cuando soliciten 
    //el AVL en un cierto orden
    public String toJSON(ListaEnlazada<Carta> cartas){
        String JSON = "{\n";
        
        for (int cartaActual = 0; cartaActual < cartas.size(); cartaActual++) {
            JSON += "\""+cartaActual+"\": "+"\""+cartas.getElement(cartaActual)+"\"";
        }        
        
        return (JSON+"\n}");//se supone no tendría que haber cabida de errores 
    }
    
}
