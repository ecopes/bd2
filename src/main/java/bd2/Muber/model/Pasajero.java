package bd2.Muber.model;

import java.util.Date;

public class Pasajero extends Usuario{
	
	private int credito;

	public Pasajero(){
		super();
		this.credito = 0;
	}
	
	public Pasajero(String nombre,String password,Date fechaIngreso,int credito){
		this();
		this.nombre = nombre;
		this.password = password;
		this.fechaIngreso = fechaIngreso;
		this.credito = credito;		
	}
	
	public int getCredito() {
		return credito;
	}
	
	public void setCredito(int credito) {
		this.credito = credito;
	}
}