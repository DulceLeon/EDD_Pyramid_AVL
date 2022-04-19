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
public class Node<E> {
    private E contenido;    
    private Node<E> leftChild;
    private Node<E> rightChild;
    private int level;
      
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
    
}
