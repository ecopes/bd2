package bd2.Muber.DTO;

import bd2.Muber.model.Pasajero;

public class PasajeroDTO extends UsuarioDTO{
	
	private double credito;
	
	public PasajeroDTO(Pasajero pasajero) {
		super(pasajero);
		this.credito = pasajero.getCredito();
	}

	public double getCredito() {
		return credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}
	
}