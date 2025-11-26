package com.apiomdb.demo.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_pelicula")
public class UsuarioPelicula {

    @EmbeddedId
    private UsuarioPeliculaId id;

    @ManyToOne(fetch = FetchType.LAZY) //la relación NO se carga automáticamente, solo cuando se necesita (mejor el rendimiento)
    @JoinColumn(name = "usuario_mail", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_imdb", insertable = false, updatable = false)
    private Pelicula pelicula;

    @Column
    private float ranking;

    public UsuarioPelicula() {}

    public UsuarioPelicula(Usuario usuario, Pelicula pelicula, float ranking) {
        this.id = new UsuarioPeliculaId(usuario.getMail(), pelicula.getImdbID());
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.ranking = ranking;
    }

    public UsuarioPeliculaId getId() {
        return id;
    }

    public void setId(UsuarioPeliculaId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }
}