package com.apiomdb.demo.models.dao;

import java.util.List;

import com.apiomdb.demo.models.entity.UsuarioPelicula;
import com.apiomdb.demo.models.entity.UsuarioPeliculaId;

public interface IUsuarioPeliculaDao {
	
	public void save(UsuarioPelicula up);
    
	public UsuarioPelicula findOne(UsuarioPeliculaId id);
    
	public List<UsuarioPelicula> findByUsuario(String mail);
    
	public void delete(UsuarioPeliculaId id);
}
