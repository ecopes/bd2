package bd2.Muber.model;

public class Calificacion{

	private int idCalificacion;
	private String comentario;
	private double puntaje;
	private Pasajero pasajero;
	private Viaje viaje;
	
	public Calificacion(){
		
	}
	
	public Calificacion(String comentario, double puntaje, Pasajero pasajero){
		this.comentario = comentario;
		this.puntaje = puntaje;
		this.pasajero = pasajero;
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

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}	
}