/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EDD;

import Backend.Tools.Tool;

/**
 *
 * @author phily
 */
public class BinaryTree <E> {
    private Tool herramientas = new Tool();    
    //no hice privado el atributo para que pueda ser heredado... no olvides, lo privado no se hereda... es razonable xD, porque solo le pertenece al que lo posee xD
    public Node<E> raiz = null;//lo que deberás hacer es colocar el nodo con sus respectivas funciones, NO CAMBIES EL NODO uq creaste, en la parte de eliminación coloca ya sea la condi der !=null o izq == null o cb los contenidos de ese else iff cuando el dato es gual por el del else de abajito y que ese quede con el contenido de ese else if xD
       
    public void insert(Object dato) {
        Node nuevoNodo = new Node(dato);
        
        if (raiz == null) {
            raiz = nuevoNodo;
            System.out.println("Se ha insertado el dato "+ dato +" como la RAÍZ");
        } else {
            Node nodoAuxiliar = raiz;
            Node nodoAnterior = null;//no se puede reducri esta var por el hecho que si se revisa en la condición directamente a los hijos podría suceder el caso en el que la raíz es null y con ello provocar un nullPointer...
            
            while (nodoAuxiliar != null) {//se exe al menos una vez, por el hecho que la raíz no es null... xD
                nodoAnterior = nodoAuxiliar;
                if (herramientas.esMayor(nodoAuxiliar.getContent(), nuevoNodo.getContent())){//aux = objeto Base; nuevoNodo  = objetoAComparar
                    nodoAuxiliar = nodoAuxiliar.getRightChild();
                } else{//<=
                    nodoAuxiliar = nodoAuxiliar.getLeftChild();
                }
            }
            
            //puesto que solo en el while se sabía si era mayor o menor, se debe hacer de nuevo aquí afuerita, la revisión
            if(nodoAnterior != null){
                if (herramientas.esMayor(nodoAnterior.getContent(), nuevoNodo.getContent())){///no hay problemas con un null pointer porque está el if para cuando la raiz es null... xD y solo en ese caso el elemento insertado no tendría un anterior...
                    nodoAnterior.setRightChild(nuevoNodo);
                    System.out.println("Se ha insertado el dato "+ dato);
                } else if(herramientas.esMayor(nuevoNodo.getContent(), nodoAnterior.getContent())) {//Esto permite que exista la aceptación de datos repetidos... en todo caso lo que debería hacer es colocar un if para < y otro en el que sea = enviar un msje diciendo que nos e admiten... y no se establezca nada...
                    nodoAnterior.setLeftChild(nuevoNodo);     
                    System.out.println("Se ha insertado el dato "+ dato);
                }else{
                    System.out.println("Ya se encuentra almacendo un dato igual");
                }//lo comentado con /**/ es para cuando NO se deba permitir insertar datos con clave repetida...                             
            }           
        }        
    }

    public void setInorder() {
        setInorder(raiz);
    }

    private void setInorder(Node laRaiz) {
        if (laRaiz != null) {
            setInorder(laRaiz.getLeftChild());
            System.out.println(" " + laRaiz.getContent());
            setInorder(laRaiz.getRightChild());
        }
    }

    public void setPostorder() {
        setPostorder(raiz);
    }

    private void setPostorder(Node laRaiz) {
        if (laRaiz != null) {
            setPostorder(laRaiz.getLeftChild());
            setPostorder(laRaiz.getRightChild());
            System.out.println(laRaiz.getContent() + " ");
        }
    }

    public void setPreorder() {
        setPreorder(raiz);
    }

    private void setPreorder(Node laRaiz) {
        if (laRaiz != null) {
            System.out.println(laRaiz.getContent() + " ");
            setPreorder(laRaiz.getLeftChild());
            setPreorder(laRaiz.getRightChild());
        }
    }   
    
    public void getHight(Object dato){
        Node nodo = find(dato);
        if(nodo!=null){            
            System.out.println("El dato se encuentra a "+findHight(nodo)+" de altura");
        }
    }
    
    public int findHight(Node nodo){//no lo cambio a private porque debo utilizarlo en el AVL... xD
        if(nodo ==null){
            return 0;
        }
        
        return 1 + Math.max(findHight(nodo.getLeftChild()), findHight(nodo.getRightChild()));        
    }

