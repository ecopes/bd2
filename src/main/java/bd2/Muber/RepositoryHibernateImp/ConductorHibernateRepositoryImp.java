package bd2.Muber.RepositoryHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Conductor;

public class ConductorHibernateRepositoryImp extends GenericRepositoryHibernateImp<Conductor> {

	public ConductorHibernateRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory, Conductor.class);
	}
}