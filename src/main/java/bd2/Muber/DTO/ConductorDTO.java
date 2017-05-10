package bd2.Muber.DTO;

import java.text.Format;
import java.text.SimpleDateFormat;

import bd2.Muber.model.Conductor;
import bd2.Muber.serviceIMP.ServiceLocator;

public class ConductorDTO extends UsuarioDTO{

	private String fechaLicencia;
	private double calificacionPromedio;

	public ConductorDTO(Conductor conductor)
	{
		super(conductor);
		Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.fechaLicencia = formatter.format(conductor.getFechaLicencia());
		this.calificacionPromedio = ServiceLocator.getInstance().getConductorService().getCalificacion(conductor);
		
	}

	public String getFechaLicencia() {
		return fechaLicencia;
	}

	public void setFechaLicencia(String fechaLicencia) {
		this.fechaLicencia = fechaLicencia;
	}

	public double getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(double calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}
	
}