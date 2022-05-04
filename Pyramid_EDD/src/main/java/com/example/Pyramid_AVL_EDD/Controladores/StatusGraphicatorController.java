/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Controladores;

import com.example.Pyramid_AVL_EDD.Backend.Manejadores.ManejadorAVL;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phily
 */
@RestController
public class StatusGraphicatorController {
    private ManejadorAVL manejadorAVL = ManejadorAVL.getAVLHandler();    
    
    @ResponseBody//los otros getter no lo tienen y funcionan xD
    @GetMapping(value = "/Game/status-avltree",
     produces = MediaType.APPLICATION_JSON_VALUE)//si te da error ahí averiguas cómo setear direcciones... si se puede devolver un JSON, por los msjes que se deben desplegar, entonces no uses esto MediaType.IMAGE_JPEG_VALUE
    public ResponseEntity<String> graph(){        
        String JSON = null;           
            
        try {        
            JSON = this.manejadorAVL.getAVLImage();
            System.out.println("se ha obtenido el path");
        } catch (IOException ex) {
            this.manejadorAVL.getErrorHandler().addError("Surgió un error en el flujo de los datos");
            System.out.println("Surgió un error en el flujo de los datos" +ex.getMessage());
        }
        
        if(!this.manejadorAVL.getErrors().isBlank()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(JSON, HttpStatus.OK);
    }
    
    
}//Esta no req de paráms


//forma 1
            //InputStream in = ServletContext.getResourceAsStream("/images/no_image.jpg");
            //return IOUtils.toByteArray(in);
            
            //forma 2
            //File img = new File("src/main/resources/static/test.jpg");
            //return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.get().getContentType(img))).body(Files.readAllBytes(img.toPath()));
