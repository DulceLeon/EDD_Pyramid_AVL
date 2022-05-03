/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pyramid_AVL_EDD.Backend.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author phily
 */
public class ImageGenerator extends Thread{
    private Runtime runtime;    
    private boolean seguirExe = true;
    private String GRAPH_NAME;
    private String DOT_PATH;
    private String IMAGE_PATH;
    
    @Override
    public void run(){
        this.generateImage();
    }
    
    private void generateImage(){
        this.seguirExe = true;
        
        try {
            while(seguirExe){            
                this.runtime = Runtime.getRuntime();
                
                runtime.exec( "dot -Tjpg "+this.GRAPH_NAME+" -o "+ this.IMAGE_PATH);
                this.seguirExe = !this.seguirExe;           
            }
        }catch (IOException ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot "+ ex.getMessage());
        }
        
    }
    
    public void saveImage(String source)throws  Exception{
        File temp;
      
        //temp = File.createTempFile(this.GRAPH_NAME, ".dot.tmp", new File(this.path));
        FileWriter fout = new FileWriter(this.DOT_PATH);
        fout.write(source);
        fout.close();
        
        FileWriter imageOut = new FileWriter(this.IMAGE_PATH);
        imageOut.write(source);
        imageOut.close();
        
        //File.createTempFile("AVL", ".jpg", new File(this.IMAGE_PATH));
    }
    
    public void setGraphInfo(String graphName, String dotPath, String imagePath){
        this.GRAPH_NAME = graphName;
        this.DOT_PATH = dotPath;
        this.IMAGE_PATH = imagePath;
    }
    
}
