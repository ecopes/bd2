package bd2.Muber.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Viaje{

	private int idViaje;
	private String destino;
	private String origen;
	private int cantidadMaximaPasajeros;
	private Date fecha;
	private float costoTotal;
	private Collection<Pasajero> pasajeros;
	private Collection<Calificacion> calificaciones;
	private Conductor conductor;
	private boolean finalizado;
	
	public Viaje(){
		this.finalizado = false;
		this.fecha = new Date();
	}
	
	public Viaje(String destino, String origen, int cantidadMaximaPasajeros,float costoTotal, Conductor conductor){
		this();
		this.destino = destino;
		this.origen = origen;
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
		this.costoTotal = costoTotal;
		this.conductor = conductor;
		this.pasajeros = new ArrayList<Pasajero>();
		this.calificaciones = new ArrayList<Calificacion>();
	}

	
	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public int getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}

	public void setCantidadMaximaPasajeros(int cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(float costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Collection<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(Collection<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	
	public boolean addPasajero(Pasajero pasajero){
		if (this.getCantidadMaximaPasajeros() > this.getPasajeros().size()){
			this.pasajeros.add(pasajero);
			return true;
		}
		return false;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Collection<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(Collection<Calificacion> calificaciones) {
		for (Calificacion calificacion : calificaciones) {
			calificacion.setViaje(this);
		}
		this.calificaciones = calificaciones;
	}
	
	public void addCalificacion(Calificacion calificacion){
		calificacion.setViaje(this);
		this.calificaciones.add(calificacion);
	}
	
}
