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
 *
 * @author phily
 */

@RestController
public class GetLevelController {
    
    @GetMapping("/Game/get-level")
    public AVLTree getLevel(@RequestParam(value = "level", defaultValue = "1") String level){//para evitar errores, mejor recibirás un String y si ese diera una excepción al hacer el casteo, entonces se mostrará en pantalla y al menos no se morirá la app xD
        int nivel;
        
        try {
            nivel = Integer.parseInt(level);
            
            
        } catch (NumberFormatException e) {
            System.out.println("Imposible convertir el parámetro a un número");
        }       
        
        return null;
    }
    
}
