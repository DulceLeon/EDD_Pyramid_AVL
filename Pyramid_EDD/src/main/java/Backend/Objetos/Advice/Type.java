/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Objetos.Advice;


/**
 *
 * @author phily
 */
public enum Type {
    OK("Solicitud procesada exitosamente"), 
    CANT_FIND("La carta no se encuentra en el árbol AVL"),
    IMPOSSIBLE_REMOVE("Los valores de las cartas no suman 13"),
    NODE_WITH_CHILDREN("Imposible de eliminar, pues la carta tiene hijos"),
    DUPLICATE_CARD("Imposible de insertar cartas repetidas"),
    OTHER("Ups, ha surgido un error");
    
    private String message;
    
    private Type(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
