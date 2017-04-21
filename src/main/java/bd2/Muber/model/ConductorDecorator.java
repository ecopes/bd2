package bd2.Muber.model;

import java.util.List;

public class ConductorDecorator extends Conductor implements Comparable<ConductorDecorator>{
	private double calificacion;

	public ConductorDecorator(){
	}
	
	public ConductorDecorator(Conductor conductor){
		super(conductor.getNombre(), conductor.getPassword(), conductor.getFechaIngreso(), conductor.getFechaLicencia());
		this.setViajes(conductor.getViajes());
		this.getCalificacion();
	}
	public double getCalificacion() {
		double calificacionTotal = 0;
		int cantidadTotal = 0;
		for (Viaje viaje : this.getViajes()) {
			List<Calificacion> calificaciones = (List<Calificacion>) viaje.getCalificaciones();
			for (Calificacion calificacion : calificaciones) {
				calificacionTotal = calificacionTotal + calificacion.getPuntaje();
				cantidadTotal++;
			}
		}
		if (cantidadTotal == 0){
			return 0;
		}
		this.setCalificacion(calificacionTotal / cantidadTotal);
		return this.calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public int compareTo(ConductorDecorator c) {
		if (this.getCalificacion() > c.getCalificacion()) {
            return -1;
        }
        if (this.getCalificacion() < c.getCalificacion()) {
            return 1;
        }
        return 0;
	}

}