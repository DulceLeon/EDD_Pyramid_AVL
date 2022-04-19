/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EDD;

/**
 *
 * @author phily
 */
public class ListaEnlazada<E> {
      private NodoLista<E> primerNodoLista;
      private NodoLista<E> ultimoNodoLista;
      private int size;
    
    public ListaEnlazada(){
        clear();
    }
    
    private boolean inicializarLista(E contenido){
        if(size == 0){
            primerNodoLista = ultimoNodoLista = new NodoLista<>(contenido);           
            //Aquí no se incre el size, puesto que al ser un métodoo privado e invocado dentro de otro método,
            //entonces el método que le invoca por ser el general, se encargará de hacer el incre respectivo...
            return true;
        }        
        return false;
    }
        
    public void add(E contenido){//agrega al final de la lista
        if(!inicializarLista(contenido)){                   
            ultimoNodoLista.setNext(contenido);
            ultimoNodoLista = ultimoNodoLista.getNext();            
        }        
        size++;
    }
    
    public void clear(){
        primerNodoLista = ultimoNodoLista = null;        
        size = 0;      
    }
      
    public NodoLista<E> removeFirstElement(){
        NodoLista<E> nodoAuxiliar = primerNodoLista;
        primerNodoLista = primerNodoLista.getNext();
        size--;
        
        return nodoAuxiliar;
    }
    
    public E getElement(int position){
        NodoLista auxiliar = null;
        
        for (int actual = 0; actual < this.size; actual++) {
            auxiliar = auxiliar.getNext();
            
            if(actual == position){
                break;
            }
        }
        
        return ((auxiliar != null)?((E) auxiliar.getContent()):null);
    }
    
    public NodoLista<E> getFirst(){
        return primerNodoLista;
    }
    
    public NodoLista<E> getLast(){
        return ultimoNodoLista;
    }
    
    public boolean isEmpty(){
        return (size==0);
    }      
    
    public int size(){
        return size;
    }
    
}
