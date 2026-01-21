package com.apiomdb.demo.models.dao;

import java.util.List;

import com.apiomdb.demo.models.entity.Actividad;


public interface IActividadDao {

	public List<Actividad> findAll();

	public void save(Actividad act);
	
	public Actividad findOne(int id);
	
	public void delete(int id);
}
