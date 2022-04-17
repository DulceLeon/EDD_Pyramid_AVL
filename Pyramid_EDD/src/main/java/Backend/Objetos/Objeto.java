/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Objetos;

/**
 *
 * @author phily
 */
public class Objeto {
    int ID;
    
    public Objeto(int ID){
       this.ID = ID;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.ID;
        return hash;
    }
    
    @Override
    public boolean equals(Object objeto){
        Objeto elObjeto;
        
        if(objeto instanceof Objeto){
            elObjeto = (Objeto) objeto;
            
            return (elObjeto.ID == ID);
        }
        
       return false;
    }//hace falta poner este caso en Tool    
    
    public int compareTo(Objeto anotherObject){
        if(this.ID == anotherObject.getID()){
            return 0;
        }else if(this.ID > anotherObject.getID()){
            return 1;
        }
        
        return -1;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public int getID() {
        return ID;
    }
}
