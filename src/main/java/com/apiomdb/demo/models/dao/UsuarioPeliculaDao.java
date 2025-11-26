package com.apiomdb.demo.models.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.apiomdb.demo.models.entity.UsuarioPelicula;
import com.apiomdb.demo.models.entity.UsuarioPeliculaId;

@Repository
public class UsuarioPeliculaDao implements IUsuarioPeliculaDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(UsuarioPelicula up) {
        em.merge(up);
    }

    @Override
    public UsuarioPelicula findOne(UsuarioPeliculaId id) {
        return em.find(UsuarioPelicula.class, id);
    }

    @Override
    public List<UsuarioPelicula> findByUsuario(String mail) {
        return em.createQuery("SELECT up FROM UsuarioPelicula up WHERE up.usuario.mail = :mail", UsuarioPelicula.class)
                 .setParameter("mail", mail)
                 .getResultList();
    }

    @Override
    public void delete(UsuarioPeliculaId id) {
        UsuarioPelicula up = em.find(UsuarioPelicula.class, id);
        if (up != null) {
            em.remove(up);
        }
    }
}
