
package Modelo;

import java.io.*;

/**
 * Esta clase maneja todo lo referente al archivo de texto donde se almacenan los datos y usuarios de la aplicacion
 * 
 * @author Estefania Baldini
 * @author Abel Marquez
 *
 */

public class ModeloTXT {
    
    private static final String direccionTXT = "Usuario.txt";
		
	/**
	 *  Este metodo se encarga de guardar en el archivo, un usuario nuevo
	 * @param Ffichero - El archivo donde se almacenan todos los usuarios y sus datos correspondientes
	 * @param SCadena - Los datos del usuario a registrar
	 */
	
	
public static void guardarEnTXT (File Ffichero,String SCadena) {
		
        try {  
            //Si no Existe el fichero lo crea  
             if(!Ffichero.exists()){  
                 Ffichero.createNewFile();  
             }  
            /*Abre un Flujo de escritura,sobre el fichero con codificacion utf-8.  
             *AdemÃ¡s  en el pedazo de sentencia "FileOutputStream(Ffichero,true)", 
             *true es por si existe el fichero seguir aÃ±adiendo texto y no borrar lo que tenia*/  
            BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero,true), "utf-8"));  
            /*Escribe en el fichero la cadena que recibe la funciÃ³n.  
             *el string "\r\n" significa salto de linea*/  
            Fescribe.write(SCadena + "\r\n");  
            //Cierra el flujo de escritura  
            Fescribe.close();  
         } catch (Exception ex) {  
            //Captura un posible error le imprime en pantalla   
            System.out.println(ex.getMessage());  
         }   
	}
	
/**
	 * Este metodo funciona para buscar una palabra especÃ­fica en el archivo donde estan los datos de los usuarios
	 * @param texto -
	 * @param palabraBuscada - La palabra a buscar en el archivo
	 * @return - Si encontro o no la palabra en forma de boolean
	 */
	
public static boolean BuscarPalabra(String texto, String palabraBuscada)
    {
        int posicionInicial = 0;
        int longitud = 0;
        char[] cadenaPorCaracter=texto.toCharArray();

        for (int i = 0; i < texto.length(); i++)
        {
            longitud++;
            if (cadenaPorCaracter[i] == ' ' || i == texto.length() - 1)
            {
                if ( texto.substring(posicionInicial, longitud).trim().toUpperCase().equals(palabraBuscada.trim().toUpperCase()))
                {
                    return true;
                }
                posicionInicial = i;
            }
        }
		return false;
    }
	


	/**
	 * Esta funcion se encarga de buscar un correo en el archivo donde estan almacenados los usuarios con sus datos
	 * @param ffichero - El archivo donde se ubican los datos
	 * @param cedula - La cedula de la persona que se desea buscar
	 * @return - si se encuentra o no el usuario en forma de boolean
	 */


public static boolean buscarEnTXT (File ffichero, String cedula) {
		  boolean existe = false;
	        int nroLinea=0;
	
	        FileReader leerArchivo=null;
	            
	        try
	        {
	        	System.out.println("Buscando usuario en archivos...");
	            leerArchivo = new FileReader (ffichero);
	            BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
	            String textoLinea=null;
	            while((textoLinea = memoriaParaLectura.readLine())!=null)
	            {
	                boolean cantidadPalabra = BuscarPalabra(textoLinea, cedula);
	                System.out.println("Esta es la cantidad de palabras "+cantidadPalabra);
	                if (cantidadPalabra)
	                {
	                	  //System.out.println("Usuario encontrado");
	   	               return cantidadPalabra;
	                    
	                }
	                nroLinea++;
	            }
	            
	        }
	        catch (Exception ex)
	        {
	            //JOptionPane.showMessageDialog(null, ex.getMessage());
	        }
	        finally
	        {
	            try
	            {
	                if (null != leerArchivo)
	                {
	                    leerArchivo.close();
	                }
	            }
	            catch (Exception ex1)
	            {
	                //JOptionPane.showMessageDialog(null, ex1.getMessage());
	            }
	        }
			return false;  
	}
	
/**
 * Metodo que devuelve un dato especifico de un usuario
 * @param email - email del usuario
 * @param campo - campo a devolver
 * @param pass - variable de control
 * @return - El dato del usuario
 */

