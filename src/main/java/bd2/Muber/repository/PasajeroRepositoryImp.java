package bd2.Muber.repository;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Pasajero;

public class PasajeroRepositoryImp extends GenericRepositoryImp<Pasajero> {

	public PasajeroRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory,Pasajero.class);
	}

}
