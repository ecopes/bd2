package bd2.Muber.repository;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Muber;

public class MuberRepositoryImp extends GenericRepositoryImp<Muber> {

	public MuberRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory, Muber.class);
	}
	
	public Muber getMuber(){
		return this.recuperar(1);
	}

}
