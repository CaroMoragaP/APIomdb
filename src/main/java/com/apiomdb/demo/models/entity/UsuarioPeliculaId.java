package com.apiomdb.demo.models.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioPeliculaId implements Serializable {
	//solo incluye los campos que se relacionan (usuario, receptor y pelicula)
	//ranking se incluye en la clase principal

	@Column(name = "usuario")
    private String usuarioMail;
	
	@Column(name = "receptor", nullable = true)
    private String receptorMail;

    @Column(name = "pelicula_imdb")
    private String peliculaImdb;
    

    public UsuarioPeliculaId() {}
    
    public UsuarioPeliculaId(String usuarioMail, String receptorMail, String peliculaImdb) {
		super();
		this.usuarioMail = usuarioMail;
		this.receptorMail = receptorMail;
		this.peliculaImdb = peliculaImdb;
	}


    public String getUsuarioMail() {
		return usuarioMail;
	}

	public void setUsuarioMail(String usuarioMail) {
		this.usuarioMail = usuarioMail;
	}

	public String getReceptorMail() {
		return receptorMail;
	}


	public void setReceptorMail(String receptorMail) {
		this.receptorMail = receptorMail;
	}


	public String getPeliculaImdb() {
		return peliculaImdb;
	}


	public void setPeliculaImdb(String peliculaImdb) {
		this.peliculaImdb = peliculaImdb;
	}


	@Override
	public int hashCode() {
		return Objects.hash(peliculaImdb, receptorMail, usuarioMail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPeliculaId other = (UsuarioPeliculaId) obj;
		return Objects.equals(peliculaImdb, other.peliculaImdb)
				&& Objects.equals(receptorMail, other.receptorMail) && Objects.equals(usuarioMail, other.usuarioMail);
	}

}

