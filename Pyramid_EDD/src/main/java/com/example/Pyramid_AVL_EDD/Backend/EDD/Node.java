/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.EDD;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author phily
 */
public class Node<E> {
    private E contenido;    
    private Node<E> leftChild;
    private Node<E> rightChild;
    private int level;
    
    private final String GRAPH_NAME = "AVL.dot";
      
    public Node(E elElemento){
        this(elElemento, null, null);//Es decir que cuando el padre sea null, el nodo que se está inspeccionando será la raíz...
    }
    
    public Node(E elElemento, Node<E> elIzquierdo, Node<E> elDerecho){//coloco al padre para que así se puede hacer "regresiones" como en el caso de las listas enlazadas, pero no se si estas regresiones (a niveles de mayor jerarquí o más arriba, para el caso de los árboles) se logran con la recursividad, según lo que estba pensando, sí es aśi, cuadno estés en el proceso de la búsqueda inténtal        
        contenido = elElemento;
        
        leftChild = elIzquierdo;
        rightChild = elDerecho;             
    }
    
    public void setRightChild(Node<E> elHijoDerecho){
        rightChild = elHijoDerecho;
    }
    
    public void setLeftChild(Node<E> elHijoIzquierdo){
        leftChild = elHijoIzquierdo;
    }
    
    public void resetLevel(int nivel){
        this.level = nivel;
    }
    
   /* public void establecerHijoDerecho(E elElemento, int elIdentificador){
        rightChild = new Nodo<>(elElemento, elIdentificador, null, null, this);               
    }
    
    public void setLeftChild(E elElemento, int elIdentificador){
        leftChild = new Nodo<>(elElemento, elIdentificador, null, null, this);               
    }*/
    
    /*Estos métodos para reestableces los cree por la eliminación, puesto que al eli un nodo que contenga hijos, el orden que poseía el árbol cb*/

     /**
     * Para cuando quieras que solo al obj que se le app este método
     * sufra cambios de CONTENIDO (pues si es de ref solo se actualizaría
     * el objeto al que se le asignó l anueva dir...)y no todos los 
     * que hacen refernecia al obj ant...
     * @param contenidoNuevoHijoDerecho
     */ 
    public void resetRightContent(E contenidoNuevoHijoDerecho){
        rightChild.resetContent(contenidoNuevoHijoDerecho);
    }    
    
    /**
     * Para cuando quieras que solo al obj que se le app este método
     * sufra cambios de CONTENIDO (pues si es de ref solo se actualizaría
     * el objeto al que se le asignó l anueva dir...)y no todos los que
     * hacen refernecia al obj ant...
     * @param contenidoNuevoHijoIzquierdo
     */      
    public void resetLeftContent(E contenidoNuevoHijoIzquierdo){//coloco como parámetro el contenido, para evitar problemas de actualización de datos, debido a las referencias manejadas con los objetos...
        if(leftChild!=null){
            leftChild.resetContent(contenidoNuevoHijoIzquierdo);
        }else{
            leftChild = new Node<>(contenidoNuevoHijoIzquierdo, null, null);
        }
        
    }
    
    /**
     * util cuando quieras que todos los obj que hacen ref al antiguo 
     * nodo se actualicen con el cambio de CONTENIDO (pues si es de ref
     * solo se actualizaría el objeto al que se le asignó l anueva dir...)
     * @param nuevoHijoDerecho
     */
    public void resetRightChild(Node<E> nuevoHijoDerecho){
        rightChild = nuevoHijoDerecho;
    }   
    
    /**
     * util cuando quieras que todos los obj que hacen ref al antiguo 
     * nodo se actualicen con el cambio de CONTENIDO (pues si es de ref
     * solo se actualizaría el objeto al que se le asignó l anueva dir...)
     * @param nuevoHijoIzquierdo
     */
    public void resetLeftChild(Node<E> nuevoHijoIzquierdo){//coloco como parámetro el contenido, para evitar problemas de actualización de datos, debido a las referencias manejadas con los objetos...
        leftChild = nuevoHijoIzquierdo;
    }
    
    public void resetContent(E elElemento){
        contenido = elElemento;
    }
    
    public int getLevel(){
        return this.level;
    }
       
    public Node<E> getLeftChild(){
        return leftChild;
    }
    
    public Node<E> getRightChild(){
        return rightChild;
    }
    
    public E getContent(){
        return contenido;
    }
    
    public int getChildsNumber(){//Estaba pensando que quizá sería útil para obtener el número total de hijos... xD
        if(rightChild != null && leftChild !=null){
            return 2;
        }else if(rightChild == null && leftChild == null){
            return 0;
        }
        return 1;
    }//literalmente para hijos, porque los nietos no se cuentan con esto xD
    
     public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try
        {
            fichero = new FileWriter(this.GRAPH_NAME);
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } 
        catch (Exception e){
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        }finally{
           try {
                if (null != fichero)
                    fichero.close();
           }catch (Exception e2){
               System.err.println("Error al cerrar el archivo aux_grafico.dot");
           } 
        }                        
        try{
          Runtime rt = Runtime.getRuntime();
          rt.exec( "dot -Tjpg "+this.GRAPH_NAME+" -o "+path);//dot -Tjpg -o "+path+"AVL.dot
          //Esperamos medio segundo para dar tiempo a que la imagen se genere.
          //Para que no sucedan errores en caso de que se decidan graficar varios
          //árboles sucesivamente.
          Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot "+ ex.getMessage());
        }
    }
    
    private String getCodigoGraphviz() {
        return "digraph grafica{\n" +
               "rankdir=TB;\n" +
               "node [shape = record, style=filled, fillcolor=seashell2];\n"+
                getCodigoInterno()+
                "}\n";
    }
    
    private String getCodigoInterno() {
        String etiqueta;
        if(leftChild==null && rightChild==null){
            etiqueta="nodo"+contenido.hashCode()+" [ label =\""+contenido.toString()+"\"];\n";
        }else{
            etiqueta="nodo"+contenido.hashCode()+" [ label =\"<C0>|"+contenido.toString()+"|<C1>\"];\n";
        }
        if(leftChild!=null){
            etiqueta=etiqueta + leftChild.getCodigoInterno() +
               "nodo"+contenido.hashCode()+":C0->nodo"+leftChild.contenido.hashCode()+"\n";
        }
        if(rightChild!=null){
            etiqueta=etiqueta + rightChild.getCodigoInterno() +
               "nodo"+contenido.hashCode()+":C1->nodo"+rightChild.contenido.hashCode()+"\n";                    
        }
        return etiqueta;
    }        
    
}
