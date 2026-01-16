package com.apiomdb.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apiomdb.demo.models.dao.IUsuarioPeliculaDao;
import com.apiomdb.demo.models.entity.UsuarioPelicula;
import com.apiomdb.demo.models.entity.UsuarioPeliculaId;
import com.apiomdb.demo.service.IUsuarioPeliculaService;

@Service
public class UsuarioPeliculaServiceImpl implements IUsuarioPeliculaService {

    @Autowired
    private IUsuarioPeliculaDao usuarioPeliculaDao;

    @Override
    @Transactional
    public void save(UsuarioPelicula usuarioPelicula) {
        usuarioPeliculaDao.save(usuarioPelicula);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioPelicula findOne(UsuarioPeliculaId id) {
        return usuarioPeliculaDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioPelicula> findByUsuario(String mail) {
        return usuarioPeliculaDao.findByUsuario(mail);
    }

    @Override
    @Transactional
    public void delete(UsuarioPeliculaId id) {
        usuarioPeliculaDao.delete(id);
    }

	@Override
	public List<UsuarioPelicula> findByReceptor(String receptorMail) {
		return usuarioPeliculaDao.findByReceptor(receptorMail);
	}

	@Override
	public List<UsuarioPelicula> findByUsuarioAndReceptor(String usuarioMail, String receptorMail) {
		return usuarioPeliculaDao.findByUsuarioAndReceptor(usuarioMail, receptorMail);
	}
}
