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
    public boolean agregarCajero (int ndiez, int nveinte, int ncincuenta, int ncien) {
        conecta = con.conectar();
        boolean bandera = consulta.agregarCajero(conecta, ndiez, nveinte, ncincuenta, ncien);
        con.desconectar(conecta);
        return bandera;
    }

    
    
    
    
    
    
    
    
    
} // Fin
