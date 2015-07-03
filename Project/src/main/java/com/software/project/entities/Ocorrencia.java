package com.software.project.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ocorrencia implements Serializable {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Basic(optional = false)
	  @Column(name = "idOcorrencia", nullable = false)
	  private Long idOcorrencia;
	  
	  @Column(name = "title", nullable = false)
	  private String title;  
	  
	  @Column(name = "endereco", nullable = false)
	  private String endereco; 
	  
	  @Column(name = "lat", nullable = false)
      private String lat;       
      
	  @Column(name = "lng", nullable = false)
	  private String lng;   
	  
	  @Basic(optional = false)
	  @Column(name = "dataOcorrencia", nullable = false)
	  private Date dataOcorrencia;
	  
	  @ManyToOne(optional = true, fetch = FetchType.EAGER)  
	  @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)  
	  private User user;

	public Long getIdOcorrencia() {
		return idOcorrencia;
	}

	public void setIdOcorrencia(Long idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	  
	  
	  

}
