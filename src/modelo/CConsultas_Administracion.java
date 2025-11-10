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
        query = "SELECT * FROM cajeros";
        ArrayList<CCajero> lista = new ArrayList<>();
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            while (resultado.next()) {
                CCajero ca = new CCajero(
                        resultado.getInt("id"),
                        resultado.getInt("ndiez"),
                        resultado.getInt("nveinte"),
                        resultado.getInt("ncincuenta"),
                        resultado.getInt("ncien"),
                        resultado.getInt("estado")
                );
                lista.add(ca);
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
        query = "SELECT * FROM clientes";
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
                        resultado.getInt("ncuenta"),
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

    public boolean agregarCajero(Connection con, int ndiez, int nveinte, int ncincuenta, int ncien) {
        this.con = con;
        query = "INSERT INTO cajeros(id,ndiez,nveinte,ncincuenta,ncien,estado) VALUES (null," + ndiez + "," + nveinte + "," + ncincuenta + "," + ncien + ",0);";
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
//------------------------------------------------------------------------------

    public boolean agregarClientes(Connection con, String nombre, String apellido, String telefono, String ciudad, int ncuenta, double saldo) {
        this.con = con;
        query = "INSERT INTO clientes(id,nombre,apellido,telefono,ciudad,ncuenta,saldo,estado) VALUES (null,'" + nombre + "' , '" + apellido + "' , '" + telefono + "' , '" + ciudad + "' , " + ncuenta + " , " + saldo + " , 0  );";
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
//------------------------------------------------------------------------------

    public boolean borrarCajero(Connection con, int idCajero) {
        this.con = con;
        query = "DELETE FROM cajeros WHERE id = '" + idCajero + "' ; ";
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla

            // Para verificar que si se haya realizado un cambio
            if (preparar.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Error en el sql");
            return false;
        }
    }
//------------------------------------------------------------------------------

    public boolean borrarCliente(Connection con, int id) {
        this.con = con;
        query = "DELETE FROM clientes WHERE id = '" + id + "' ; ";
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla

            // Para verificar que si se haya realizado un cambio
            if (preparar.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Error en el sql");
            return false;
        }
    }
//------------------------------------------------------------------------------

    public boolean editarCajeros(Connection con, int idCajero, int ndiez, int nveinte, int ncincuenta, int ncien) {
        this.con = con;
        query = "UPDATE cajeros SET ndiez= '" + ndiez + "', nveinte= '" + nveinte + "', ncincuenta= '" + ncincuenta + "', ncien= '" + ncien + "' WHERE id='" + idCajero + "';";
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            preparar.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

//------------------------------------------------------------------------------
    public boolean editarClientes(Connection con, int id, String nombre, String apellido, String telefono, String ciudad, int ncuenta, double saldo) {
        this.con = con;
        query = "UPDATE clientes SET nombre= '" + nombre + "' ,apellido= '" + apellido + "', telefono= '" + telefono + "', ciudad= '" + ciudad + "', ncuenta= '" + ncuenta + "', saldo= '" + saldo + "' WHERE id='" + id + "';";
        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            preparar.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
//------------------------------------------------------------------------------    

    public CCliente buscarClienteID(Connection con, int id) {
        this.con = con;
        CCliente cliente = null;
        query = "SELECT * FROM clientes where id='" + id + "'";

        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                cliente = new CCliente(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("telefono"),
                        resultado.getString("ciudad"),
                        resultado.getInt("ncuenta"),
                        resultado.getDouble("saldo"),
                        resultado.getInt("estado")
                );
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }
    
    
    
    
    
}// fin
