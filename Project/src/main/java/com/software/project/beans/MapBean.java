package com.software.project.beans;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.software.project.entities.Ocorrencia;
import com.software.project.service.OcorrenciaBO;
import com.software.project.service.adapter.PersistenceEntity;
import com.software.project.service.adapter.PersistenceEntityWS;

@Controller("mapBean")
@Scope("view")
public class MapBean implements Serializable {  
	  
	private MapModel advancedModel;  
	  
    private Marker marker;  
    private Ocorrencia ocorrenciaSelected;
    private Ocorrencia ocorrencia;
    private List<Ocorrencia> ocorrencias;
    private String qtdOcorrencias;
    private PieChartModel pieModel;  
    
   /* @Autowired
    OcorrenciaBO ocorrenciaBO;*/
    @Autowired
    PersistenceEntity persistenceEntity;
  
    
    public MapBean() {  
        advancedModel = new DefaultMapModel();  
        ocorrencia = new Ocorrencia();
    }  
    
    @PostConstruct
    public void init() throws Exception{
    	
    	qtdOcorrencias = String.valueOf(persistenceEntity.countOcorrencia());
    	ocorrencias = persistenceEntity.getAll();
        if(ocorrencias.size()>0) {
         	 for (Ocorrencia ocorrencia : ocorrencias) {
             	  marker = new Marker(new LatLng(Double.valueOf(ocorrencia.getLat()), Double.valueOf(ocorrencia.getLng())), String.valueOf(ocorrencia.getIdOcorrencia()));  
             	 advancedModel.addOverlay(marker);
     		}
 		}
        createPieModel();
 
    }
    
    
	private void createPieModel() throws Exception {  
        pieModel = new PieChartModel();  
  
        pieModel.set("CPA", persistenceEntity.countOcorrenciaByType("CPA"));  
        pieModel.set("COVP",  persistenceEntity.countOcorrenciaByType("COVP"));  
        pieModel.set("CVM2-3R", persistenceEntity.countOcorrenciaByType("CVM2-3R"));  
        pieModel.set("CACC",  persistenceEntity.countOcorrenciaByType("CACC")); 
        pieModel.set("CTPO",  persistenceEntity.countOcorrenciaByType("CTPO"));
        pieModel.set("CTVF",  persistenceEntity.countOcorrenciaByType("CTVF"));
        pieModel.set("COVNM",  persistenceEntity.countOcorrenciaByType("COVNM"));
        pieModel.set("COFE",  persistenceEntity.countOcorrenciaByType("COFE"));
        pieModel.set("ANDT",  persistenceEntity.countOcorrenciaByType("ANDT"));
        pieModel.set("AI",  persistenceEntity.countOcorrenciaByType("AI"));
    }  
    
	
	
    public MapModel getAdvancedModel() {  
        return advancedModel;  
    }  
      
    public void onMarkerSelect(OverlaySelectEvent event) throws NumberFormatException, ParseException {  
        marker = (Marker) event.getOverlay();  
        ocorrenciaSelected = persistenceEntity.findById(Long.parseLong(marker.getTitle()));
    }  
      
    public Marker getMarker() {  
        return marker;  
    }

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Ocorrencia getOcorrenciaSelected() {
		return ocorrenciaSelected;
	}

	public void setOcorrenciaSelected(Ocorrencia ocorrenciaSelected) {
		this.ocorrenciaSelected = ocorrenciaSelected;
	}

	public String getQtdOcorrencias() {
		return qtdOcorrencias;
	}

	public void setQtdOcorrencias(String qtdOcorrencias) {
		this.qtdOcorrencias = qtdOcorrencias;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}  
    
}  
                      