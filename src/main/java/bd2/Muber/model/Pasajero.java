package bd2.Muber.model;

import java.util.Date;

public class Pasajero extends Usuario{
	
	private double credito;

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
	
	public double getCredito() {
		return credito;
	}
	
	public void setCredito(double credito) {
		this.credito = credito;
	}
	
	public void addCredito(double credito){
		this.credito += credito;
	}
	
	public boolean subtractCredito(double credito){ 
		if ((this.credito - credito) >= 0 ){
			this.credito -= credito;
			return true;
		}else{
			return false;
		}
	}
}