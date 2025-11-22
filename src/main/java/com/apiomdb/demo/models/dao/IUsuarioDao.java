package com.apiomdb.demo.models.dao;

import java.util.List;

import com.apiomdb.demo.models.entity.Usuario;

public interface IUsuarioDao {

	public List<Usuario> findAll();

	public void save(Usuario usu);
	
	public Usuario findOne(String mail);
	
	public void delete(String mail);
}
