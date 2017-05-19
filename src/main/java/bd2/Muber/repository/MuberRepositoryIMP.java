package bd2.Muber.repository;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Muber;

public class MuberRepositoryIMP extends GenericRepositoryIMP<Muber> {

	public MuberRepositoryIMP(SessionFactory sessionFactory) {
		super(sessionFactory, Muber.class);
	}
	
	public Muber getMuber(){
		return this.recuperar(1);
	}

}
