package com.software.project.dao;

import java.util.List;

import com.software.project.entities.MsgResponse;
import com.software.project.entities.Ocorrencia;

public interface OcorrenciaDAO extends GenericDAO<Ocorrencia, Long>{

	public List<Ocorrencia> getByUserId(Long idUser);

	public Long countOcorrencia();

	public Long countOcorrencia(Long idOcorrencia);

	public Long countOcorrenciaByType(String title);
	
	public Ocorrencia getByLatLng(double lat, double lng);
	
	public MsgResponse getAlertByLatLng(double lat, double lng, double dist);

}
