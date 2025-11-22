package com.apiomdb.demo.service;

import java.util.List;

import com.apiomdb.demo.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();

	public void save(Usuario usu);
	
	public void delete(String mail);

	public Usuario findOne(String mail);
}
