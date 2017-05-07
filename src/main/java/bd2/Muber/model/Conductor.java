package bd2.Muber.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class Conductor extends Usuario {

	private Date fechaLicencia;
	private Collection<Viaje> viajes;
	
	public Conductor(){
		super();
		this.viajes = Collections.emptyList();
	}
	
	public Conductor(String nombre,String password,Date fechaIngreso,Date fechaLicencia){
		this();
		this.nombre = nombre;
		this.password = password;
		this.fechaIngreso = fechaIngreso;
		this.fechaLicencia = fechaLicencia;
		
	}

	public Date getFechaLicencia() {
		return fechaLicencia;
	}

	public void setFechaLicencia(Date fechaLicencia) {
		this.fechaLicencia = fechaLicencia;
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}
	public void addViaje(Viaje viaje) {
		this.viajes.add(viaje);
	}
}