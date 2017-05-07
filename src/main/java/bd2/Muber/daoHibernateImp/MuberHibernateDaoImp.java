package bd2.Muber.daoHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Muber;

public class MuberHibernateDaoImp extends GenericDaoHibernateImp<Muber> {

	public MuberHibernateDaoImp(SessionFactory sessionFactory) {
		super(sessionFactory, Muber.class);
	}

}
