package bd2.Muber.daoHibernateImp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.PersistentObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDaoHibernateImp<T> {

	protected Class<T> persistentClass;

	private SessionFactory sessionFactory;

	public GenericDaoHibernateImp(SessionFactory sessionFactory,Class<T> clase) {
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

	public T persistir(T entity) {
		try{
			this.getCurrentSession().save(entity); 
		}
		catch(PersistentObjectException e){
			this.getCurrentSession().merge(entity);
		}		

		return entity;
	}

	public T actualizar(T entity) {
		this.getCurrentSession().clear();
		this.getCurrentSession().merge(entity);
		return entity;
	}

	public void borrarFisico(T entity) {
		this.getCurrentSession().delete(entity);
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
	/*	
  	public List<T> recuperarTodosSinBorrados(String columnOrder) {
		Query consulta= this.getEntityManager().createQuery("select	e from "+ getPersistentClass().getSimpleName()+" e where estado = 1 order by	:columnOrder").setParameter("columnOrder", "e."+columnOrder);
		@SuppressWarnings("unchecked")
		List<T> resultado = (List<T>)consulta.getResultList();
		return resultado;
	}

	public List<T> recuperarTodosConBorrados(String columnOrder) {
		Query consulta= this.getEntityManager().createQuery("select	e from "+ getPersistentClass().getSimpleName()+" e where order by	:columnOrder").setParameter("columnOrder", "e."+columnOrder);
		@SuppressWarnings("unchecked")
		List<T> resultado = (List<T>)consulta.getResultList();
		return resultado;
	}

	public void borrarFisico(Serializable id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null) {
			this.borrarFisico(entity);
		}

	}

	public void borrar(Serializable id){
		Query query = this.getEntityManager().createQuery("update "+ getPersistentClass().getSimpleName()+" set estado = 0" +
				" where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	public List<T> recuperarBorrados(String columnOrder) {
		Query consulta= this.getEntityManager().createQuery("select	e from "+ getPersistentClass().getSimpleName()+" e where estado = 0 order by	:columnOrder").setParameter("columnOrder", "e."+columnOrder);
		@SuppressWarnings("unchecked")
		List<T> resultado = (List<T>)consulta.getResultList();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public T recuperarBorrado(Serializable id) {
		try{
			Query query = this.getEntityManager().createQuery("select	e from "+ getPersistentClass().getSimpleName()+" e where estado = 1 and id = :id");
			query.setParameter("id", id);
			T t =  (T) query.getSingleResult();
			return t;
		}catch(NoResultException e)
		{
			return null;
		}
	}
	 */
}