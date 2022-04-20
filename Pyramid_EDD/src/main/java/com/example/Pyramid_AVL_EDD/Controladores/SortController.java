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
    private ManejadorAVL manejadorAVL = ManejadorAVL.getAVLHandler();
    private Advice advice = new Advice();
    
    @GetMapping(value = "/Game/avltree",    
    produces =  MediaType.APPLICATION_JSON_VALUE)//eliminé la especificación de consume, puesto que lo que recibe no es un RequestBody de tipo JSON, sino un parám por medio de queryStrings xD
    public ResponseEntity<Advice> sortTree(@RequestParam(value = "transversal") String transversal){//defaultValue = "inOrder" con tal de que cuando no envíe bien el parámetro se muestre un error
        String JSON;
        
        if((JSON = this.manejadorAVL.getSort(transversal)) != null){
            return new ResponseEntity<>(advice.getAdvice(Type.OK, JSON), HttpStatus.OK);
        }
        return new ResponseEntity<>(advice.getAdvice(Type.OTHER, this.manejadorAVL.getErrors()), HttpStatus.BAD_REQUEST);//se retorna la instancia del árbol, para que así pueda ser convertida a un JSON...
    }
    
    
}
