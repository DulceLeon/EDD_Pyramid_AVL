/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Manejadores;

import Backend.EDD.AVLTree;
import Backend.EDD.ListaEnlazada;
import Backend.Objetos.Carta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author phily
 */
public class ManejadorAVL {          
    //para app el modelo singleton
    private static ManejadorAVL manejadorAVL;
        
    private final AVLTree<Carta> avlTree;
    private final ManejadorDeErrores manejadorErrores;
    private final JSONParser jsonParser;    

    private ManejadorAVL(){        
        this.avlTree = new AVLTree<>();
        this.manejadorErrores = new ManejadorDeErrores();//aunque en realidad para este manejador no es necesario, tenerlo aquí puesto que no se req algo como un reseteo para usarlo...
        this.jsonParser = new JSONParser();        
        
        this.avlTree.setErrorHandler(manejadorErrores);
    }
    
    public static ManejadorAVL getAVLHandler() {
        if (manejadorAVL == null) {
            manejadorAVL = new ManejadorAVL();
        }
        return manejadorAVL;
    }        
    
    private BufferedReader getContent(String elJSON){
        elJSON = elJSON.replace("{", "");
        elJSON = elJSON.replace("}", "");
        
        return (new BufferedReader(new StringReader(elJSON)));        
    }
    
    public boolean createTree(String JSON){   
        this.manejadorErrores.resetError();
        
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
            System.out.println("La sintaxis de la línea #"+numeroLinea+" es incorrecta \n"+ex.getMessage());
            manejadorErrores.addError("La sintaxis de la línea #"+numeroLinea+" es incorrecta");
        }
        
        return ((this.manejadorErrores.getError().isBlank()));
    }//dependiendo del valor booleano que se despligue, el servlet deidirá entre retornar el string del error o mostrar el string de éxito xD o en el caso dw la graficación, la representación del elemento...  
    
    public boolean insertCard(String JSON){
        this.manejadorErrores.resetError();
        
        BufferedReader buffer = this.getContent(JSON);
        try{        
            this.avlTree.insert(this.createCard(buffer.toString()));
        } catch (IOException ex) {
            System.out.println("Una o más líneas con sintaxis incorrecta\n"+ex.getMessage());
            manejadorErrores.addError("Una o más líneas con sintaxis incorrecta");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Una petición de inserción no puede contener +1 carta\n"+e.getMessage());
            manejadorErrores.addError("Una petición de inserción no puede contener +1 carta");
        }
        
        return (this.manejadorErrores.getError().isBlank());
    }
    
    public boolean deleteElement(String JSON){
        this.manejadorErrores.resetError();
        
        BufferedReader buffer = this.getContent(JSON);       
        Carta[] cartas = new Carta[2];
        int numeroLinea = 0;
                
        try {
            String linea;
            
            while((linea = buffer.readLine()) != null){
                if(!linea.isEmpty() || !linea.isBlank()){//por si xD, aunque no sería debido a las eli de los "{" "}" hechas, puesto que ya revisé y al eli esto, elimina la línea si es que no hay algo maś que las rodee
                    cartas[numeroLinea] = this.createCard(linea);
                }                
                numeroLinea++;
            }
        } catch (IOException ex) {
            System.out.println("La sintaxis de la línea #"+numeroLinea+" es incorrecta \n"+ex.getMessage());
            manejadorErrores.addError("La sintaxis de la línea #"+numeroLinea+" es incorrecta");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Una petición de eliminación no puede tener más de 2 elementos \n"+e.getMessage());
            manejadorErrores.addError("Una petición de eliminación no puede tener más de 2 elementos");
        }
        
        if((this.manejadorErrores.getError().isBlank())){
            if(checkDelete(cartas)){
                avlTree.delete(cartas[0]);
            
                if(cartas[1] != null){
                    avlTree.delete(cartas[1]);
                }            
            }
        }
        
        return ((this.manejadorErrores.getError().isBlank()));
    }
    
    private boolean checkDelete(Carta[] cartas){
        if(cartas[1]!= null){
            if((cartas[0].getValor() + cartas[1].getValor()) == 13){
               return true;
            }else{
                manejadorErrores.addError("Los valores de las cartas a eliminar deben sumar 13");
                return false;
            }
        }
        
        if((cartas[0].getValor()) == 13){            
            return true;
        }
        
        manejadorErrores.addError("Imposible eliminar la carta, ésta no posee un valor de 13");
        return false;
    }
    
    public boolean getAVLImage(){
        this.manejadorErrores.resetError();
        
        return true;
    }//Aquí se mandará a invocar el método para hacer la graficación... y a partir del resultado que dé, se procederá a hacer la apertura, conversión, o lo que se deba hacer para que se muestre la img en pantalla
    
    public String getLevel(String nivel){
        this.manejadorErrores.resetError();
        
        String elementsOfLevel = "";
        
        try{
            int level = Integer.parseInt(nivel);            
            elementsOfLevel = this.jsonParser.toJSON((ListaEnlazada<Carta>) this.avlTree.getElementsOfLevel(level));
            
        }catch(NumberFormatException e){
            System.out.println("Imposible convertir el parámetro a un número \nmsje: "+e.getMessage());
            manejadorErrores.addError("Imposible convertir el parámetro a un número");
        }
        
        return ((this.manejadorErrores.getError().isBlank())?elementsOfLevel:null);
    }
    
    public String getSort(String type){
        this.manejadorErrores.resetError();
        
        String sortElements = "";       
                      
        switch(type){
            case "preOrder":
                sortElements = this.jsonParser.toJSON((ListaEnlazada<Carta>) this.avlTree.setPreorder());
                break;
                
            case "inOrder":
                sortElements = this.jsonParser.toJSON((ListaEnlazada<Carta>) this.avlTree.setInorder());
                break;
                
            case "postOrder":
                sortElements = this.jsonParser.toJSON((ListaEnlazada<Carta>) this.avlTree.setPostorder());
                break;
            default:
                System.out.println("No se ha especificado un recorrido válido");
                manejadorErrores.addError("No se ha especificado un recorrido válido");    
        }
        
        return ((this.manejadorErrores.getError().isBlank())?sortElements:null);
    }//se tiene un valor default, para la queryString, entonces nunca se exe un caso default, pero quizá sería mejor quitarlo, para informar que hizo algo malo...
    
    private Carta createCard(String elemento)throws IOException, ArrayIndexOutOfBoundsException{//debemos que excepción es específicamente la que lanza cuando reciba un dato que no permite crear el obj...
        String nombreCarta = elemento.split(",")[1];//puesto que solo necesito el segundo elemento... ya que cuando se invoque este método se hará sabiendo lo que se quiere hacer con el onjeto que éste método devolverá...
        
        return new Carta(getValue(nombreCarta), nombreCarta);//creo que el ID no será necesario por lo que dijo la aux...
    }
    
    private int getValue(String nombreCarta){
        return (nombreCarta.contains("As")?1:(nombreCarta.contains("2")?2
           :(nombreCarta.contains("3")?3:(nombreCarta.contains("4")?4
           :(nombreCarta.contains("5")?5:(nombreCarta.contains("6")?6
           :(nombreCarta.contains("7")?7:(nombreCarta.contains("8")?8
           :(nombreCarta.contains("9")?9:(nombreCarta.contains("10")?10
           :(nombreCarta.contains("J")?11:(nombreCarta.contains("Q")?12:13))))))))))));    
    }
    
    public String getErrors(){
        return this.manejadorErrores.getError();
    }
    
    public AVLTree<Carta> getAVL(){
        return this.avlTree;
    }
}
