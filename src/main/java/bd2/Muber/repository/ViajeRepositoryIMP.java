package bd2.Muber.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import bd2.Muber.model.Viaje;

public class ViajeRepositoryIMP extends GenericRepositoryIMP<Viaje> {

	public ViajeRepositoryIMP(SessionFactory sessionFactory) {
		super(sessionFactory,Viaje.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Viaje> getViajesAbiertos()
	{
		Query query = getCurrentSession().createQuery("from Viaje where finalizado = false");
		return (List<Viaje>) query.list();
	}
}