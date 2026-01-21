package com.apiomdb.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiomdb.demo.models.dao.IActividadDao;
import com.apiomdb.demo.models.entity.Actividad;
import com.apiomdb.demo.service.IActividadService;

@Service
public class ActividadServiceImpl implements IActividadService{

	@Autowired
	private IActividadDao actividadDao;
	
	@Override
	public List<Actividad> findAll() {
		return actividadDao.findAll();
	}

	@Override
	public void save(Actividad act) {
		actividadDao.save(act);
		
	}

	@Override
	public Actividad findOne(int id) {
		return actividadDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		actividadDao.delete(id);		
	}

	@Override
	public List<Actividad> findAllOrderByFechaDesc() {
		return actividadDao.findAllOrderByFechaDesc();
	}

}
