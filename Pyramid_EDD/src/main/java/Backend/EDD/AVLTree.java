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
public class AVLTree<E> extends BinaryTree<E>{
    
    @Override
    public void insert(Object dato){
        super.insert(dato);
        
        equilibrarArbol(raiz, null, "ninguno");//para que así se revise todo el árbol, aunque quizá lo mejor sería enviar el papá el subarbol izq o der hijo de la raíz, donde se inertó el dato, para no tener que revisar todo xD, pero eso implica tener que cambiar el método de insert para saber a que hijo directo de la raíz se fue... si te da tiempo , hazo, porque al final de cuentas solo ese se debería arrehlar, y hasta donde se no provoca problemas con que no se encíe la raíz porque esta no cambia de lugar xD, mejor haz los métodos para hacer las rotaciones y luego decides si add el dar el árbol hijo directo de la raíz donde se almacenó el dato, claro si es que no da problemas y no requiere de cambios severos :v xD
    }
    
    @Override
    public Node<E> delete(Object dato){
        Node<E> nodo = super.delete(dato);        
        equilibrarArbol(raiz, null, "ninguno");//debe ser raíz porque si envías nodo provocarás un error, puesto que ese ya fue eliminado :v xD
        
        return nodo;
    }
    
    public int hallarFactorDeEquilibrio(Node<E> nodo){
        if(nodo != null){//en realidad no debería estar esta condi puesto que en el método de eqq árbol (método en el cual se llama directa e indirectamente) solo llama de manera directa a este método cuando el nodo!=null  y tambińe en el caso indirecto puesto que el meodo de hallarTipoDeRotación(en donde se hace una invocación de este método de hallarFactor) tb se llama solo cuando nodo!=null
            return findHight(nodo.getRightChild())-findHight(nodo.getLeftChild());
        }
        
        System.out.println("El nodo recibido no se encuentra en el árbol\n");//este msje no se mostrará al ser empleado este método en el de equilibrar árbol, por el hecho que se verfica que el nodod que intenta utilizar esto no sea null xD
        return 0;        
    }//funcional
    
    public void equilibrarArbol(Node<E> nodo, Node<E> padreDeNodo, String tipoHijo){//el null nunca se utilizará porque en la raíz al revisar el FE, nunca se encontrará un FE anormal, porque el desequilibrio se habrá arreglado (Si es que eixstiera xD) antes de llegar ahi xD
        if(nodo != null){
            equilibrarArbol(nodo.getRightChild(), nodo, "derecho");//Creo que al fianl de cuentas esto no da problema, ademśa creo que es la única manera de revisar los hijos del nodo en cuestión de manera correcta... o creo que lo puse así porque tenía que ver con la reglamnetación para hacer el reequilivrio :v, pero sí funciona xD, CREO... XD
            equilibrarArbol(nodo.getLeftChild(), nodo, "izquierdo");
            
            int factorDeEquilibro = hallarFactorDeEquilibrio(nodo);//el tener el llamado aquí, permite que aunque se haga un reequilibrio, al buscar el FE del papá (o de los nodos de niveles más arriba) se obtenga un valor verídico, puesto que se vuelve a pedir la altura de los hijos a pesar de haberla buscado antes, lo cual debe suceder puesto que existen cambios debido al reequilibrio realizado...
            
            if(Math.abs(factorDeEquilibro) > 1){
                //las condiciones para ejecutar la rotación que corresponde según sea el caso... 
                hallarTipoRotacion(factorDeEquilibro, nodo, padreDeNodo, tipoHijo);//el anterior es el padre del desequilibrado...
            }
        }
        //no se si colocar un return provoque que todas las llamadas recursivas previas se cancelen [de alguna manera pienso que puede suceder esto, a pesar de que los métodos de recursión que se han usado siempre han devuelto algo :v xD9
        //o si el no colocarlo provocará que no se regrese bien al método previo (aunque sabes que cuando termina un método le devuelve el mando a aquel quein lo llaamó y por ese motivdo no debería dar problemas :V xD)
    }//funcional          
    
