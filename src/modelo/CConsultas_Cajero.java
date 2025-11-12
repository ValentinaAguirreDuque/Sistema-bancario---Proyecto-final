/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author tinit
 */
public class CConsultas_Cajero {

    Connection con;
    String query;
    String resultadoCajero = "Los billetes retirados fueron: \n";
    HashMap<Integer, Integer> cajero = new HashMap<>();

    public CConsultas_Cajero() {
    }

    public int retirarDinero(Connection con, int idCliente, int ncuenta, int idCajero, int dinero) {
        this.con = con;

        try {
//VERIFICAR EL ESTADO Y EL SALDO DEL CLIENTE------------------------------------            
            query = "SELECT saldo, estado FROM clientes WHERE id= " + idCliente + " AND ncuenta= " + ncuenta + ";";
            PreparedStatement preparar = con.prepareStatement(query);
            ResultSet resultado = preparar.executeQuery();

            if (!resultado.next()) {
                System.out.println("El cliente no existe.");
                return 1;
            }
            if (resultado.getInt("estado") == 1) {
                System.out.println("El cliente está ocupado.");
                return 2;
            }
            double saldo = resultado.getDouble("saldo");
            if (saldo < dinero) {
                System.out.println("Saldo insuficiente.");
                return 3;
            }

//VERIFICAR EL ESTADO DEL CAJERO------------------------------------------------            
            query = "SELECT ndiez, nveinte, ncincuenta, ncien, estado FROM cajeros WHERE id= " + idCajero + ";";
            PreparedStatement preparar1 = con.prepareStatement(query);
            ResultSet resultado1 = preparar1.executeQuery();

            if (!resultado1.next()) {
                System.out.println("El cajero no existe.");
                return 4;
            }
            if (resultado1.getInt("estado") == 1) {
                System.out.println("El cajero está ocupado.");
                return 5;
            }

//CAMBIAR EL ESTADO DEL CLIENTE-------------------------------------------------            
            query = "UPDATE clientes SET estado=1 WHERE ncuenta= " + ncuenta + " AND id= " + idCliente + ";";
            PreparedStatement preparar2 = con.prepareStatement(query);
            preparar2.executeUpdate();

//CAMBIAR EL ESTADO DEL CAJERO--------------------------------------------------
            query = "UPDATE cajeros SET estado=1 WHERE id=" + idCajero + ";";
            PreparedStatement preparar3 = con.prepareStatement(query);
            preparar3.executeUpdate();

//CÓDIGO PARA LLENAR EL HASHMAP-------------------------------------------------
            cajero.put(10000, resultado1.getInt("ndiez"));
            cajero.put(20000, resultado1.getInt("nveinte"));
            cajero.put(50000, resultado1.getInt("ncincuenta"));
            cajero.put(100000, resultado1.getInt("ncien"));

// CÓDIGO SI NO HAY UN MONTO INGRESADO------------------------------------------
            if (dinero == 0) {
                System.out.println("ERROR: No hay un monto ingresado. Ingrese un monto");

                query = "UPDATE clientes SET estado=0 WHERE ncuenta=" + ncuenta + " AND id=" + idCliente + ";";
                PreparedStatement preparar4 = con.prepareStatement(query);
                preparar4.executeUpdate();

                query = "UPDATE cajeros SET estado=0 WHERE id=" + idCajero + ";";
                PreparedStatement preparar5 = con.prepareStatement(query);
                preparar5.executeUpdate();
                return 6;
            }

// VALIDACIONES-----------------------------------------------------------------
            if (dinero < 10000 || dinero > 1000000) {

                System.out.println("ERROR: Monto fuera de rango [10.000 Y 1´000.000]");

                query = "UPDATE clientes SET estado=0 WHERE ncuenta=" + ncuenta + " AND id=" + idCliente + ";";
                PreparedStatement preparar6 = con.prepareStatement(query);
                preparar6.executeUpdate();

                query = "UPDATE cajeros SET estado=0 WHERE id=" + idCajero + ";";
                PreparedStatement preparar7 = con.prepareStatement(query);
                preparar7.executeUpdate();
                return 7;
            }

            if (dinero % 10000 != 0) {
                System.out.println("Error: Ingrese un monto multiplo de 10");

                query = "UPDATE clientes SET estado=0 WHERE ncuenta=" + ncuenta + " AND id=" + idCliente + ";";
                PreparedStatement preparar8 = con.prepareStatement(query);
                preparar8.executeUpdate();

                query = "UPDATE cajeros SET estado=0 WHERE id=" + idCajero + ";";
                PreparedStatement preparar9 = con.prepareStatement(query);
                preparar9.executeUpdate();
                return 8;
            }

//-------------CÓDIGO PARA LA LÓGICA DE DISPENSACIÓN DE BILLETES----------------
            boolean retiroExitoso = true;
//SI EL DINERO A RETIRAR SON 10.000---------------------------------------------            
            if (dinero == 10000) {
                if (cajero.get(10000) >= 1) {
                    cajero.replace(10000, cajero.get(10000) - 1);
                    resultadoCajero += "Billetes de 10.000: 1\n";
                } else {
                    retiroExitoso = false;
                }

//SI EL DINERO A RETIRAR SON 20.000---------------------------------------------                
            } else if (dinero == 20000) {
                if (cajero.get(20000) >= 1) {
                    cajero.replace(20000, cajero.get(20000) - 1);
                    resultadoCajero += "Billetes de 20.000: 1\n";
                } else {
                    retiroExitoso = false;
                }

//SI EL DINERO A RETIRAR SON 50.000---------------------------------------------
            } else if (dinero == 50000) {
                if (cajero.get(20000) >= 2 && cajero.get(10000) >= 1) {
                    cajero.replace(20000, cajero.get(20000) - 2);
                    cajero.replace(10000, cajero.get(10000) - 1);
                    resultadoCajero += "Billetes de 20.000: 2 \nBilletes de 10.000: 1 \n";
                } else {
                    retiroExitoso = false;
                }

//SI EL DINERO A RETIRAR SON 100.000--------------------------------------------
            } else if (dinero == 100000) {
                if (cajero.get(50000) >= 1 && cajero.get(20000) >= 2 && cajero.get(10000) >= 1) {
                    cajero.replace(50000, cajero.get(50000) - 1);
                    cajero.replace(20000, cajero.get(20000) - 2);
                    cajero.replace(10000, cajero.get(10000) - 1);
                    resultadoCajero += "Billetes de 50.000: 1 \nBilletes de 20.000: 2 \nBilletes de 10.000: 1 \n";
                } else {
                    retiroExitoso = false;
                }

//SI EL DINERO A RETIRAR ES OTRO VALOR------------------------------------------
            } else {
                int restante = dinero;
                if (dinero > 100000) {
                    if (cajero.get(50000) >= 1 && cajero.get(20000) >= 2 && cajero.get(10000) >= 1) {
                        cajero.replace(50000, cajero.get(50000) - 1);
                        cajero.replace(20000, cajero.get(20000) - 2);
                        cajero.replace(10000, cajero.get(10000) - 1);
                        resultadoCajero += "Billetes de 50.000: 1 \nBilletes de 20.000: 2 \nBilletes de 10.000: 1 \n";
                        restante -= 100000;
                    }
                }
                int[] billetes = {100000, 50000, 20000, 10000};
                int billetesDisponibles;
                int billetesNecesarios;
                int cifra;

                for (int valor : billetes) {
                    billetesDisponibles = cajero.get(valor);
                    billetesNecesarios = restante / valor;

                    if (billetesNecesarios > billetesDisponibles) {
                        cifra = billetesDisponibles;
                    } else {
                        cifra = billetesNecesarios;
                    }
                    if (cifra > 0) {
                        cajero.replace(valor, cajero.get(valor) - cifra);
                        resultadoCajero += "Billetes de " + valor + ": " + cifra + "\n";
                        restante -= valor * cifra;
                    }
                    if (restante != 0) {
                        retiroExitoso = false;
                    }
                }
                retiroExitoso = false;
            }
//SI NO HAY BILLETES SUFICIENTS PARA RETIRAR------------------------------------
            if (!retiroExitoso) {
                System.out.println("No hay billetes suficientes para retirar.");
                query = "UPDATE clientes SET estado=0 WHERE ncuenta=" + ncuenta + " AND id=" + idCliente + ";";
                PreparedStatement preparar10 = con.prepareStatement(query);
                preparar10.executeUpdate();

                query = "UPDATE cajeros SET estado=0 WHERE id=" + idCajero + ";";
                PreparedStatement preparar11 = con.prepareStatement(query);
                preparar11.executeUpdate();
                return 9;
            }

//CÓDIGO PARA CAMBIAR EL SALDO EN CLIENTE Y EN CAJEROS--------------------------
            saldo -= dinero;

            query = "UPDATE clientes SET saldo=" + saldo + ", estado=0 WHERE ncuenta=" + ncuenta + " AND id=" + idCliente + ";";
            PreparedStatement preparar12 = con.prepareStatement(query);
            preparar12.executeUpdate();

            int ndiez = cajero.get(10000);
            int nveinte = cajero.get(20000);
            int ncincuenta = cajero.get(50000);
            int ncien = cajero.get(100000);

            query = "UPDATE cajeros SET ndiez=" + ndiez + ", nveinte=" + nveinte + ", ncincuenta=" + ncincuenta + ", ncien=" + ncien + ", estado=0 WHERE id=" + idCajero + ";";
            PreparedStatement preparar13 = con.prepareStatement(query);
            preparar13.executeUpdate();

            System.out.println(resultadoCajero);
            return 10;
            
//CATCH-------------------------------------------------------------------------
        } catch (SQLException e) {
            System.out.println("Error en el SQL.");
            System.out.println(query);
            return 0;
        }
    }

//PARA RECIBIR EL RESULTADO Y MANDARLO A CONTROLADOR----------------------------    
    public String getResultadoCajero() {
        return resultadoCajero;
    }
    
//PARA RECIBIR EL HASHMAP Y MANDARLO A CONTROLADOR----------------------------
    public HashMap<Integer, Integer> getCajero() {
        return cajero;
    }

} //  Fin consultas cajero
