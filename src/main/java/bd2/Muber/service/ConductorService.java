package bd2.Muber.service;

import java.util.List;

import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Viaje;

public class ConductorService {

	public float getCalificacionPromedio(Conductor conductor) {
		List<Viaje> viajes = (List<Viaje>) conductor.getViajes();
		float calificacionTotal = 0;
		int cantidadTotal = 0;
		for (Viaje viaje : viajes) {
			List<Calificacion> calificaciones = (List<Calificacion>) viaje.getCalificaciones();
			for (Calificacion calificacion : calificaciones) {
				calificacionTotal = calificacionTotal + calificacion.getPuntaje();
				cantidadTotal++;
			}
		}
		float promedioCalificaciones = calificacionTotal / cantidadTotal;
		return promedioCalificaciones;
	}
}
