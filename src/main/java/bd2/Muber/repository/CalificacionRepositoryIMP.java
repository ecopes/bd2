package bd2.Muber.repository;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Calificacion;

public class CalificacionRepositoryIMP extends GenericRepositoryIMP<Calificacion> {

	public CalificacionRepositoryIMP(SessionFactory sessionFactory) {
		super(sessionFactory,Calificacion.class);
	}

}
