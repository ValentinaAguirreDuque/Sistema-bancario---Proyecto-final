/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author tinit
 */
public class CCajero {
    
    int id;
    int ndiez;
    int nveinte;
    int ncincuenta;
    int ncien;
    int estado;

    public CCajero(int id, int ndiez, int nveinte, int ncincuenta, int ncien, int estado) {
        this.id = id;
        this.ndiez = ndiez;
        this.nveinte = nveinte;
        this.ncincuenta = ncincuenta;
        this.ncien = ncien;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getNdiez() {
        return ndiez;
    }

    public int getNveinte() {
        return nveinte;
    }

    public int getNcincuenta() {
        return ncincuenta;
    }

    public int getNcien() {
        return ncien;
    }

    public int getEstado() {
        return estado;
    }

    public void setNdiez(int ndiez) {
        this.ndiez = ndiez;
    }

    public void setNveinte(int nveinte) {
        this.nveinte = nveinte;
    }

    public void setNcincuenta(int ncincuenta) {
        this.ncincuenta = ncincuenta;
    }

    public void setNcien(int ncien) {
        this.ncien = ncien;
    }
    
    
    
    
    
    
    
}
