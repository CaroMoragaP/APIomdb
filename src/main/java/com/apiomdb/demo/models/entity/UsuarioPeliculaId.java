package com.apiomdb.demo.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioPeliculaId implements Serializable {

	@Column(name = "usuario_mail")
    private String usuarioMail;

    @Column(name = "pelicula_imdb")
    private String peliculaImdb;

    public UsuarioPeliculaId() {}

    public UsuarioPeliculaId(String usuarioMail, String peliculaImdb) {
        this.usuarioMail = usuarioMail;
        this.peliculaImdb = peliculaImdb;
    }

    public String getUsuarioId() {
        return usuarioMail;
    }

    public void setUsuarioId(String usuarioMail) {
        this.usuarioMail = usuarioMail;
    }

    public String getPeliculaImdbId() {
        return peliculaImdb;
    }

    public void setPeliculaId(String peliculaImdbId) {
        this.peliculaImdb = peliculaImdbId;
    }

    // Importante para claves compuestas
    @Override
    public int hashCode() {
        return usuarioMail.hashCode() + peliculaImdb.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UsuarioPeliculaId other = (UsuarioPeliculaId) obj;
        return usuarioMail.equals(other.usuarioMail) && peliculaImdb.equals(other.peliculaImdb);
    }
}

