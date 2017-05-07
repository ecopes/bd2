package bd2.Muber.daoHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Pasajero;

public class PasajeroHibernateDaoImp extends GenericDaoHibernateImp<Pasajero> {

	public PasajeroHibernateDaoImp(SessionFactory sessionFactory) {
		super(sessionFactory,Pasajero.class);
	}

}
