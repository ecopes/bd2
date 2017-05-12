package bd2.Muber.RepositoryHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Calificacion;

public class CalificacionHibernateRepositoryImp extends GenericRepositoryHibernateImp<Calificacion> {

	public CalificacionHibernateRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory,Calificacion.class);
	}

}
