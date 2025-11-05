/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author tinit
 */
public class CConsultas_Administracion {

    Connection con;
    String query;

    public CConsultas_Administracion() {
    }

//------------------------------------------------------------------------------    
    public ArrayList<CCajero> consultarCajero(Connection con) {
        this.con = con;
        query = "SELECT * FROM datos";
        ArrayList<CCajero> lista = new ArrayList<>();
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            while (resultado.next()) {
                CCajero c = new CCajero(
                        resultado.getInt("id"),
                        resultado.getInt("ndiez"),
                        resultado.getInt("nveinte"),
                        resultado.getInt("ncincuenta"),
                        resultado.getInt("ncien"),
                        resultado.getInt("estado")
                );
                lista.add(c);
            }
            System.out.println("Consulta correcta");
            return lista;

        } catch (SQLException ex) {
            System.out.println("Error en el sql");
            return null;
        }
    }
//------------------------------------------------------------------------------
    public ArrayList<CCliente> consultarClientes(Connection con) {
        this.con = con;
        query = "SELECT * FROM datos";
        ArrayList<CCliente> lista1 = new ArrayList<>();
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            while (resultado.next()) {
                CCliente c = new CCliente(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("telefono"),
                        resultado.getString("ciudad"),
                        resultado.getString("ncuenta"),
                        resultado.getDouble("saldo"),
                        resultado.getInt("estado")
                );
                lista1.add(c);
            }
            System.out.println("Consulta correcta");
            return lista1;

        } catch (SQLException ex) {
            System.out.println("Error en el sql");
            return null;
        }
    }
//------------------------------------------------------------------------------
    public boolean ingresarBilletes(Connection con, String nombres, String apellidos, String telefono, String direccion, String email) {
        this.con = con;
        query = "INSERT INTO datos(id,nombres,apellidos,telefono,direccion,email) VALUES (null,'" + nombres + "','" + apellidos + "','" + telefono + "','" + direccion + "','" + email + "');";
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            preparar.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en el sql");
            return false;
        }
    }

}// fin
