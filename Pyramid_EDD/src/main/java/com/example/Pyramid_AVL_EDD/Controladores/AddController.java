/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Controladores;

import com.example.Pyramid_AVL_EDD.Backend.Manejadores.ManejadorAVL;
import com.example.Pyramid_AVL_EDD.Backend.Objetos.Advice.Advice;
import com.example.Pyramid_AVL_EDD.Backend.Objetos.Advice.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phily
 */
@RestController
public class AddController {
    private ManejadorAVL manejadorAVL = ManejadorAVL.getAVLHandler();
    private Advice advice = new Advice();    
    
    @PostMapping(value = "/Game/add",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advice> addCard(@RequestBody String JSON){//puesot que no es una queryString, no es necesario add la etiqueta...
        if(this.manejadorAVL.insertCard(JSON)){
            return new ResponseEntity<>(advice.getAdvice(Type.OK, "Carta agregada con éxito"), HttpStatus.OK);
        }else if(this.manejadorAVL.getErrors().contains("sintaxis") || this.manejadorAVL.getErrors().contains("petición")){
            return new ResponseEntity<>(advice.getAdvice(Type.OTHER, "Ha surgido un error al momento de crear el árbol\n\n"+this.manejadorAVL.getErrors()), HttpStatus.BAD_REQUEST);//mientras no tengas respuesta a si solo debemos enviar el código de error o no, aún no podrás enviar el string que contiene los errores...         
        }
        
        return new ResponseEntity<>(advice.getAdvice(Type.DUPLICATE_CARD, this.manejadorAVL.getErrors()), HttpStatus.NOT_ACCEPTABLE);        
    }       
}