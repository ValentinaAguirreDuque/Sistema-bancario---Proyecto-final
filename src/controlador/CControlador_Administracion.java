/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.util.ArrayList;
import modelo.*;

/**
 *
 * @author tinit
 */
public class CControlador_Administracion {

    CConexion con = new CConexion();
    Connection conecta;
    CConsultas_Administracion consulta = new CConsultas_Administracion();

    public CControlador_Administracion() {
    }
//------------------------------------------------------------------------------

    public ArrayList<CCajero> consultarCajero() {
        ArrayList<CCajero> lista = new ArrayList<>();
        conecta = con.conectar();
        if (conecta != null) {
            lista = consulta.consultarCajero(conecta);
            con.desconectar(conecta);
            return lista;
        } else {
            return null;
        }
    }
//------------------------------------------------------------------------------

    public ArrayList<CCliente> consultarClientes() {
        ArrayList<CCliente> lista1 = new ArrayList<>();
        conecta = con.conectar();
        if (conecta != null) {
            lista1 = consulta.consultarClientes(conecta);
            con.desconectar(conecta);
            return lista1;
        } else {
            return null;
        }
    }
//------------------------------------------------------------------------------

    public boolean agregarCajero(int ndiez, int nveinte, int ncincuenta, int ncien) {
        conecta = con.conectar();
        boolean bandera = consulta.agregarCajero(conecta, ndiez, nveinte, ncincuenta, ncien);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------

    public boolean agregarClientes(String nombre, String apellido, String telefono, String ciudad, int ncuenta, double saldo) {
        conecta = con.conectar();
        boolean bandera = consulta.agregarClientes(conecta, nombre, apellido, telefono, ciudad, ncuenta, saldo);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------

    public boolean borrarCajero(int idCajero) {
        conecta = con.conectar();
        boolean bandera = consulta.borrarCajero(conecta, idCajero);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------ 

    public boolean borrarCliente(int id) {
        conecta = con.conectar();
        boolean bandera = consulta.borrarCliente(conecta, id);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------

    public boolean editarCajero(int idCajero, int ndiez, int nveinte, int ncincuenta, int ncien) {
        conecta = con.conectar();
        boolean bandera = consulta.editarCajeros(conecta, idCajero, ndiez, nveinte, ncincuenta, ncien);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------

    public boolean editarClientes(int id, String nombre, String apellido, String telefono, String ciudad, int ncuenta, double saldo) {
        conecta = con.conectar();
        boolean bandera = consulta.editarClientes(conecta, id, nombre, apellido, telefono, ciudad, ncuenta, saldo);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------    

    public CCliente buscarClienteID(int id) {
        CCliente cliente = null;
        conecta = con.conectar();
        if (conecta != null) {
            cliente = consulta.buscarClienteID(conecta, id);
            con.desconectar(conecta);
            return cliente;
        } else {
            return null;
        }
    }
//------------------------------------------------------------------------------    

    public CCliente buscarClienteNombre(String nombre) {
        CCliente cliente = null;
        conecta = con.conectar();
        if (conecta != null) {
            cliente = consulta.buscarClienteNombre(conecta, nombre);
            con.desconectar(conecta);
            return cliente;
        } else {
            return null;
        }
    }
//------------------------------------------------------------------------------    

    public boolean consignar(int id, double valor) {
        conecta = con.conectar();
        boolean bandera = consulta.consignar(conecta, id, valor);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------    

    public boolean retirar(int id, double valor) {
        conecta = con.conectar();
        boolean bandera = consulta.retirar(conecta, id, valor);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------

    public boolean agregarBilletesCajero(int idCajero, int valorDiez, int valorVeinte, int valorCincuenta, int valorCien) {
        conecta = con.conectar();
        boolean bandera = consulta.agregarBilletesCajero(conecta, idCajero, valorDiez, valorVeinte, valorCincuenta, valorCien);
        con.desconectar(conecta);
        return bandera;
    }
//------------------------------------------------------------------------------

    public boolean quitarBilletesCajero(int idCajero, int valorDiez, int valorVeinte, int valorCincuenta, int valorCien) {
        conecta = con.conectar();
        boolean bandera = consulta.quitarBilletesCajero(conecta, idCajero, valorDiez, valorVeinte, valorCincuenta, valorCien);
        con.desconectar(conecta);
        return bandera;
    }

} // Fin
