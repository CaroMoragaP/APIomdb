package com.apiomdb.demo.service;

import java.util.List;

import com.apiomdb.demo.models.entity.Actividad;

public interface IActividadService {
	
	public List<Actividad> findAll();
	
	public List<Actividad> findAllOrderByFechaDesc();

	public void save(Actividad act);
	
	public Actividad findOne(int id);
	
	public void delete(int id);

}
