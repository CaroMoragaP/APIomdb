package com.apiomdb.demo.service;

import java.util.List;

import com.apiomdb.demo.models.entity.UsuarioPelicula;
import com.apiomdb.demo.models.entity.UsuarioPeliculaId;

public interface IUsuarioPeliculaService {
	
	public void save(UsuarioPelicula usuarioPelicula);
    
	public UsuarioPelicula findOne(UsuarioPeliculaId id);
    
	public List<UsuarioPelicula> findByUsuario(String mail);

	 public void delete(UsuarioPeliculaId id);
}
