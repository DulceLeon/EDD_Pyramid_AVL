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

/**
 *
 * @author phily
 */
public class AddController {
    private ManejadorAVL manejadorAVL = ManejadorAVL.getAVLHandler();
    private Advice advice = new Advice();    
    
    @PostMapping("/Game/add")    
    public ResponseEntity<Advice> addCard(String JSON){//puesot que no es una queryString, no es necesario add la etiqueta...
        if(this.manejadorAVL.insertCard(JSON)){
            return new ResponseEntity<>(advice.getAdvice(Type.OK, "Carta agregada con éxito"), HttpStatus.OK);
        }else if(!this.manejadorAVL.getErrors().contains("sintaxis") || !this.manejadorAVL.getErrors().contains("petición")){
            return new ResponseEntity<>(advice.getAdvice(Type.DUPLICATE_CARD, this.manejadorAVL.getErrors()), HttpStatus.NOT_ACCEPTABLE);
        }
        
        return new ResponseEntity<>(advice.getAdvice(Type.OTHER, "Ha surgido un error al momento de crear el árbol\n\n"+this.manejadorAVL.getErrors()), HttpStatus.BAD_REQUEST);//mientras no tengas respuesta a si solo debemos enviar el código de error o no, aún no podrás enviar el string que contiene los errores...
    }       
}
