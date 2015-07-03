package com.software.project.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.software.project.entities.MsgResponse;
import com.software.project.entities.Ocorrencia;


@Repository("OcorrenciaDAO")
public class OcorrenciaDAOImp extends GenericDAOImp<Ocorrencia, Long> implements OcorrenciaDAO , Serializable  {

	@Override
	public List<Ocorrencia> getByUserId(Long idUser) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT o ");
		sb.append("FROM Ocorrencia o ");
		sb.append("WHERE o.user.id = ?1 ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("1", idUser);

		
		return listSearchParam(sb.toString(), params);
	}
	
	@Override
	public Long countOcorrencia() {
		 String querty = "SELECT DISTINCT o FROM Ocorrencia o ";
		 Query q = getEntityManager().createQuery(querty);
		 return new Long(q.getResultList().size());
	}
	
	@Override
	public Long countOcorrencia(Long idOcorrencia) {
		 String querty = "SELECT DISTINCT o FROM Ocorrencia o where o.idOcorrencia = ?1 ";
		 Query q = getEntityManager().createQuery(querty);
		 q.setParameter("1", idOcorrencia);
		 return new Long(q.getResultList().size());
	}
	
	@Override
	public Long countOcorrenciaByType(String title) {
		 String querty = "SELECT DISTINCT o FROM Ocorrencia o where o.title = ?1 ";
		 Query q = getEntityManager().createQuery(querty);
		 q.setParameter("1", title);
		 return new Long(q.getResultList().size());
	}

	@Override
	public Ocorrencia getByLatLng(double lat, double lng) {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT o ");
		sb.append("FROM Ocorrencia o ");
		sb.append("WHERE CONVERT(o.lat,char) = ?1 and CONVERT(o.lng,char) = ?2  ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("1", Double.toString(lat));
		params.put("2", Double.toString(lng));

		return searchParam(sb.toString(), params);
	}

	@Override
	public MsgResponse getAlertByLatLng(double lat, double lng, double dist) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT *,((3956 * 2 * ASIN( SQRT(POWER( ");
		sb.append("SIN((abs("+lat+") - abs(dest.lat)) * pi()/180 / 2),2) + ");
		sb.append("COS(abs("+lat+") * pi()/180 ) * COS(abs(dest.lat) * pi()/180) * ");
		sb.append("POWER(SIN((abs("+lng+") - abs(dest.lng)) * pi()/180 / 2), 2)) ");
		sb.append(")) * 1.609344) as distancia ");
		sb.append("FROM ocorrencia dest ");
		sb.append("WHERE abs("+lat+") != abs(dest.lat) AND abs("+lng+") != abs(dest.lng) ");
		sb.append("having distancia  <> 0 and distancia < "+dist+" ORDER BY distancia limit 1 ");
        double d;
		Query q = getEntityManager().createNativeQuery(sb.toString());
		List<Object> queryResult = q.getResultList();
		MsgResponse msgResponse = new MsgResponse();
		for (int i = 0; i < queryResult.size(); i++) {
		  Object[] row = (Object[]) queryResult.get(i);
		  msgResponse.setIdUser(String.valueOf((BigInteger)row[5]));
		  msgResponse.setTitle((String)row[4]);
		  msgResponse.setLat(String.valueOf(row[2]));
		  msgResponse.setLng(String.valueOf(row[3]));
		  msgResponse.setEndereco((String)row[6]);
		  d = Double.valueOf(String.valueOf((Double)row[7]))*1000;
		  msgResponse.setDistance(String.valueOf(d)); 
		 
		}
		return msgResponse;
	}
	
	private Date getDateValue(Object object) {
		if (object == null ) {
			return null;
		}if(object instanceof Timestamp){
			Timestamp timestamp = (Timestamp)object;
			return new Date(timestamp.getTime());
		}else if(object instanceof Date){
			return (java.sql.Date)object;
		}
		return (Date) object;
	}
    
	
}
