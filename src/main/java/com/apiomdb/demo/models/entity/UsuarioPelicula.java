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
    @JoinColumn(name = "receptor", insertable = false, updatable = false)
    private Usuario receptor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_imdb", insertable = false, updatable = false)
    private Pelicula pelicula;

    @Column(name = "ranking Usuario")
    private float rankingUsuario;
    
    @Column(name = "ranking Receptor")
    private float rankingReceptor;

    public UsuarioPelicula() {}

	public UsuarioPelicula(UsuarioPeliculaId id, Usuario usuario, Usuario receptor, Pelicula pelicula,
			float rankingUsuario, float rankingReceptor) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.receptor = receptor;
		this.pelicula = pelicula;
		this.rankingUsuario = rankingUsuario;
		this.rankingReceptor = rankingReceptor;
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
		return Objects.hash(id, pelicula, rankingReceptor, rankingUsuario, receptor, usuario);
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
				&& Float.floatToIntBits(rankingReceptor) == Float.floatToIntBits(other.rankingReceptor)
				&& Float.floatToIntBits(rankingUsuario) == Float.floatToIntBits(other.rankingUsuario)
				&& Objects.equals(receptor, other.receptor) && Objects.equals(usuario, other.usuario);
	}

}