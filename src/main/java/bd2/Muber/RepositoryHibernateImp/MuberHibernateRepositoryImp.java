package bd2.Muber.RepositoryHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Muber;

public class MuberHibernateRepositoryImp extends GenericRepositoryHibernateImp<Muber> {

	public MuberHibernateRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory, Muber.class);
	}
	
	public Muber getMuber(){
		return this.recuperar(1);
	}

}
