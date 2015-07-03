package com.software.project.beans;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.software.project.entities.Ocorrencia;
import com.software.project.service.LoginService;
import com.software.project.service.OcorrenciaBO;


@Controller("homeManageBean")
@Scope("view")
public class HomeManegeBean {
	
	    private MapModel emptyModel;  
      
	    private String title;  
	      
	    private String lat;  
	      
	    private String lng;  
	    
	    private String endereco;
	    
	    private Date date1 = new Date(); 
	    
	    private Marker marker; 
	    
	    private Long idDelete;
	    
	    private Ocorrencia ocorrencia;
	    private List<Ocorrencia> ocorrencias;
	    
	    
	    @Autowired
	    OcorrenciaBO ocorrenciaBO;
	    
	    @Resource
		private LoginService loginservice;
	  
	    public HomeManegeBean(){  
	        emptyModel = new DefaultMapModel();  
	        ocorrencia = new Ocorrencia();
	   }  
	    
	    @PostConstruct
	    public void init() throws Exception{
			
			ocorrencias = ocorrenciaBO.getByUserId(loginservice.getUser().getId());
			
	        if(ocorrencias.size()>0) {
	         	 for (Ocorrencia ocorrencia : ocorrencias) {
	             	 Marker marker = new Marker(new LatLng(Double.valueOf(ocorrencia.getLat()), Double.valueOf(ocorrencia.getLng())), ocorrencia.getTitle(), ocorrencia);  
	                  emptyModel.addOverlay(marker);
	     		}
	 		}

	    }
	      
	    public MapModel getEmptyModel() {  
	        return emptyModel;  
	    }  
	      
	    public void addMessage(FacesMessage message) {  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }  
	    
	    public void onMarkerSelect(OverlaySelectEvent event) {  
	        marker = (Marker) event.getOverlay();  
            idDelete = ((Ocorrencia)marker.getData()).getIdOcorrencia();
	    }  
	    
	    public void deleteMarcador() throws NumberFormatException, Exception{   
	        ocorrenciaBO.deleteById("idOcorrencia",idDelete);  
	        emptyModel.getMarkers().remove(marker);
	    } 
	    
	    public void selfRedirect() throws IOException{
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/pages/home.jsf");
	    }
	      
	    public String getTitle() {  
	        return title;  
	    }  
	  
	    public void setTitle(String title) {  
	        this.title = title;  
	    }  
	  
	    public String getLat() {  
	        return lat;  
	    }  
	  
	    public void setLat(String lat) {  
	        this.lat = lat;  
	    }  
	  
	    public String getLng() {  
	        return lng;  
	    }  
	  
	    public void setLng(String lng) {  
	        this.lng = lng;  
	    } 
	      

		public Ocorrencia getOcorrencia() {
			return ocorrencia;
		}

		public void setOcorrencia(Ocorrencia ocorrencia) {
			this.ocorrencia = ocorrencia;
		}

		public void addMarker(ActionEvent actionEvent) throws Exception {  
	        ocorrencia.setEndereco(endereco);
	        ocorrencia.setLat(lat);
	        ocorrencia.setLng(lng);
	        ocorrencia.setTitle(title.toUpperCase());
	        ocorrencia.setUser(loginservice.getUser());
	        ocorrencia.setDataOcorrencia(date1);
	        ocorrenciaBO.createNew(ocorrencia);  
	        Marker marker = new Marker(new LatLng(Double.valueOf(lat), Double.valueOf(lng)), title, ocorrencia); 
	        emptyModel.addOverlay(marker);
	        ocorrencia = new Ocorrencia();
	    }

		public List<Ocorrencia> getOcorrencias() {
			return ocorrencias;
		}

		public void setOcorrencias(List<Ocorrencia> ocorrencias) {
			this.ocorrencias = ocorrencias;
		}

		public Date getDate1() {
			return date1;
		}

		public void setDate1(Date date1) {
			this.date1 = date1;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}


	    
}
