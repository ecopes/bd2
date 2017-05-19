package bd2.Muber.repository;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Calificacion;

public class CalificacionRepositoryImp extends GenericRepositoryImp<Calificacion> {

	public CalificacionRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory,Calificacion.class);
	}

}
