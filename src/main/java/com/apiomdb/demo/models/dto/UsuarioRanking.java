package com.apiomdb.demo.models.dto;

import com.apiomdb.demo.models.entity.Usuario;

public class UsuarioRanking {
    
    private Usuario usuario;
    private double promedioImdb;
    private int cantidadPeliculas;
    
    public UsuarioRanking() {
        super();
    }
    
    public UsuarioRanking(Usuario usuario, double promedioImdb, int cantidadPeliculas) {
        this.usuario = usuario;
        this.promedioImdb = promedioImdb;
        this.cantidadPeliculas = cantidadPeliculas;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public double getPromedioImdb() {
        return promedioImdb;
    }
    
    public void setPromedioImdb(double promedioImdb) {
        this.promedioImdb = promedioImdb;
    }
    
    public int getCantidadPeliculas() {
        return cantidadPeliculas;
    }
    
    public void setCantidadPeliculas(int cantidadPeliculas) {
        this.cantidadPeliculas = cantidadPeliculas;
    }
}
