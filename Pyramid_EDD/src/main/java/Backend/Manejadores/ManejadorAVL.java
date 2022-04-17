/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Manejadores;

import Backend.EDD.AVLTree;
import Backend.Objetos.Carta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author phily
 */
public class ManejadorAVL {          
    private final AVLTree<Carta> avlTree;
    private final ManejadorDeErrores manejadorErrores;

    public ManejadorAVL(){
        this.avlTree = new AVLTree<>();
        this.manejadorErrores = new ManejadorDeErrores();//aunque en realidad para este manejador no es necesario, tenerlo aquí puesto que no se req algo como un reseteo para usarlo...
    }
    
    private BufferedReader getContent(String elJSON){
        elJSON = elJSON.replace("{", "");
        elJSON = elJSON.replace("}", "");
        
        return (new BufferedReader(new StringReader(elJSON)));        
    }
    
    public void createTree(String JSON){      
        BufferedReader buffer = this.getContent(JSON);
        int numeroLinea = 0;
        
        try {
            String linea;
            
            while((linea = buffer.readLine()) != null){
                if(!linea.isEmpty() || !linea.isBlank()){//por si xD, aunque no sería debido a las eli de los "{" "}" hechas, puesto que ya revisé y al eli esto, elimina la línea si es que no hay algo maś que las rodee
                    avlTree.insert(this.createCard(linea));
                }
                
                numeroLinea++;
            }
        } catch (IOException ex) {
            System.out.println("Error al intentar leer la lista del JSON");
            manejadorErrores.addError("la línea #"+numeroLinea+"contiene");
        }
    }   
    
    public void insertCard(String JSON){
        BufferedReader buffer = this.getContent(JSON);
        
        this.avlTree.insert(this.createCard(buffer.toString()));
    }
    
    public void deleteElement(String JSON){
        
        
    }
    
    
    
    public Carta createCard(String elemento){
        String nombreCarta = elemento.split(",")[1];//puesto que solo necesito el segundo elemento... ya que cuando se invoque este método se hará sabiendo lo que se quiere hacer con el onjeto que éste método devolverá...
        
        return new Carta(0, getValue(nombreCarta), nombreCarta);//creo que el ID no será necesario por lo que dijo la aux...
    }
    
    private int getValue(String nombreCarta){
        return (nombreCarta.contains(nombreCarta)?1:0);    
    }
    
    public String getErrors(){
        return this.manejadorErrores.getError();
    }
    
    public AVLTree<Carta> getAVL(){
        return this.avlTree;
    }
}
