package com.apiomdb.demo.models.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="actividades")
public class Actividad {

    public enum TipoActividad { COMPARTIR_PELICULA, PUNTUAR_PELICULA, GUARDAR_PELICULA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividad tipoActividad;

    // Usuario que realiza la acción
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Usuario receptor (opcional, solo para compartir)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptor_id", nullable = true)
    private Usuario receptor;

    // Película asociada a la actividad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    // Ranking registrado en el momento de la actividad
    @Column(name = "ranking")
    private int ranking;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public Actividad() {
        this.fecha = LocalDateTime.now();
    }

    public Actividad(TipoActividad tipoActividad, Usuario usuario, Usuario receptor, Pelicula pelicula, int ranking, LocalDateTime fecha) {
        this.tipoActividad = tipoActividad;
        this.usuario = usuario;
        this.receptor = receptor;
        this.pelicula = pelicula;
        this.ranking = ranking;
        this.fecha = (fecha != null) ? fecha : LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, id, pelicula, ranking, receptor, tipoActividad, usuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Actividad)) return false;
        Actividad other = (Actividad) obj;
        return Objects.equals(fecha, other.fecha) &&
               Objects.equals(id, other.id) &&
               Objects.equals(pelicula, other.pelicula) &&
               ranking == other.ranking &&
               Objects.equals(receptor, other.receptor) &&
               tipoActividad == other.tipoActividad &&
               Objects.equals(usuario, other.usuario);
    }
}
