/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.Objetos;

/**
 *
 * @author phily
 */
public class Carta extends Objeto{//Creo que el ID dejará de existir por la manera en la que se insertarán las cosas
    private int valor;
    private String name;
    private String tipo;
    
    
    
    public Carta(int valor, String name){        
        super(valor + ((name.contains("♣"))?0:((name.contains("♦"))?20:((name.contains("♥"))?40:60))));//ya no, por la forma en la que se realizará la inserción...        
     
        this.valor = valor;
        this.name = name;
        this.tipo = ((name.contains("♣"))?"♣":((name.contains("♦"))?"♦":((name.contains("♥"))?"♥":"♠")));
    }    
    
    private Carta(int ID){
        
    }
    
    @Override
    public int hashCode() {
        int hash = valor + ((name.contains("♣"))?0:((name.contains("♦"))?20:((name.contains("♥"))?40:60)));
        return hash;
    }//bueno, si sobreescribo esto no tendría que sobreescribir el equals, o eso pienso, y más creo que tendrás que hacer un hash propio, no así como este xD

    @Override
    public boolean equals(Object objeto){
        Carta laCarta;
        
        if(objeto instanceof Carta){
            laCarta = (Carta) objeto;
            
            return ((laCarta.valor == this.valor) && (laCarta.tipo.equalsIgnoreCase(this.tipo)));//puse el ignore case, porque si coloco el equals, creo que lo que sucedería es que volvería a invocar al equals sobreescrito y no al equals normal por aśi decirlo...
        }
        
       return false;
    }//creo que sí lo deberás sobreescribir, puesto que en este caso también interviene el tipo, par determinar si es o no igual...
    
    //no debo sobreescribir el compareTo, pues por el cambio que app, se debe hacer la comp con los ID...
    
    @Override
    public String toString(){
        return "Carta[ nombre: "+name+", valor: "+valor+"] ";
    }
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public String getName(){
        return this.name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
