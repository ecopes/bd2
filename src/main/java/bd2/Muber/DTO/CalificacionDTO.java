package bd2.Muber.DTO;

import bd2.Muber.model.Calificacion;

public class CalificacionDTO {
	
	private int idCalificacion;
	private String comentario;
	private double puntaje;
	private int pasajero;
	private int viaje;
	
	public CalificacionDTO(Calificacion calificacion){
		this.idCalificacion = calificacion.getIdCalificacion();
		this.comentario = calificacion.getComentario();
		this.puntaje = calificacion.getPuntaje();
		this.pasajero = calificacion.getPasajero().getIdUsuario();
		this.viaje = calificacion.getViaje().getIdViaje();
	}

	public int getIdCalificacion() {
		return idCalificacion;
	}

	public void setIdCalificacion(int idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public int getPasajero() {
		return pasajero;
	}

	public void setPasajero(int pasajero) {
		this.pasajero = pasajero;
	}

	public int getViaje() {
		return viaje;
	}

	public void setViaje(int viaje) {
		this.viaje = viaje;
	}
	

}
