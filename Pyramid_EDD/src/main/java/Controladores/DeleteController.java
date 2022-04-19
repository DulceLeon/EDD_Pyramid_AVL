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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phily
 */

@RestController
public class DeleteController{
    private ManejadorAVL manejadorAVL = ManejadorAVL.getAVLHandler();
    private Advice advice = new Advice();
    
    @DeleteMapping("/Game/delete")//también lo hubieras podido separar en dos: @RequestMapping("/Game")[y para mejor orden colocarlo debajo de @RestController] y @DeleteMapping("/delete")
    public ResponseEntity<Advice> deleteNode(String JSON){//se debe recibir un listado JSON, puesto que no es 1 obj, entonces no podría utilizar el @RequestBody, para así mapear de una vez al objeto, al momento de recibir el dato...
        if(this.manejadorAVL.deleteElement(JSON)){
            return new ResponseEntity<>(advice.getAdvice(Type.OK, "Carta eliminada con éxito"), HttpStatus.OK);
        }else if(this.manejadorAVL.getErrors().contains("sintaxis") || !this.manejadorAVL.getErrors().contains("petición") ||
                (this.manejadorAVL.getErrors().contains("13") && (this.manejadorAVL.getErrors().contains("hijos")))){
            return new ResponseEntity<>(advice.getAdvice(Type.OTHER, "Surgió un error al momento de eliminar la carta\n\n"+this.manejadorAVL.getErrors()), HttpStatus.BAD_REQUEST);//mientras no tengas respuesta a si solo debemos enviar el código de error o no, aún no podrás enviar el string que contiene los errores...            
        }else if(this.manejadorAVL.getErrors().contains("13")){
            return new ResponseEntity<>(advice.getAdvice(Type.IMPOSSIBLE_REMOVE, this.manejadorAVL.getErrors()), HttpStatus.NOT_ACCEPTABLE);
        }
        
        return new ResponseEntity<>(advice.getAdvice(Type.NODE_WITH_CHILDREN, this.manejadorAVL.getErrors()), HttpStatus.CONFLICT);
    }
    
}
