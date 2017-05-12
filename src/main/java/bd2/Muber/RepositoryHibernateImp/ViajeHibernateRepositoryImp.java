package bd2.Muber.RepositoryHibernateImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import bd2.Muber.model.Viaje;

public class ViajeHibernateRepositoryImp extends GenericRepositoryHibernateImp<Viaje> {

	public ViajeHibernateRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory,Viaje.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Viaje> getViajesAbiertos()
	{
		Query query = getCurrentSession().createQuery("from Viaje where finalizado = false");
		return (List<Viaje>) query.list();
	}
}