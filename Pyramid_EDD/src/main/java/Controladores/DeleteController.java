/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phily
 */

@RestController
public class DeleteController {
    
    @DeleteMapping("/Game/delete")//también lo hubieras podido separar en dos: @RequestMapping("/Game")[y para mejor orden colocarlo debajo de @RestController] y @DeleteMapping("/delete")
    public String deleteNode(String JSON){//se debe recibir un listado JSON, puesto que no es 1 obj, entonces no podría utilizar el @RequestBody, para así mapear de una vez al objeto, al momento de recibir el dato...
        
        //se llama al método que se encarga de convertir cada dato en el JSON a una carta 
        //[sin importar que esté repetida o no, a pesar de que no haga esa revisión, yo diría
        //que si tendría que revisar que el tipo que se reciba corresponda a un tipo de carta]
        //para luego ser enviada al árbol, deplano que se tendrá que hacer tb una lista para
        //almacenar una carta, o hacer que el método que se invoque aquí, recorra el JSON, con
        //un ciclo y que envíe solo el tipo al método convertidor para que así este último 
        //devuelva de una vez el elemento y se pueda enviar a hacer la inserción
        
        //quizá esta vez no será útil usar un parser de JSON, puesto que no se va a hacer la
        //conversión de una sola vez, sino que solo tomará un parámetro recibido en el JSON
        //y a partir de él hará la conversión 1 por 1...
        
        //también podría utilizarse una mini gramática, pero ya que sería muy pequeña y se deben
        //Reportar errores que el árbol pueda informar, y que puede haber más de 1 y que no se 
        //puede enviar directamente el msje, mejor con un méodo se hará xD
            //aunque esa acumulación bien la podría y debo hacerla ahora que lo pienso, concatenarla
            //en un string para hacer 1 sola devolución...      
        
        
        
        return "";//Aquí se enviará el msje ya sea de error o que se ha realizado la eli exitosamente...
    }
    
}