/*
public static String devolverDatoUsuario (String email, int campo, boolean pass) {
		
		//Debe buscar en el archivo TXT cual es la contrasena, desencriptarla y enviarla
		  String datosUsuario = "", datos[], contrasena;
		   boolean existe = false;
	        int nroLinea=0;
	
	        FileReader leerArchivo=null;
	            
	        	System.out.println("Buscando usuario en archivos...");
	            try {
					leerArchivo = new FileReader (direccionTXT);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
	            String textoLinea=null;
	            try {
					while((textoLinea = memoriaParaLectura.readLine())!=null)
					{
					    nroLinea++;
					    boolean cantidadPalabra = BuscarPalabra(textoLinea, email);
					  
					    if (cantidadPalabra)
					    {
					        datosUsuario = textoLinea;
					        System.out.println(datosUsuario);
					        System.out.println("Ahora a buscar el numero correlativo...");
					        
					    }
					    else {
					    	
					    	datosUsuario = "NOVALIDO";
					    }
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
		
		
		if (!datosUsuario.equals("NOVALIDO") && (pass) && (campo != 1)) {
				
			datos = datosUsuario.split(" ");
			contrasena = datos[campo];		//Campo deberia ser 3, por el orden en el txt de USUARIO					
			return (ModeloEncriptacion.decrypt(contrasena));
			
		}
		else if (!datosUsuario.equals("NOVALIDO") && (!pass)) {
		
			datos = datosUsuario.split(" ");
			contrasena = datos[campo] + " " + datos[campo++];		//Campo deberia ser 2 y 3, por el orden en el txt de DATOS					
			return (contrasena); //Contrasena aqui son los token juntos por un espacio
			
		}
		else if (!datosUsuario.equals("NOVALIDO") && (pass) && (campo == 1)) {
			
			datos = datosUsuario.split(" ");
			contrasena = datos[0];  //Ahi esta el numero correlativo SIEMPRE
			System.out.println("El numero correlativo actual es:" + contrasena);
			User.setNumCorrelativo(Integer.parseInt(contrasena));
			return (contrasena);		
		}
		return ("Error en el sistema...");
		
	}  

/*

/**
 * Metodo que modifica el archivo
 * @param FficheroAntiguo - Archivo de texto
 * @param Santigualinea - Linea sin modificar
 * @param Snuevalinea  - Linea modificada
 */
		
public static void ModificarFichero(File FficheroAntiguo, String Santigualinea , String Snuevalinea){  
		  try {   
                /*Abro un flujo de lectura*/  
			  
               BufferedReader Flee= new BufferedReader(new FileReader(FficheroAntiguo));  
			  
                 String Slinea;  
                /*Recorro el fichero de texto linea a linea*/  

                while((Slinea=Flee.readLine())!=null) {   
                    /*Si la linea obtenida es igual al la buscada 
                     *para modificar*/  
                    if (Slinea.equals(Santigualinea)) {  
                       /*Escribo la nueva linea en vez de la que tenia*/  
                    	guardarEnTXT(FficheroAntiguo,Snuevalinea);  
                        System.out.println(Snuevalinea);
                    }else{  
                        /*Escribo la linea antigua*/  
                    	System.out.println(Slinea);
                    	guardarEnTXT(FficheroAntiguo,Slinea);  
                    }      

                }  
                Flee.close();   
              
         } catch (Exception ex) {  
            /*Captura un posible error y le imprime en pantalla*/
        	
             System.out.println(ex.getMessage());  
        }  
    }  

public static String devolverProducto() {
		
		int resultado = 1, linea = 0;
		
		 FileReader leerArchivo = null;
		 File archivo = new File("Productos.txt");
         	               
	            try {
					leerArchivo = new FileReader (archivo);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
	            String textoLinea = "";
                            String lineafinal = "";
	            try {
                          while((textoLinea = memoriaParaLectura.readLine())!=null)
                          {
                                        System.out.println("Linea que deberia devolver:" + textoLinea);
                                        lineafinal = lineafinal + textoLinea + "/"; 
                           }
			return (lineafinal);		
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	               try {
					leerArchivo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   
	     return ("Error");
	
	}

	
public static String devolverLinea(String nombreArchivo) {
		
		int resultado = 1, linea = 0;
		
		 FileReader leerArchivo = null;
		 File archivo = new File(nombreArchivo);
         
	        	System.out.println("Buscando cedula de usuario...");
	            try {
					leerArchivo = new FileReader (archivo);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
	            String textoLinea = null;
	            try {
                          while((textoLinea = memoriaParaLectura.readLine())!=null)
                          {
                               String valores[] = textoLinea.split(" ");
                               System.out.println(valores[2]);
                               int num= Integer.parseInt(valores[2]);
                                      if (num == (Integer.parseInt(Usuario.getCedula()))) {
                                        System.out.println("Linea que deberia devolver(antigua):" + textoLinea);
                                        return (textoLinea);
                                      }
                                           
                                      linea++ ;

                           }
					
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	               try {
					leerArchivo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   
	     return ("Error");
	
	}

/*
public static String cargarCuentas () {
	  
	  String cuentas = "";
	  int linea = 0;
		
		 FileReader leerArchivo = null;
		 File archivo = new File("AccessUsuario.txt");
    
	        	System.out.println("Cargando cuentas de usuario...");
	            try {
					leerArchivo = new FileReader (archivo);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
	            String textoLinea = null;
	            try {
					while((textoLinea = memoriaParaLectura.readLine())!=null)
					{
								String valores[] = textoLinea.split(" ");
								
								if (Integer.parseInt(valores[0]) == (User.getNumCorrelativo())){
									
									System.out.println(valores[1]);
									cuentas = " " + valores[1];
								}
					
									linea++ ;
						
					}
					
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	               try {
					leerArchivo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     System.out.println("Cuentas de usuario encontradas:" + cuentas);
	     return cuentas;

}

*/

}

