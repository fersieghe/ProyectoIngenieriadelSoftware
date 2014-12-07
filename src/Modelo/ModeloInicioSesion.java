/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;

/**
 *
 * @author Abel Márquez
 */
public class ModeloInicioSesion {
    
    
    public static String inicioSesion (String nombre, String apellido, String cedula, String username, String email, String direccion, String password) {
        
        File usuarios = new File("Usuario.txt");
        
        boolean existe = ModeloRegistro.usuarioEnSistema(usuarios, cedula);
        
        
        if (existe) {  //Existe se refiere a si existe el usuario en el sistema
        //Si encuentra el usuario y todo sale bien
        Usuario user = new Usuario();
         user.setNombre(nombre);
         user.setApellido(apellido);
         user.setCedula(cedula);
         user.setUsername(username);
         user.setEmail(email);
         user.setDireccion(direccion);
         user.setPassword(password);
         
         return ("Inicio de Sesion Exitoso"); 
         
        }
        
        return ("Error en el sistema");
    }
    
}