    public Node search(Object dato) {//colocqué un retorno de tipo Nodo, para que se pudiera emplear en el método de dar Altura... aunque de los dos qeu dan la altura, el más importante es el que halla la altura... xD
        Node nodo = find(dato);
        
        System.out.println("El dato \""+((nodo == null)?dato:nodo.getContent())
                           +"\" "+((nodo == null)?"NO":"SI")+" está almacenado");
        
        return nodo;//NO se muestra un msje coherente, pero si funciona como debe UwU, lo digo porque ya lo corroboré con el debbuger xD [lo que sucede es que al llegar a la primer llamada, la var laRaiz tiene como valor literalmente la raíz... entonces eso es lo que termina reciviendo el método que muestra el msje :v
                    //lo que tendŕia que hacerse el almacenar el dato al llegar a la última llamada[Es decir en la cual se halló] y en lugar de mostrar el dato que devuleve el método que realiza la axn mostrar un msje a base del contenido de esa var xD... pero la cuestión sería establecerla en el punto correcto, porque sino terminaría obteniendo otroo valor, esto es más que todo cuando el dato no está xD
    }
    
    private Node find(Object dato) {
        return find(raiz, dato);
    }
    
    private Node find(Node nodoActual, Object dato) {//coloco object, puesto que cuando se emplee este método, éste rev¿cibirá como parám el tipo correspondiente... lo cual se hará realidad por medio de la interfaz... xD
        if (nodoActual == null || herramientas.esIgual(nodoActual.getContent(), dato)) {//aquí se empleará el equals sobreescrito...                                    
            return nodoActual;
        }
        if (herramientas.esMayor(nodoActual.getContent(), dato)) {//para evitar crear otro método, para el caso de z, se usará la equivalencia de raiz < dato, que sería dato > raíz...
            return find(nodoActual.getRightChild(), dato);
        }
        return find(nodoActual.getLeftChild(), dato);
    }    
    
    public Node delete(Object dato){
        Node nodoAEliminar = find(dato);
                
        if(nodoAEliminar != null){
            if(nodoAEliminar.getLeftChild()==null && nodoAEliminar.getRightChild()==null) {                    
                nodoAEliminar = null;
            }else if(nodoAEliminar.getRightChild() != null){
                
            }else{
                
            }                 
        }
        
        System.out.println("El dato \""+dato+"\""+ ((nodoAEliminar != null)?" ha sido eliminado":" NO estaba almacenado"));                        
        
        return nodoAEliminar;//NO se muestra un msje coherente, pero si funciona como debe UwU, lo digo porque ya lo corroboré con el debbuger xD [lo que sucede es que al llegar a la primer llamada, la var laRaiz tiene como valor literalmente la raíz... entonces eso es lo que termina reciviendo el método que muestra el msje :v
    }
}

//**ACUERDO**
//Cuando un dato se requiera comparar con uno que ya está almacenado [como en el caso de insertar y search]
//el tipo de parámetro para este dato será OBJECT, a diferencia de cuando el dato que reciba el método sea para 
//re o establecer, el parámetro y el tipo de return será E xD


//**DEDUCCIÓN**
//bien hubiera podido utilizarse en lugar de los nodos solamente los árboles, pue en lugar de colocar hijoIzq o der sería subArbolIzq o der, aquí estaría de una vez
//la var para el contenido y el identificador, también tendría "raíz" o padre para así hacer los reajustes debido a eliminación, es decir todo lo que tiene la clase
//nodo estaría aquí, además de tener las acciones que se pueden realizar en este tipo de árboles :v xD

//por lo cual con todo eso llego a la conclusión que teniendo el nodo, no debo crear un atrib o estructura tal y como un subárbol, puesto que sería redundante y en vano
//porque el nodo cumple con la función de un subárbol, por lo cual cuando te toque recorrer el árbol, deberás hacerlo utilizando los atribs de los nodos, es decir, si el
//dato es menor que el del nodo actual, entonces te vas a hijo izquierdo sino al derecho, eso quiere decir que el método de búsqueda en lugar de recibir un "subárbol" de-
//bería tener como parámetro un nodo, para que así este método pueda utilizarse en recursividad xD

//Entonces en resumen, o es subárbol o es nodo xD, para orden mejor crear el nodo, pero para  que se mire más impresionante el código xD, usa subárbol como atrib del árbol xD

