package com.apiomdb.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apiomdb.demo.models.dao.IUsuarioDao;
import com.apiomdb.demo.models.entity.Usuario;
import com.apiomdb.demo.service.IUsuarioService;



@Service
public class UsuarioServicelmpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usu) {
		usuDao.save(usu);
		
	}


	@Override
	@Transactional
	public Usuario findOne(String email) {
		return usuDao.findOne(email);
	}

	@Override
	public void delete(String mail) {
		usuDao.delete(mail);
		
	}

}
