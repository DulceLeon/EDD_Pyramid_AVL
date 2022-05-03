/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.EDD;

import com.example.Pyramid_AVL_EDD.Backend.Manejadores.ManejadorDeErrores;
import com.example.Pyramid_AVL_EDD.Backend.Tools.Tool;

/**
 *
 * @author phily
 * @param <E>
 */
public class BinaryTree <E> {
    private final Tool herramientas = new Tool();    
    //no hice privado el atributo para que pueda ser heredado... no olvides, lo privado no se hereda... es razonable xD, porque solo le pertenece al que lo posee xD
    public Node<E> raiz = null;//lo que deberás hacer es colocar el nodo con sus respectivas funciones, NO CAMBIES EL NODO uq creaste, en la parte de eliminación coloca ya sea la condi der !=null o izq == null o cb los contenidos de ese else iff cuando el dato es gual por el del else de abajito y que ese quede con el contenido de ese else if xD
    private ManejadorDeErrores manejadorErrorres;   
    
    public void setErrorHandler(ManejadorDeErrores manejadorDeErrores){
        this.manejadorErrorres = manejadorDeErrores;
    }    
    
    public void insert(Object dato){                
       this.insert(raiz, 0, dato);
    }
        
    private void insert(Node nodo, int nivel, Object dato) {
        Node nuevoNodo = new Node(dato);
        
        if (raiz == null) {
            nuevoNodo.resetLevel(nivel);
            raiz = nuevoNodo;
            
            System.out.println("Se ha insertado el dato "+ dato +" como la RAÍZ");
        } else {
            Node nodoAuxiliar = nodo;
            Node nodoAnterior = null;//no se puede reducri esta var por el hecho que si se revisa en la condición directamente a los hijos podría suceder el caso en el que la raíz es null y con ello provocar un nullPointer...
            
            while (nodoAuxiliar != null) {//se exe al menos una vez, por el hecho que la raíz no es null... xD
                nodoAnterior = nodoAuxiliar;
                
                if (herramientas.esMayor(nodoAuxiliar.getContent(), nuevoNodo.getContent())){//aux = objeto Base; nuevoNodo  = objetoAComparar
                    nodoAuxiliar = nodoAuxiliar.getRightChild();
                } else{//<=
                    nodoAuxiliar = nodoAuxiliar.getLeftChild();
                }                
                nivel++;//puesto que la estrcutura de if's anterior, abarca los tres casos posibles... aunque en realidad el caso cuando hay datos iguales, se abracará con otro método, puesto que por el comportamiento del programa online, ese seteo en realidad no es así, porque se irá a la izq, solo si de ese lado hay otro elemento, de lo contrario no...
            }
            
            //puesto que solo en el while se sabía si era mayor o menor, se debe hacer de nuevo aquí afuerita, la revisión
            if(nodoAnterior != null){//a mi parecer nunca llegaría a ser null, pero por si xD
                nuevoNodo.resetLevel(nivel);//puesto que al llegar aquí ya se tiene por hecho que se va a insertar...
                
                if (herramientas.esMayor(nodoAnterior.getContent(), nuevoNodo.getContent())){///no hay problemas con un null pointer porque está el if para cuando la raiz es null... xD y solo en ese caso el elemento insertado no tendría un anterior...                   
                    nodoAnterior.setRightChild(nuevoNodo);
                    System.out.println("Se ha insertado el dato "+ dato);
                } else if(herramientas.esMayor(nuevoNodo.getContent(), nodoAnterior.getContent())) {//Esto permite que exista la aceptación de datos repetidos... en todo caso lo que debería hacer es colocar un if para < y otro en el que sea = enviar un msje diciendo que nos e admiten... y no se establezca nada...
                    nodoAnterior.setLeftChild(nuevoNodo);     
                    System.out.println("Se ha insertado el dato "+ dato);
                }else if(herramientas.esIgual(nuevoNodo.getContent(), nodoAnterior.getContent())){//este else se vuelve inncesario por la revisión de existencia que se realiza previamente... aunque podría hace la revisión aquí, y así ahorrarme ese paso que agrega carga al programa...
                    System.out.println("Ya se encuentra almacendo un dato igual");
                     this.manejadorErrorres.addError("El elemento " + dato.toString()+ "ya se encuentra almacenado en el árbol");
                }//lo comentado con /**/ es para cuando NO se deba permitir insertar datos con clave repetida...                             
            }           
        }        
    }    

