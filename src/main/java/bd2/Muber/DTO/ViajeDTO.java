package bd2.Muber.DTO;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;

public class ViajeDTO {
	private int idViaje;
	private String destino;
	private String origen;
	private int cantidadMaximaPasajeros;
	private String fecha;
	private double costoTotal;
	private Collection<Integer> pasajeros;
	private Collection<String> calificaciones;
	private ConductorDTO conductor;
	private boolean finalizado;

	public ViajeDTO(Viaje viaje)
	{
		this.idViaje = viaje.getIdViaje();
		this.destino = viaje.getDestino();
		this.origen = viaje.getOrigen();
		this.cantidadMaximaPasajeros = viaje.getCantidadMaximaPasajeros();
		Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.fecha = formatter.format(viaje.getFecha());
		this.costoTotal = viaje.getCostoTotal();
		this.pasajeros = new ArrayList<Integer>();
		for (Pasajero pasajero : viaje.getPasajeros()){
			this.pasajeros.add(pasajero.getIdUsuario());
		}
		this.calificaciones = new ArrayList<String>();
		for (Calificacion calificacion : viaje.getCalificaciones()) {
			this.calificaciones.add(calificacion.getComentario());
		}
		this.conductor = new ConductorDTO(viaje.getConductor());
		this.finalizado = viaje.isFinalizado();
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Collection<Integer> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(Collection<Integer> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public Collection<String> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(Collection<String> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public ConductorDTO getConductor() {
		return conductor;
	}

	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
}
