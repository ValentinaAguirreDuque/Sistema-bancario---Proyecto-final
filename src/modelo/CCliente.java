/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author tinit
 */
public class CCliente {
    int id;
    String nombre;
    String apellido;
    String telefono;
    String ciudad;
    String ncuenta;
    double saldo;
    int estado;

    public CCliente() {

    }

    public CCliente(int id, String nombre, String apellido, String telefono, String ciudad, String ncuenta, double saldo, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.ncuenta = ncuenta;
        this.saldo = saldo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNcuenta() {
        return ncuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getEstado() {
        return estado;
    }

    

    
    
}
