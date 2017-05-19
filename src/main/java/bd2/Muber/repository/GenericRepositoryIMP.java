package bd2.Muber.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericRepositoryIMP<T> {

	protected Class<T> persistentClass;

	private SessionFactory sessionFactory;

	public GenericRepositoryIMP(SessionFactory sessionFactory,Class<T> clase) {
		this.setPersistentClass(clase);
		this.sessionFactory = sessionFactory;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
		
	}

	public List<T> recuperarTodos() {
		@SuppressWarnings("unchecked")
		List<T> resultado = getCurrentSession().createCriteria(getPersistentClass()).list();
		return resultado;
	}

	public boolean existe(Serializable id) {
		T entity = this.recuperar(id);
		if (entity != null) {
			return true;
		} return false;
	}

	@SuppressWarnings("unchecked")
	public T recuperar(Serializable id) {
		try{
			return (T) this.getCurrentSession().get(getPersistentClass(),id);
		}catch(NoResultException e)
		{
			return null;
		}

	}
}