    private void hallarTipoRotacion(int factorDeEquilibrio, Node<E> nodo, Node<E> nodoPadre, String tipoHijo){//este tipo de hijo es del nodo con FE = |2|                        
        if(factorDeEquilibrio == -2){
            if(hallarFactorDeEquilibrio(nodo.getLeftChild()) == -1){//se requiere simple a la derecha...
               rotarSimplementeALaDerecha(nodo, nodoPadre, tipoHijo);
            }else{//Es decir que es +1, no puede ser otro número por la naturaleza del problema, pues si fuera 0, entonces no se habría provocado un desequilibrio en el padre de este... son normas xD
               rotarDoblementeALaIzquierda(nodo, nodoPadre, tipoHijo);//Si no entras a este lo más seguro es que e lFE debía colocarse solo como + porque no es necesariamente +1, aunque yo pienso que sí, porque se ha estado arreglado el desequilibrio desde la parte infereior del aárbol, por lo cual no tendría que obtenerese un FE mayor a |1| en el hijo correspondiente del desequilibrado actual...
            }  
            //por si acaso es posible o se debería evitar desmoronar el eqq del hijo puesto que tiene FE = 0, por medio de la exe de una rot simle para esos casos...
            /*if(hallarFactorDeEquilibrio(nodo.getLeftChild()) == 1){//se requiere simple a la derecha...
               rotarDoblementeALaIzquierda(nodo, nodoPadre);//Si no entras a este lo más seguro es que e lFE debía colocarse solo como + porque no es necesariamente +1, aunque yo pienso que sí, porque se ha estado arreglado el desequilibrio desde la parte infereior del aárbol, por lo cual no tendría que obtenerese un FE mayor a |1| en el hijo correspondiente del desequilibrado actual...               
            }else{//Es decir que es +1, no puede ser otro número por la naturaleza del problema, pues si fuera 0, entonces no se habría provocado un desequilibrio en el padre de este... son normas xD
               rotarSimplementeALaDerecha(nodo, nodoPadre);
            }*/           
        }else{//Es decir es igual a +2, no puede ser otro número porque el desequilibrio se arregla cada vez que un nodo lo provoca y de manera obligatoria al usar este método es porque el FE no es normal, es decir no es ni 0, 1 o .1...
            if(hallarFactorDeEquilibrio(nodo.getRightChild()) == 1){//se requiere simple a la izquierda
               rotarSimplementeALaIzquierda(nodo, nodoPadre, tipoHijo);
            }else{//es decir que es -1, por el hecho que solo 1 y -1 podrían provocar un desequilibrio...
               rotarDoblementeALaDerecha(nodo, nodoPadre, tipoHijo);
            }   
            //por si acaso es posible o se debería evitar desmoronar el eqq del hijo puesto que tiene FE = 0, por medio de la exe de una rot simle para esos casos...
            /*if(hallarFactorDeEquilibrio(nodo.getRightChild()) == -1){//se requiere simple a la izquierda
               rotarDoblementeALaDerecha(nodo, nodoPadre);               
            }else{//es decir que es -1, por el hecho que solo 1 y -1 podrían provocar un desequilibrio...
               rotarSimplementeALaIzquierda(nodo, nodoPadre);
            }*/  
        }        
    }//sale mejor que estar haciendo un método para dar true or false por cada tipo de rotación... pues si se tuviera que hacer la última, se tendrían que hacer más llamadas del métood para hallar FE, que en este xD
    
    private void rotarSimplementeALaIzquierda(Node<E> nodoDesequilibrado, Node<E> padreDelDesequilibrado, String tipoHijo){
        this.resetLevels_Left(nodoDesequilibrado, nodoDesequilibrado.getRightChild(), nodoDesequilibrado.getRightChild().getRightChild());//en un caso de rotación a la izq normal, siempre el 3er nodo que en conjunto con los demás, provoca desequilibrio, es el hijo derecho del derecho, o sea el nieto derecho xD        
        Node<E> nodoAuxiliar = nodoDesequilibrado.getRightChild();//se resguarda el nodo que reemplazará al desequilibrado...
        
        nodoDesequilibrado.resetRightChild(nodoAuxiliar.getLeftChild());//Se reubica el hijo izq del nodo de reemplazo, en el hijo der donde se encontraba el hijo que reemplezará al padre, puesto que en este lugar (del nodo de reemplazo) quedará el desequilibrado
        nodoAuxiliar.resetLeftChild(nodoDesequilibrado);//se restablece el hijo izq del nodo de reemplazo como el nodo desequilibrado        
        
        switch (tipoHijo) {//mejor deje las condiciones aquí porque si las coloco en un método que me permita evitar tenerlo en ambos métodos [para rotIzq y rotDer simples] aún así tendría que colocar otros 2 para cuando fueran doble y al hacer eso terminaría con más bloques de estos [específicamente 1 más es decir:3...] que al dejarlos aquí 
            case "derecho":
                padreDelDesequilibrado.resetRightChild(nodoAuxiliar);//se hace actualiza el hijo del que era padre del desequilibrado al de reemplazo, es decir el aux...
                break;
            case "izquierdo":
                padreDelDesequilibrado.resetLeftChild(nodoAuxiliar);
                break;
            default://por el envío del tipoHijo, ya no es necesario tener que revisar si el padre es null o no, porque en el caso de que sea así, es decir en el caso que el desequilibrado sea la raíz, lo que se envía como valor a ese parámetro de tipoHIjo es "ninguno" lo cual no concuerda con las únicas dos opciones existentes para realizar un reestalecimiento... xD
                raiz = nodoAuxiliar;
                break;
        }
    }//funcional 
    
