/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.Tools;

import com.example.Pyramid_AVL_EDD.Backend.Objetos.Carta;
import com.example.Pyramid_AVL_EDD.Backend.Objetos.Objeto;

/**
 *
 * @author phily
 */
public class Tool {
        
    public boolean esMayor(Object objetoBase, Object objetoAComparar){
    
        if(objetoBase instanceof Double && objetoAComparar instanceof Double){//puse double para inclu ahí de una vez decimales y enteros...
            double numeroAComparar = (Double) objetoAComparar;
            double numeroBase = (Double) objetoBase;//creo que es con mayúscula, ya no me acuerdo :,v
            
            return (numeroAComparar > numeroBase);
        }
        if(objetoBase instanceof Integer && objetoAComparar instanceof Integer){//puse double para inclu ahí de una vez decimales y enteros...
            double numeroAComparar = (Integer) objetoAComparar;
            double numeroBase = (Integer) objetoBase;//creo que es con mayúscula, ya no me acuerdo :,v
            
            return (numeroAComparar > numeroBase);
        }
        if(objetoBase instanceof  Character && objetoAComparar instanceof Character){
            char caracterAComparar = (char) objetoAComparar;
            char caracterBase = (char) objetoBase;
            
            return (caracterAComparar > caracterBase);//recuerda que los char pueden ser tratados como números...
        }if(objetoBase instanceof String && objetoAComparar instanceof String){
            String cadenaAComparar = (String) objetoAComparar;
            String cadenaBase = (String) objetoBase;

            return (cadenaAComparar.compareTo(cadenaBase) == 1);
        }if(objetoBase instanceof Objeto && objetoAComparar instanceof Objeto){            
            Carta cartaAComparar = (Carta) objetoAComparar;
            Carta cartaBase = (Carta) objetoBase;
            
            return (cartaAComparar.compareTo(cartaBase) == 1);            
        }//esto es específico del proyecto... puesto que no se puede castear un tipo que hereda al tipo base, en este caso Carta a Objeto
        
        return false;        
    }
    
    public boolean esIgual(Object objetoBase, Object objetoAComparar){    
        if(objetoBase instanceof Integer && objetoAComparar instanceof Integer){//puse double para inclu ahí de una vez decimales y enteros...
            double numeroAComparar = (Integer) objetoAComparar;
            double numeroBase = (Integer) objetoBase;//creo que es con mayúscula, ya no me acuerdo :,v
            
            return (numeroAComparar == numeroBase);
        }
        if(objetoBase instanceof Double && objetoAComparar instanceof Double){//puse double para inclu ahí de una vez decimales y enteros...
            double numeroAComparar = (Double) objetoAComparar;
            double numeroBase = (Double) objetoBase;//creo que es con mayúscula, ya no me acuerdo :,v
            
            return (numeroAComparar == numeroBase);
        }if(objetoBase instanceof  Character && objetoAComparar instanceof Character){
            char caracterAComparar = (char) objetoAComparar;
            char caracterBase = (char) objetoBase;
            
            return (caracterAComparar == caracterBase);//recuerda que los char pueden ser tratados como números...
        }if(objetoBase instanceof String && objetoAComparar instanceof String){
            String cadenaAComparar = (String) objetoAComparar;
            String cadenaBase = (String) objetoBase;

            return (cadenaAComparar.compareTo(cadenaBase) ==0);
        }if(objetoBase instanceof Objeto && objetoAComparar instanceof Objeto){
            Carta cartaAComparar = (Carta) objetoAComparar;//no hago un casteo a Objeto, puesto que hasta donde recuerdo no se puede, por los atrib del objeto específico...
            Carta cartaBase = (Carta) objetoBase;
            
            return (cartaAComparar.equals(cartaBase));
        }
        
        return false;//lamas el método esIgual de cualquiera de los objetos propios que existan, pueso que cada uno tendría su propia vrs, de no tener nada en común con otro... xD        
    }
    
    /**
     * OJO: Este método fue creado para ser empleado por las clases de objetos creadas por tí
     * de tal manera que puedan saber que tipo de dato le enviaron y asó pueda dar el 
     * valor base [que provendrá de uno de sus atrib] y así proceder a llamar al método
     * esMayor o esIgual y hacer la respectiva comparación xD
     * esMayor de aquí xD
     * @param elObjeto
     * @return
     */
    public String darTipoInstancia(Object elObjeto){
        if(elObjeto instanceof Double){
            return "Double";
        }if(elObjeto instanceof Character){
            return "Character";
        }if(elObjeto instanceof  Boolean){
            return "Boolean";
        }if(elObjeto instanceof String){
            return "String";
        }
        
        return "Objeto";//puesto que podría tener atribs de objetos propios...
    }
    //NOTA: NO será necesario tener un método para envolver a los tipos primitivos, puesto que se envuelven automáticamente :,) 
    
}
