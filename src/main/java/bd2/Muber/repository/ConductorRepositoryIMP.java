package bd2.Muber.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import bd2.Muber.model.Conductor;

public class ConductorRepositoryIMP extends GenericRepositoryIMP<Conductor> {

	public ConductorRepositoryIMP(SessionFactory sessionFactory) {
		super(sessionFactory, Conductor.class);
	}

	@SuppressWarnings("unchecked")
	public List<Conductor> getConductoresSinViajesAbiertos(){
		Query query = getCurrentSession().createQuery("from Conductor c where not exists ( from c.viajes v where v.finalizado = false)");
		return (List<Conductor>) query.list();
	}
}