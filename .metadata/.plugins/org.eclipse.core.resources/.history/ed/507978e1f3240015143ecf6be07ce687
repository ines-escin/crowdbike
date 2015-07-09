package com.software.project.service;

import java.util.ArrayList;
import java.util.List;

import com.software.project.entities.Entity;
import com.software.project.entities.Ocorrencia;

public interface PersistenceEntity {
	
	public void createNew(Entity entity) throws Exception;
    public List<Entity> getAll() throws Exception;
    public List<Entity> getById(Long id) throws Exception;
	public Entity findById(Long id);
	public Entity getByLatLng(double lat, double lng);
	public Long countEntity();
	public void deleteById(Long id) throws Exception;
	public Long countEntityByTitle(String title);
	

}
