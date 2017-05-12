package bd2.Muber.RepositoryHibernateImp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericRepositoryHibernateImp<T> {

	protected Class<T> persistentClass;

	private SessionFactory sessionFactory;

	public GenericRepositoryHibernateImp(SessionFactory sessionFactory,Class<T> clase) {
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

		Query query = getCurrentSession().createQuery("from "+getPersistentClass().getName());
		@SuppressWarnings("unchecked")
		List<T> resultado = (List<T>) query.list();

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