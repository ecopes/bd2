package bd2.Muber.repository;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Pasajero;

public class PasajeroRepositoryIMP extends GenericRepositoryIMP<Pasajero> {

	public PasajeroRepositoryIMP(SessionFactory sessionFactory) {
		super(sessionFactory,Pasajero.class);
	}

}
