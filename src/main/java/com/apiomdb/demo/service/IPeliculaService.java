package com.apiomdb.demo.service;

import java.util.List;

import com.apiomdb.demo.models.entity.Pelicula;

public interface IPeliculaService {
	public List<Pelicula> findAll();

	public void save(Pelicula peli);
	
	public Pelicula findOne(Long id);
	
	public void delete(Long id);
}
