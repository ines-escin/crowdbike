package com.software.project.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

 
public interface GenericDAO<T, ID extends Serializable> {

  
    public Class<T> getObjectClass();

    public T createNew(T entityBean) throws Exception;

    public T update(T entityBean) throws Exception;

    public void delete(T entityBean) throws Exception;
    
	public void delete(String propertyName, ID id) throws Exception;

    public List<T> all();

    public List<T> all(String columnName);

    public T searchById(ID id);

    public List<T> listSearch(String query);

    public List<T> listSearchParam(String query, Map<String, Object> params);

    public List<T> listSearchParam(String query, Map<String, Object> params,int max, int current);

    public T searchParam(String query, Map<String, Object> params);
    
    public T searchParam(String query, Map<String, Object> params, int fistResult, int maxResult);
 
    public void flush() throws Exception;

}
