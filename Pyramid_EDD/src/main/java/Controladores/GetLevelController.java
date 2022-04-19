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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phily
 */

@RestController
public class GetLevelController {
    private ManejadorAVL manejadorAVL = ManejadorAVL.getAVLHandler();
    private Advice advice = new Advice();
    
    @GetMapping("/Game/get-level")
    public ResponseEntity<Advice> getLevel(@RequestParam(value = "level", defaultValue = "0") String level){//para evitar errores, mejor recibirás un String y si ese diera una excepción al hacer el casteo, entonces se mostrará en pantalla y al menos no se morirá la app xD
        String JSON;
        
        if((JSON = this.manejadorAVL.getLevel(level)) != null){
            return new ResponseEntity<>(advice.getAdvice(Type.OK, JSON), HttpStatus.OK);
        }
        return new ResponseEntity<>(advice.getAdvice(Type.OTHER, this.manejadorAVL.getErrors()), HttpStatus.BAD_REQUEST);
    }
    
}


//aunque ahora que lo pienso, creo que cuando no envíen algo, esto se va a morir, porque no tengo hereadado el 
//objeto/controlador al que se add la tag, para que se exe automáticamente...