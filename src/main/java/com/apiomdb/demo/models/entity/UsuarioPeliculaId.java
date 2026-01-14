package com.apiomdb.demo.models.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioPeliculaId implements Serializable {

	@Column(name = "usuario")
    private String usuarioMail;
	
	@Column(name = "receptor")
    private String receptorMail;

    @Column(name = "pelicula_imdb")
    private String peliculaImdb;
    
    @Column(name = "ranking_usuario")
    private float rankingUsuario;
    
    @Column(name = "ranking_receptor")
    private float rankingReceptor;
    

    public UsuarioPeliculaId() {}
    
    public UsuarioPeliculaId(String usuarioMail, String receptorMail, String peliculaImdb, float rankingUsuario,
			float rankingReceptor) {
		super();
		this.usuarioMail = usuarioMail;
		this.receptorMail = receptorMail;
		this.peliculaImdb = peliculaImdb;
		this.rankingUsuario = rankingUsuario;
		this.rankingReceptor = rankingReceptor;
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


	public float getRankingUsuario() {
		return rankingUsuario;
	}


	public void setRankingUsuario(float rankingUsuario) {
		this.rankingUsuario = rankingUsuario;
	}


	public float getRankingReceptor() {
		return rankingReceptor;
	}


	public void setRankingReceptor(float rankingReceptor) {
		this.rankingReceptor = rankingReceptor;
	}


	@Override
	public int hashCode() {
		return Objects.hash(peliculaImdb, rankingReceptor, rankingUsuario, receptorMail, usuarioMail);
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
				&& Float.floatToIntBits(rankingReceptor) == Float.floatToIntBits(other.rankingReceptor)
				&& Float.floatToIntBits(rankingUsuario) == Float.floatToIntBits(other.rankingUsuario)
				&& Objects.equals(receptorMail, other.receptorMail) && Objects.equals(usuarioMail, other.usuarioMail);
	}

}

