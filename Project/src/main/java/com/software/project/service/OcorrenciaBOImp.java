package com.software.project.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.software.project.dao.OcorrenciaDAO;
import com.software.project.entities.Ocorrencia;



@Service("OcorrenciaBO")
@Transactional(propagation=Propagation.REQUIRED)
public class OcorrenciaBOImp implements OcorrenciaBO{
    
	@Resource
	OcorrenciaDAO ocorrenciaDAO;
	
	@Override
	public void createNew(Ocorrencia ocorrencia) throws Exception {
		// TODO Auto-generated method stub
		ocorrenciaDAO.createNew(ocorrencia);
	}

	@Override
	public List<Ocorrencia> getAll() throws Exception {
		// TODO Auto-generated method stub
		return ocorrenciaDAO.all();
	}

	@Override
	public List<Ocorrencia> getByUserId(Long idUser) throws Exception {
		// TODO Auto-generated method stub
	    return ocorrenciaDAO.getByUserId(idUser);
	}

	@Override
	public Ocorrencia findById(Long id) {
		// TODO Auto-generated method stub
		return ocorrenciaDAO.searchById(id);
	}

	@Override
	public Long countOcorrencia() {
		// TODO Auto-generated method stub
		return ocorrenciaDAO.countOcorrencia();
	}

	@Override
	public Long countOcorrencia(Long idOcorrencia) {
		// TODO Auto-generated method stub
		return ocorrenciaDAO.countOcorrencia(idOcorrencia);
	}

	@Override
	public void deleteById(String nameColumn, Long id) throws Exception {
		// TODO Auto-generated method stub
		ocorrenciaDAO.delete(nameColumn, id);
	}

	@Override
	public Long countOcorrenciaByType(String title) {
		// TODO Auto-generated method stub
		return ocorrenciaDAO.countOcorrenciaByType(title);
	}

	@Override
	public Ocorrencia getByLatLng(double lat, double lng) {
		// TODO Auto-generated method stub
		return ocorrenciaDAO.getByLatLng(lat, lng);
	}

	

}
