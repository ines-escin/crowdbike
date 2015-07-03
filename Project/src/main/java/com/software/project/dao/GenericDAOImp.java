package com.software.project.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDAOImp<T, ID extends Serializable> implements GenericDAO<T, ID>{
    
	@PersistenceContext
    private EntityManager entityManager;
    
    private final Class<T> myClass;			

    @Override
	public Class<T> getObjectClass() {
        return this.myClass;
    }

    @SuppressWarnings("unchecked")
    public GenericDAOImp() {
        this.myClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

 
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

   
    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            throw new IllegalStateException("Erro");
        }
        return entityManager;
    }

/*	protected DataSource getDataSource() throws SQLException {
		// Get current context from spring application
        ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();  
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		// Retrieve the data source from the application context
		DataSource ds = (DataSource) ctx.getBean("dataSource");
		return ds;
	}*/
    /* ------------------------------ GenericDAO INTERFACE -------------------------------------- */
  
	
    @Override
    public T createNew(T entityBean) throws Exception{
    	getEntityManager().persist(entityBean);
        return entityBean;
    }
  
    @Override
    public T update(T entityBean) throws Exception{
    	entityBean = getEntityManager().merge(entityBean);
    	getEntityManager().flush();
        return entityBean;
    }
   
    @Override
    public void delete(T entityBean) throws Exception{
        entityBean = getEntityManager().merge(entityBean);
        getEntityManager().remove(entityBean);
        getEntityManager().flush();
    }
    
   
    @Override
    public void delete(String propertyName, ID id) throws Exception{
    	Query q = getEntityManager()
    			.createQuery("DELETE " + myClass.getSimpleName() + " WHERE " + propertyName  + " = ?1")
        		.setParameter(1, id);
        q.executeUpdate();
        flush();
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<T> all(String columnName) {
    	String queryS = " FROM " + myClass.getSimpleName() + " ";
    	if(columnName!=null){
    		if(columnName.indexOf(',')!=-1){
    			queryS += " ORDER BY " +  columnName; 
    		}else{
    			queryS += " GROUP BY " +  columnName +  " ORDER BY " +  columnName; 
    		}
    	}
        Query query = getEntityManager().createQuery(queryS);
        return query.getResultList();    	
	}
   
    @Override
    public List<T> all() {
        return all(null);
    }

    @Override
    public T searchById(ID id) {
        return getEntityManager().find(myClass, id);
    }
   
    @SuppressWarnings("unchecked")
    @Override
    public List<T> listSearch(String query) {
        Query q = getEntityManager().createQuery(query);
        return q.getResultList();
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<T> listSearchParam(String query, Map<String, Object> params) {
        Query q = getEntityManager().createQuery(query);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
        return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> listSearchParam(String query, Map<String, Object> params, int max, int current) {
        Query q = getEntityManager().
                createQuery(query).
                setMaxResults(max).
                setFirstResult(current);

        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));

        }
        return q.getResultList();
    }
    
   
    @SuppressWarnings("unchecked")
    @Override
    public T searchParam(String query, Map<String, Object> params) {
        Query q = getEntityManager().createQuery(query);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
        try {
            return (T) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
   
    @SuppressWarnings("unchecked")
    @Override
    public T searchParam(String query, Map<String, Object> params, int fistResult, int maxResult) {
        Query q = getEntityManager().createQuery(query);
        q.setFirstResult(fistResult);
        q.setMaxResults(maxResult);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
        
        try {
            return (T) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

	@Override
	public void flush() throws Exception {
		getEntityManager().flush();
	}
}
