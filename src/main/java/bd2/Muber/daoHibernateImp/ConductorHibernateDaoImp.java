package bd2.Muber.daoHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Conductor;

public class ConductorHibernateDaoImp extends GenericDaoHibernateImp<Conductor> {

	public ConductorHibernateDaoImp(SessionFactory sessionFactory) {
		super(sessionFactory, Conductor.class);
	}
}