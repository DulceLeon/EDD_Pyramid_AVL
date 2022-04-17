/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Backend.EDD.AVLTree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * Clase empleada para devolver la representación
 * grafica del árbol AVL existente al solicitar la
 * axn
 * @author phily
 */

@RestController
public class SortController {    
    
    @GetMapping("/Game/avltree")
    public AVLTree sortTree(@RequestParam(value = "transversal", defaultValue = "inOrder") String transversal){
                
        
        
        return null;//se retorna la instancia del árbol, para que así pueda ser convertida a un JSON...
    }
    
    
}
