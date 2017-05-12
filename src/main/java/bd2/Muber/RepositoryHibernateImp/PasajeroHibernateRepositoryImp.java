package bd2.Muber.RepositoryHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Pasajero;

public class PasajeroHibernateRepositoryImp extends GenericRepositoryHibernateImp<Pasajero> {

	public PasajeroHibernateRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory,Pasajero.class);
	}

}
