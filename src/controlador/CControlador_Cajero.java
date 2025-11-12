/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.util.HashMap;
import modelo.*;

/**
 *
 * @author tinit
 */
public class CControlador_Cajero {

    CConexion con = new CConexion();
    Connection conecta;
    CConsultas_Cajero c = new CConsultas_Cajero();

    public CControlador_Cajero() {
    }

    public int retirarDinero(int id, int ncuenta, int idCajero, int dinero) {
        conecta = con.conectar();
        int retirar = c.retirarDinero(conecta, id, ncuenta, idCajero, dinero);
        con.desconectar(conecta);
        return retirar;
    }

    public String getResultadoCajero() {
        return c.getResultadoCajero();
    }

    public HashMap<Integer, Integer> getCajero() {
        return c.getCajero();
    }

}
