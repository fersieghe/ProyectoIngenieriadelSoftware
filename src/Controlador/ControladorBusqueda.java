/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloBusqueda;

/**
 *
 * @author fersieghe
 */
public class ControladorBusqueda {
    
    public static String busquedaPorNombreyCategoria (String nombre, String categoria) {
        
        return (ModeloBusqueda.busquedaDeProductos(nombre, categoria));
        
    }
    
}
