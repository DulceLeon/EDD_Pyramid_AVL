/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Backend.Manejadores.ManejadorGraficacion;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author phily
 */
public class StatusGraphicatorController {
    private ManejadorGraficacion manejadorGraficacion;
    
    @GetMapping("/Game/status-avltree")//si te da error ahí averiguas cómo setear direcciones
    public void graph(){
        this.manejadorGraficacion = new ManejadorGraficacion();
        
        
        
    
    }//en este caso no debe retornar un objeto, puesto que lo que se hará es agregar la imagen a la vista...
    
    
}//Esta no req de paráms
