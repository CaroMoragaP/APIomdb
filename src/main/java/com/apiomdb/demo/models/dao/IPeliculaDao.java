package com.apiomdb.demo.models.dao;

import java.util.List;

import com.apiomdb.demo.models.entity.Pelicula;

public interface IPeliculaDao {

	public List<Pelicula> findAll();

	public void save(Pelicula peli);
	
	public Pelicula findOne(Long id);
	
	public void delete(Long id);
}
