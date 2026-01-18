package com.apiomdb.demo.service;

import java.util.List;

import com.apiomdb.demo.models.entity.UsuarioPelicula;
import com.apiomdb.demo.models.entity.UsuarioPeliculaId;

public interface IUsuarioPeliculaService {
	
	public void save(UsuarioPelicula usuarioPelicula);
    public void delete(UsuarioPeliculaId id);
    
	public UsuarioPelicula findOne(UsuarioPeliculaId id);
    
	public List<UsuarioPelicula> findByUsuario(String usuarioMail);

	public List<UsuarioPelicula> findByReceptor(String receptorMail);
	
	public List<UsuarioPelicula> findByUsuarioAndReceptor(String usuarioMail, String receptorMail);
	
	public List<UsuarioPelicula> findByUsuarioAndReceptorNot(String usuarioMail, String receptorMail);
}