    public ListaEnlazada<E> setInorder() {
        ListaEnlazada<E> sortElements = new ListaEnlazada<>();
        
        setInorder(raiz, sortElements);
        return sortElements;
    }

    private void setInorder(Node<E> laRaiz, ListaEnlazada<E> sortElements) {
        if (laRaiz != null) {
            setInorder(laRaiz.getLeftChild(), sortElements);
            sortElements.add(laRaiz.getContent());
            System.out.println(" " + laRaiz.getContent());
            setInorder(laRaiz.getRightChild(), sortElements);
        }
    }

    public ListaEnlazada<E> setPostorder() {
        ListaEnlazada<E> sortElements = new ListaEnlazada<>();
        
        setPostorder(raiz, sortElements);
        return sortElements;
    }

    private void setPostorder(Node<E> laRaiz, ListaEnlazada<E> sortElements) {
        if (laRaiz != null) {
            setPostorder(laRaiz.getLeftChild(), sortElements);
            setPostorder(laRaiz.getRightChild(), sortElements);
            System.out.println(laRaiz.getContent() + " ");
            sortElements.add(laRaiz.getContent());
        }
    }

    public ListaEnlazada<E> setPreorder() {
        ListaEnlazada<E> sortElements = new ListaEnlazada<>();
        
        setPreorder(raiz, sortElements);
        return sortElements;
    }

    private void setPreorder(Node<E> laRaiz, ListaEnlazada<E> sortElements) {
        if (laRaiz != null) {
            System.out.println(laRaiz.getContent() + " ");
            sortElements.add(laRaiz.getContent());
            setPreorder(laRaiz.getLeftChild(), sortElements);
            setPreorder(laRaiz.getRightChild(), sortElements);
        }
    }//estos métodos para hacer el ordenamiento, no req invocar al addError del manejador de errores   
    
