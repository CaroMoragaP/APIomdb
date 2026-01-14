package com.apiomdb.demo.models.entity;

import java.util.Objects;

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
    @JoinColumn(name = "usuario", insertable = false, updatable = false)
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY) //la relación NO se carga automáticamente, solo cuando se necesita (mejor el rendimiento)
    @JoinColumn(name = "receptor", insertable = false, updatable = false, nullable = true)
    private Usuario receptor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_imdb", insertable = false, updatable = false)
    private Pelicula pelicula;

    @Column(name = "ranking")
    private float ranking;

    public UsuarioPelicula() {}

	public UsuarioPelicula(UsuarioPeliculaId id, Usuario usuario, Usuario receptor, Pelicula pelicula,
			float rankingUsuario) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.receptor = receptor;
		this.pelicula = pelicula;
		this.ranking = rankingUsuario;
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

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public float getRankingUsuario() {
		return ranking;
	}

	public void setRankingUsuario(float rankingUsuario) {
		this.ranking = rankingUsuario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, pelicula, ranking, receptor, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPelicula other = (UsuarioPelicula) obj;
		return Objects.equals(id, other.id) && Objects.equals(pelicula, other.pelicula)
				&& Float.floatToIntBits(ranking) == Float.floatToIntBits(other.ranking)
				&& Objects.equals(receptor, other.receptor) && Objects.equals(usuario, other.usuario);
	}

}