/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Objetos;

import java.util.Objects;

/**
 *
 * @author phily
 */
public class Carta{//Creo que el ID dejará de existir por la manera en la que se insertarán las cosas
    private int valor;
    private String tipo;
    
    public Carta(int valor, String tipo){
        //int valorID = valor + ((tipo.equals("♦"))?10:((tipo.equals("♥"))?20:((tipo.equals("♠"))?30:40)));//ya no, por la forma en la que se realizará la inserción...
        
        this.valor = valor;
        this.tipo = tipo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.valor;
        hash = 89 * hash + Objects.hashCode(this.tipo);
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
    
     public int compareTo(Carta anotherCard){
        if(this.valor == anotherCard.getValor()){
            return 0;
        }else if(this.valor > anotherCard.getValor()){
            return 1;
        }
        
        return -1;
    }   
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
