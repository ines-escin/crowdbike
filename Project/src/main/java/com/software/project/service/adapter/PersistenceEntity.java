package com.software.project.service.adapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.software.project.entities.Entity;
import com.software.project.entities.Ocorrencia;

public interface PersistenceEntity {
	
	public void createNew(Ocorrencia ocurrence) throws Exception;
    public List<Ocorrencia> getAll() throws Exception;
    public List<Ocorrencia> getById(Long id) throws Exception;
	public Ocorrencia findById(Long id) throws ParseException;
	public Ocorrencia getByLatLng(double lat, double lng);
	public Long countOcorrencia() throws Exception;
	public List<Ocorrencia> getByUserId(Long id) throws Exception;
	public void deleteById(Long id) throws Exception;
	public Long countOcorrenciaByType(String title) throws Exception;
	
}
