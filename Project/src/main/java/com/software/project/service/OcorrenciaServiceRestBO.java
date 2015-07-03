package com.software.project.service;

import java.util.List;

import com.software.project.entities.MsgResponse;
import com.software.project.entities.Ocorrencia;



public interface OcorrenciaServiceRestBO {

	public void createNew(Ocorrencia ocorrencia) throws Exception;
    public List<Ocorrencia> getAll() throws Exception;
    public List<Ocorrencia> getByUserId(Long idUser) throws Exception;
	public Ocorrencia findById(Long long1);
	public Ocorrencia getByLatLng(double lat, double lng);
	public Long countOcorrencia();
	public Long countOcorrencia(Long idOcorrencia);
	public void deleteById(String nameColumn, Long id) throws Exception;
	public Long countOcorrenciaByType(String title);
	public MsgResponse getAlertByLatLng(double lat, double lng, double dist);
	
}
