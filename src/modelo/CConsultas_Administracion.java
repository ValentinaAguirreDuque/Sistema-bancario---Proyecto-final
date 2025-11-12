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
        try {
            query = "SELECT estado FROM cajeros WHERE id=" + idCajero + ";";
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();
            if (resultado.next()) {
                if (resultado.getInt("estado") != 1) {
                    query = "UPDATE cajeros SET estado=1 WHERE id=" + idCajero + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    query = "DELETE FROM cajeros WHERE id=" + idCajero + ";";
                    PreparedStatement preparar2 = con.prepareStatement(query);
                    preparar2.executeUpdate();
                    return true;
                } else {
                    System.out.println("El cajero está ocupado");
                    return false;
                }
            } else {
                System.out.println("El cajero no existe");
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
        try {
            query = "SELECT estado FROM clientes WHERE id=" + id + ";";
            PreparedStatement preparar = con.prepareStatement(query);
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                if (resultado.getInt("estado") != 1) {
                    query = "UPDATE clientes SET estado=1 WHERE id=" + id + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    query = "DELETE FROM clientes WHERE id=" + id + ";";
                    PreparedStatement preparar2 = con.prepareStatement(query);
                    preparar2.executeUpdate();
                    return true;
                } else {
                    System.out.println("El cliente está ocupado");
                    return false;
                }
            } else {
                System.out.println("EL cliente no existe");
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
        try {

            query = "SELECT estado FROM cajeros WHERE id=" + idCajero + ";";
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                if (resultado.getInt("estado") != 1) {
                    query = "UPDATE cajeros SET estado=1 WHERE id=" + idCajero + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    query = "UPDATE cajeros SET ndiez= '" + ndiez + "', nveinte= '" + nveinte + "', ncincuenta= '" + ncincuenta + "', ncien= '" + ncien + "', estado=0 WHERE id='" + idCajero + "';";
                    PreparedStatement preparar2 = con.prepareStatement(query);
                    preparar2.executeUpdate();
                    return true;
                } else {
                    System.out.println("EL cajero está ocupado.");
                    return false;
                }
            } else {
                System.out.println("El cajero no existe.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

//------------------------------------------------------------------------------
    public boolean editarClientes(Connection con, int id, String nombre, String apellido, String telefono, String ciudad, int ncuenta, double saldo) {
        this.con = con;
        try {
            query = "SELECT estado FROM clientes WHERE id=" + id + ";";
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                if (resultado.getInt("estado") != 1) {
                    query = "UPDATE clientes SET estado=1 WHERE id=" + id + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    query = "UPDATE clientes SET nombre= '" + nombre + "' ,apellido= '" + apellido + "', telefono= '" + telefono + "', ciudad= '" + ciudad + "', ncuenta= '" + ncuenta + "', saldo= '" + saldo + "', estado=0 WHERE id='" + id + "';";
                    PreparedStatement preparar2 = con.prepareStatement(query);
                    preparar2.executeUpdate();
                    return true;
                } else {
                    System.out.println("El cliente está ocupado.");
                    return false;
                }
            } else {
                System.out.println("El cliente no existe.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
//------------------------------------------------------------------------------    

    public CCliente buscarClienteID(Connection con, int id) {
        this.con = con;
        CCliente cliente = null;
        query = "SELECT * FROM clientes WHERE id='" + id + "'";

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
//------------------------------------------------------------------------------

    public ArrayList<CCliente> buscarClienteNombre(Connection con, String nombre) {
        this.con = con;
        ArrayList<CCliente> cliente = new ArrayList<>();
        query = "SELECT * FROM clientes WHERE nombre LIKE '%" + nombre + "%'"; // Se hizo el cambio para que busque similares o solo por inicial

        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
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
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }
//------------------------------------------------------------------------------

    public boolean consignar(Connection con, int id, double valor) {
        this.con = con;

        try {
            query = "SELECT estado FROM clientes WHERE id=" + id + ";";
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                if (resultado.getInt("estado") != 1) {
                    query = "UPDATE clientes SET estado=1 WHERE id=" + id + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    CCliente obj = this.buscarClienteID(con, id);
                    obj.setSaldo(obj.getSaldo() + valor);
                    query = "UPDATE clientes SET saldo = '" + obj.getSaldo() + "', estado=0 WHERE id='" + id + "';";
                    PreparedStatement preparar2 = con.prepareStatement(query);
                    preparar2.executeUpdate();
                    return true;
                } else {
                    System.out.println("El cliente está ocupado");
                    return false;
                }
            } else {
                System.out.println("El cliente no existe ");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
//------------------------------------------------------------------------------    

    public boolean retirar(Connection con, int id, double valor) {
        this.con = con;

        try {
            query = "SELECT estado FROM clientes WHERE id=" + id + ";";
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                if (resultado.getInt("estado") != 1) {
                    query = "UPDATE clientes SET estado=1 WHERE id=" + id + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    CCliente obj = this.buscarClienteID(con, id);
                    if (obj.getSaldo() >= valor) {
                        obj.setSaldo(obj.getSaldo() - valor);
                        query = "UPDATE clientes SET saldo = '" + obj.getSaldo() + "', estado=0 WHERE id='" + id + "';";
                        PreparedStatement preparar2 = con.prepareStatement(query);
                        preparar2.executeUpdate();
                        return true;
                    } else {
                        System.out.println("El valor no puede ser restado por fondos insuficientes");
                        query = "UPDATE clientes SET estado=0 WHERE id='" + id + "';";
                        PreparedStatement preparar3 = con.prepareStatement(query);
                        preparar3.executeUpdate();
                        return false;
                    }
                } else {
                    System.out.println("El cliente esta ocupado.");
                    return false;
                }
            } else {
                System.out.println("El cliente no existe.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    //------------------------------------------------------------------------------

    public CCajero buscarCajeroID(Connection con, int idCajero) {
        this.con = con;
        CCajero cajero = null;
        query = "SELECT * FROM cajeros WHERE id='" + idCajero + "'";

        try {
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                cajero = new CCajero(
                        resultado.getInt("id"),
                        resultado.getInt("ndiez"),
                        resultado.getInt("nveinte"),
                        resultado.getInt("ncincuenta"),
                        resultado.getInt("ncien"),
                        resultado.getInt("estado")
                );
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cajero;
    }

//------------------------------------------------------------------------------    
    public boolean agregarBilletesCajero(Connection con, int idCajero, int valorDiez, int valorVeinte, int valorCincuenta, int valorCien) {
        this.con = con;
        try {
            query = "SELECT estado FROM clientes WHERE id=" + idCajero + ";";
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();

            if (resultado.next()) {
                if (resultado.getInt("estado") != 0) {
                    query = "UPDATE cajeros SET estado=1 WHERE id=" + idCajero + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    CCajero obj = this.buscarCajeroID(con, idCajero);
                    obj.setNdiez(obj.getNdiez() + valorDiez);
                    obj.setNveinte(obj.getNveinte() + valorVeinte);
                    obj.setNcincuenta(obj.getNcincuenta() + valorCincuenta);
                    obj.setNcien(obj.getNcien() + valorCien);
                    query = "UPDATE cajeros SET ndiez = '" + obj.getNdiez() + "', nveinte = '" + obj.getNveinte() + "', ncincuenta = '" + obj.getNcincuenta() + "', ncien = '" + obj.getNcien() + "', estado=0 WHERE id='" + idCajero + "';";
                    PreparedStatement preparar2 = con.prepareStatement(query);
                    preparar2.executeUpdate();
                    return true;
                } else {
                    System.out.println("El cajero está ocupado.");
                    return false;
                }
            } else {
                System.out.println("EL cajero no existe.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
//------------------------------------------------------------------------------

    public boolean quitarBilletesCajero(Connection con, int idCajero, int valorDiez, int valorVeinte, int valorCincuenta, int valorCien) {
        this.con = con;
        try {
            query = "SELECT estado FROM clientes WHERE id=" + idCajero + ";";
            //preparo la consulta
            PreparedStatement preparar = con.prepareStatement(query);
            //ejecuto la consulta luego de prepararla
            ResultSet resultado = preparar.executeQuery();
            if (resultado.next()) {
                if (resultado.getInt("estado") != 0) {
                    query = "UPDATE cajeros SET estado=1 WHERE id=" + idCajero + ";";
                    PreparedStatement preparar1 = con.prepareStatement(query);
                    preparar1.executeUpdate();

                    CCajero obj = this.buscarCajeroID(con, idCajero);
                    if (obj.getNdiez() >= valorDiez || obj.getNveinte() >= valorVeinte || obj.getNcincuenta() >= valorCincuenta || obj.getNcien() >= valorCien) {
                        obj.setNdiez(obj.getNdiez() - valorDiez);
                        obj.setNveinte(obj.getNveinte() - valorVeinte);
                        obj.setNcincuenta(obj.getNcincuenta() - valorCincuenta);
                        obj.setNcien(obj.getNcien() - valorCien);
                        query = "UPDATE cajeros SET ndiez = '" + obj.getNdiez() + "', nveinte = '" + obj.getNveinte() + "', ncincuenta = '" + obj.getNcincuenta() + "', ncien = '" + obj.getNcien() + "' WHERE id='" + idCajero + "';";
                        PreparedStatement preparar2 = con.prepareStatement(query);
                        preparar2.executeUpdate();
                        return true;
                    } else {
                        System.out.println("El valor no puede ser restado por fondos insuficientes");
                        query = "UPDATE cajeros SET estado=0 WHERE id='" + idCajero + "';";
                        PreparedStatement preparar3 = con.prepareStatement(query);
                        preparar3.executeUpdate();
                        return false;
                    }
                }else{
                    System.out.println("El cajero está ocupado.");
                    return false;
                }
            }else{
                System.out.println("El cajero no existe.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }
}// fin