    private void resetLevels_Left(Node<E> nodoDesequilibrado, Node<E> hijoDerecho, Node<E> nietoDerecho){
        //Se resetean los niveles        
        nodoDesequilibrado.resetLevel(nodoDesequilibrado.getLevel()-1);
        hijoDerecho.resetLevel(hijoDerecho.getLevel()+1);//debido a las rotaciones dobles, fijos fijos siempre serán los primeros dos nodos, el tercero podría o no existir... pero de igual forma siempre sería el derecho en el caso de las rot a la izq...
        
        if(nietoDerecho != null){
            nietoDerecho.resetLevel(nietoDerecho.getLevel()+1);
        }
    }
    
    private void rotarSimplementeALaDerecha(Node<E> nodoDesequilibrado, Node<E> padreDelDesequilibrado, String tipoHijo){
        this.resetLevels_Right(nodoDesequilibrado, nodoDesequilibrado.getLeftChild(), nodoDesequilibrado.getLeftChild().getLeftChild());
        Node<E> nodoAuxiliar = nodoDesequilibrado.getLeftChild();//se resguarda el nodo que reemplazará al desequilibrado...
        
        nodoDesequilibrado.resetLeftChild(nodoAuxiliar.getRightChild());//Se reubica el hijo der del nodo de reemplazo, puesto que en este lugar quedará el desequilibrado y el hijo izquierdo (el lugar donde se colocará) correspondía al hijo que reemplazará a dicho desequilibrado...
        nodoAuxiliar.resetRightChild(nodoDesequilibrado);//se restablece el hijo der del nodo de reemplazo como el nodo desequilibrado [antes se usaba el método que reestablece contenido... pero si debe ser el nodo, por la referncia a los hijos que podría tener...        
        
        switch (tipoHijo) {//mejor deje las condiciones aquí porque si las coloco en un método que me permita evitar tenerlo en ambos métodos [para rotIzq y rotDer simples] aún así tendría que colocar otros 2 para cuando fueran doble y al hacer eso terminaría con más bloques de estos [específicamente 1 más es decir:3...] que al dejarlos aquí 
            case "derecho":
                padreDelDesequilibrado.resetRightChild(nodoAuxiliar);//se hace actualiza el hijo del que era padre del desequilibrado al de reemplazo, es decir el aux...
                break;
            case "izquierdo":
                padreDelDesequilibrado.resetLeftChild(nodoAuxiliar);
                break;
            default:             
                raiz = nodoAuxiliar;
                break;
        }//recuerda que no hay problemas con el hecho de que el padre haya cambiado porque esto podría suceder únicamente al llegar a evaluarlo a él, lo cual sucedería después de haber terminado con cada uno de sus hijos, por lo cual se puede decir que el padre no cambiará al menos mientras no se haya terminado de realizar las revisiones de los hijos...xD      
    }//funcional   
    
    private void resetLevels_Right(Node<E> nodoDesequilibrado, Node<E> hijoIzquierdo, Node<E> nietoIzquierdo){
        //Se resetean los niveles        
        nodoDesequilibrado.resetLevel(nodoDesequilibrado.getLevel()-1);
        hijoIzquierdo.resetLevel(hijoIzquierdo.getLevel()+1);//debido a las rotaciones dobles, fijos fijos siempre serán los primeros dos nodos, el tercero podría o no existir... pero de igual forma siempre sería el derecho en el caso de las rot a la izq...
        
        if(nietoIzquierdo != null){
            nietoIzquierdo.resetLevel(nietoIzquierdo.getLevel()+1);
        }
    }//puedo hacer esto así de simple, puesto que como el árbol, se creará desde 0, entonces no habrá nada más debajo de los nodos involucrados en el deseq, puesto que el nodo add generó eso, entonces todo se encuentra en una parte final. Y en caso de afectar tb a lo de arriba ahí si habría problema, pero no creo...
    
    private void rotarDoblementeALaIzquierda(Node<E> nodoDesequilibrado, Node<E> padreDelDesequilibrado, String tipoHijo){//este tipo de hijo es del desequilibrado, es decir del que tiene FE = |2|
        //este orden es así, pues se debe arreglar primero el hijo y luego al padre 
        rotarSimplementeALaIzquierda(nodoDesequilibrado.getLeftChild(), nodoDesequilibrado, "izquierdo");//Se supone que se deberí aactualizar el mero obj al salir de este proceso ;;        
        rotarSimplementeALaDerecha(nodoDesequilibrado, padreDelDesequilibrado, tipoHijo);//puesto que aquí se trabaja con el desequilibrio que se encuentra más arriba, que es justo por el cual se entró a este método...
    }//funcional

    private void rotarDoblementeALaDerecha(Node<E> nodoDesequilibrado, Node<E> padreDelDesequilibrado, String tipoHijo){
        rotarSimplementeALaDerecha(nodoDesequilibrado.getRightChild(), nodoDesequilibrado, "derecho");
        rotarSimplementeALaIzquierda(nodoDesequilibrado, padreDelDesequilibrado, tipoHijo);
    }//funcional
    
}
