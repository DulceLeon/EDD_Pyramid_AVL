/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Backend.Manejadores.ManejadorAVL;
import Backend.Objetos.Advice.Advice;
import Backend.Objetos.Advice.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phily
 */
@RestController
public class InitController {
    private ManejadorAVL manejadorAVL = ManejadorAVL.getAVLHandler();
    private Advice advice = new Advice();    
    
    @PostMapping("/Game/start")
    public ResponseEntity<Advice> initAVL(String JSON){//puesot que no es una queryString, no es necesario add la etiqueta...
        if(this.manejadorAVL.createTree(JSON)){
            return new ResponseEntity<>(advice.getAdvice(Type.OK, "El árbol creado con éxito"), HttpStatus.OK);
        }else if(!this.manejadorAVL.getErrors().contains("sintaxis")){
            return new ResponseEntity<>(advice.getAdvice(Type.DUPLICATE_CARD, this.manejadorAVL.getErrors()), HttpStatus.NOT_ACCEPTABLE);
        }
        
        return new ResponseEntity<>(advice.getAdvice(Type.OTHER, "Ha surgido un error al momento de crear el árbol\n\n"+this.manejadorAVL.getErrors()), HttpStatus.BAD_REQUEST);
    }    
}

//Esto (lo marcado con #1) era lo que anteriormente tenía pensado, pero en todo caso, puesto que el árbol no es un objeto normal xD
//mejor haré que se muestre la representación del árbol...
//(#1) se retorna la instancia del árbol, para que así pueda ser convertida a un JSON...
