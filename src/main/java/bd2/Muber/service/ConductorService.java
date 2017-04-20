package bd2.Muber.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Viaje;

public class ConductorService {

	protected Session getSession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		@SuppressWarnings("deprecation")
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}


	public double getCalificacionPromedio(Conductor conductor) {
		List<Viaje> viajes = (List<Viaje>) conductor.getViajes();
		double calificacionTotal = 0;
		int cantidadTotal = 0;
		for (Viaje viaje : viajes) {
			List<Calificacion> calificaciones = (List<Calificacion>) viaje.getCalificaciones();
			for (Calificacion calificacion : calificaciones) {
				calificacionTotal = calificacionTotal + calificacion.getPuntaje();
				cantidadTotal++;
			}
		}
		if (cantidadTotal == 0){
			return 0;
		}
		return calificacionTotal / cantidadTotal;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Conductor> getTop10Conductors(){
		Session session = getSession();
		Query query = session.createQuery("from Conductor").setMaxResults(10);
		ArrayList<Conductor> conductoresTop10 = (ArrayList<Conductor>) query.list();
		Collections.sort(conductoresTop10,Comparators.comparar); 
		return conductoresTop10;
		
	}

	public static class Comparators {
		private static ConductorService conductorService = new ConductorService();
		public static Comparator<Conductor> comparar = new Comparator<Conductor>() {

			public int compare(Conductor c1, Conductor c2) {
				return (int) (conductorService.getCalificacionPromedio(c2) - conductorService.getCalificacionPromedio(c1)); 
			}
		};

	}
}

