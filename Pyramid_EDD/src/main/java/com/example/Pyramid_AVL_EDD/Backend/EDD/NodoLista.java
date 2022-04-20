/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.EDD;

/**
 *
 * @author phily
 */
public class NodoLista<E> {
    private E contenido;    
    private NodoLista<E> siguiente;
    private NodoLista<E> ultimoNodoLista;
    
    /**
    *ctrctor para 1er elemento es decir, cabeza
    * @param elemento
    */
    public NodoLista(E elemento){
          contenido = elemento;
          this.siguiente = null;        
    }
    
    public void resetContent(E contenidoNuevo){
        contenido = contenidoNuevo;                      
    }//será útil para la ordenación...

    public void setNext(E contenido){            
        siguiente = new NodoLista(contenido);
    }      

    public E getContent(){//no será necesario el índice?? para hacer ref a uno específico y obtener sus respect datos??
        return contenido;
    }

    public NodoLista<E> getNext(){//Aqupi estas refiriendote al nodo, mas no al objeto que dentro de él está contenido
        return siguiente;
    }              
}
