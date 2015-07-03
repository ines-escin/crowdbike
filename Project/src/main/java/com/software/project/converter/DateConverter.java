package com.software.project.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.column.Column;


/**
 * @author dcavalcanti
 *
 */
@FacesConverter("com.software.project.converter.DateConverter")
public class DateConverter implements Converter {

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException{
		Date date = null; 
		try {
			if(value != null && !value.trim().equals("")){
				sdf.setLenient(false);
				date = sdf.parse(value);
			}
		} catch (ParseException e) {
			Column column = null;
			try{
				column = (Column) component.getParent().getParent();
			}catch(Exception e2){
				column = (Column) component.getParent().getParent().getParent();
			}
			String mensagem = "exception.invalid_date " + column.getHeaderText();
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(mensagem);
			message.setDetail(mensagem);
			throw new ConverterException(message);
		}
		return date;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object date) {
		return sdf.format(date);
	}
}