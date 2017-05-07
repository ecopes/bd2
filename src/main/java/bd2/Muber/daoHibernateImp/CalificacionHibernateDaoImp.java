package bd2.Muber.daoHibernateImp;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Calificacion;

public class CalificacionHibernateDaoImp extends GenericDaoHibernateImp<Calificacion> {

	public CalificacionHibernateDaoImp(SessionFactory sessionFactory) {
		super(sessionFactory,Calificacion.class);
	}

}