    public void getHight(Object dato){
        Node nodo = find(raiz, dato);
        
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

    public Node<E> search(Object dato) {//colocqué un retorno de tipo Nodo, para que se pudiera emplear en el método de dar Altura... aunque de los dos qeu dan la altura, el más importante es el que halla la altura... xD
        Node nodo = find(raiz, dato);        
        
        return nodo;//NO se muestra un msje coherente, pero si funciona como debe UwU, lo digo porque ya lo corroboré con el debbuger xD [lo que sucede es que al llegar a la primer llamada, la var laRaiz tiene como valor literalmente la raíz... entonces eso es lo que termina reciviendo el método que muestra el msje :v
                    //lo que tendŕia que hacerse el almacenar el dato al llegar a la última llamada[Es decir en la cual se halló] y en lugar de mostrar el dato que devuleve el método que realiza la axn mostrar un msje a base del contenido de esa var xD... pero la cuestión sería establecerla en el punto correcto, porque sino terminaría obteniendo otroo valor, esto es más que todo cuando el dato no está xD
    }
    
    private Node<E> find(Node nodoActual, Object dato) {//coloco object, puesto que cuando se emplee este método, éste rev¿cibirá como parám el tipo correspondiente... lo cual se hará realidad por medio de la interfaz... xD
        if (nodoActual == null || herramientas.esIgual(nodoActual.getContent(), dato)) {//aquí se empleará el equals sobreescrito...       
            if(nodoActual == null){               
                System.out.println("El dato \""+dato+"\" "+"NO está almacenado");
                this.manejadorErrorres.addError("El dato no ha sido encontrado");
            }else {//Aquí solo entrará una vez, sin importar cuántas veces se llame a este método recursivamente...
                System.out.println("La búsqueda se ha realizado exitosamente");
            }
            return nodoActual;
        }
        if (herramientas.esMayor(nodoActual.getContent(), dato)) {//para evitar crear otro método, para el caso de z, se usará la equivalencia de raiz < dato, que sería dato > raíz...
            return find(nodoActual.getRightChild(), dato);
        }
        return find(nodoActual.getLeftChild(), dato);
    }
    
    private Node<E> findFather(Node<E> padre, Node hijo, Object dato) {//coloco object, puesto que cuando se emplee este método, éste rev¿cibirá como parám el tipo correspondiente... lo cual se hará realidad por medio de la interfaz... xD
        if (hijo == null || herramientas.esIgual(hijo.getContent(), dato)) {//aquí se empleará el equals sobreescrito...       
            if(hijo == null){               
                System.out.println("El dato \""+dato+"\" "+"NO está almacenado");
                this.manejadorErrorres.addError("El dato no ha sido encontrado");
                
                return null;//con tal de saber si el padre, puesto que este simepre será != null, en realidad se devolvió porque tiene al hijo buscado...
            }else {//Aquí solo entrará una vez, sin importar cuántas veces se llame a este método recursivamente...
                System.out.println("La búsqueda se ha realizado exitosamente");
            }
            return padre;
        }
        if (herramientas.esMayor(hijo.getContent(), dato)) {//para evitar crear otro método, para el caso de z, se usará la equivalencia de raiz < dato, que sería dato > raíz...
            return findFather(hijo, hijo.getRightChild(), dato);
        }
        return findFather(hijo, hijo.getLeftChild(), dato);
    }
    
    public ListaEnlazada<E> getElementsOfLevel(int nivel){    
        ListaEnlazada<E> lista = new ListaEnlazada<>();
        return this.getElementsOfLevel(lista, raiz, nivel);    
    }
    
    public ListaEnlazada<E> getElementsOfLevel(ListaEnlazada<E> lista, Node<E> node, int nivel){
        if (node != null) {            
            getElementsOfLevel(lista, node.getLeftChild(), nivel);
            
            if(node.getLevel()  == nivel){
                lista.add(node.getContent());
                   
                if(nivel == 0){                 
                    return lista;
                }                
            }                        
            getElementsOfLevel(lista, node.getRightChild(), nivel);
        }  
        
        return lista;
    }//escogí hacer una búsqueda recursiva como en el inorden, porque ese es el que me permitirá que la lista tenga los nodos respectivos de izq a der...
    //puede que quizá sea necesario, colocar el level == nivel, entre las llamadas recursivas, en caso no funcione, pues si sucede eso, entonces quiere decir que esto debería colocarse con el centro como si fuera el equivalente...
    
    public boolean delete(Object dato){
        Node padre = findFather(null, raiz, dato);
                
        if(padre != null){    
            boolean esHijoIzquierdo = ((padre.getLeftChild()!= null)?((herramientas.esIgual(padre.getLeftChild().getContent(), dato))):false);
            
            if(((esHijoIzquierdo)?padre.getLeftChild():padre.getRightChild()).getLeftChild()== null 
              && ((esHijoIzquierdo)?padre.getLeftChild():padre.getRightChild()).getRightChild()== null) {                    
               
                if(esHijoIzquierdo){
                    padre.resetLeftChild(null);
                }else{
                    padre.resetRightChild(null);
                }                
                System.out.println("Dato eliminado exitosamente");
            }else {
                this.manejadorErrorres.addError("No es posible eliminar nodos con hijos");
                return false;
            }                 
        }        
        
        return true;//NO se muestra un msje coherente, pero si funciona como debe UwU, lo digo porque ya lo corroboré con el debbuger xD [lo que sucede es que al llegar a la primer llamada, la var laRaiz tiene como valor literalmente la raíz... entonces eso es lo que termina reciviendo el método que muestra el msje :v
    }
    
    public void graficar(String dotPath, String imageDirectory){
        Node<E> auxiliar = raiz;
        auxiliar.graficar(dotPath, imageDirectory);
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

