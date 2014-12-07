/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;

/**
 * @author Estefania Baldini
 * @author Abel Márquez
 */
public class ModeloInventario {
    
    public static void agregarProductos(String nombre, int precio, String descripcion, String categoria, int cant) {
        
                ModeloTXT guardar= new ModeloTXT();
		
		File Ffichero=new File("Productos.txt");
		System.out.println(Ffichero);
    
                                        String cadenaRegistro;
                
                                                cadenaRegistro = nombre + ";" + precio + ";" + descripcion + ";" + categoria + ";" + cant + ";" + "Disponible";

                         guardar.guardarEnTXT(Ffichero, cadenaRegistro);
    }
    
     public static void actualizarProductos(String nombre, int precio, String descripcion, String categoria, int cant) {
        
             
    }
    
}
