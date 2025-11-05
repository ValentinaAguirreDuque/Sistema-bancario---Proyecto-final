/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tinit
 */
public class CConexion {
   String usuario="valentina";
   String contrasena="#3Programacion3";
   String url="jdbc:mysql://72.167.84.254/banco";
   
   public CConexion(){
       
   }
   
   public Connection conectar(){
       Connection con=null;
       try{         
          con= DriverManager.getConnection(url,usuario,contrasena);
          System.out.println("conexion correcta");
          return con;
       }catch (Exception e){
          return null;
       }
   }
   
   public void desconectar(Connection con){
       try{         
          con.close();
          System.out.println("se cerró la conexión");
       }catch (Exception e){
          System.out.println(e.toString());
       }
       
   }
}
