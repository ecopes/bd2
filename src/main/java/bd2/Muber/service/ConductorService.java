package bd2.Muber.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.transaction.annotation.Transactional;

import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Viaje;

@Transactional
public class ConductorService {
	
	private SessionFactory sessionFactory;
	
	public ConductorService(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
		
	}

	protected Session getSession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		@SuppressWarnings("deprecation")
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	@SuppressWarnings("unchecked")
	public List<Conductor> getTop10Conductors(){
		Session session = getCurrentSession();
		Query query = session.createQuery("from Conductor c");
		List<Conductor> conductoresTop10 = (List<Conductor>) query.list();
		Collections.sort(conductoresTop10, (c1, c2) -> compareTo(c1, c2));
		if (conductoresTop10.size()<=10){
			return conductoresTop10;
		}

		return conductoresTop10.subList(0, 10);

	}
	
	public double getCalificacion(Conductor conductor) {
		double calificacionTotal = 0;
		int cantidadTotal = 0;
		for (Viaje viaje : conductor.getViajes()) {
			List<Calificacion> calificaciones = (List<Calificacion>) viaje.getCalificaciones();
			for (Calificacion calificacion : calificaciones) {
				calificacionTotal = calificacionTotal + calificacion.getPuntaje();
				cantidadTotal++;
			}
		}
		if (cantidadTotal == 0){
			return 0;
		}
		return (calificacionTotal / cantidadTotal);
		
	}
	
	public int compareTo(Conductor c1, Conductor c2) {
		if (getCalificacion(c1) > getCalificacion(c2)) {
            return -1;
        }
        if (getCalificacion(c1) < getCalificacion(c2)) {
            return 1;
        }
        return 0;
	}
}

