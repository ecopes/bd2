package bd2.Muber.model;

import java.util.ArrayList;
import java.util.Collection;

public class Muber {
	
	private int idMuber;
	private Collection<Usuario> usuarios;
	private Collection<Viaje> viajes;
	
	public Muber(){
		this.usuarios = new ArrayList<Usuario>();
		this.viajes = new ArrayList<Viaje>();
	}
	
	
	public int getIdMuber() {
		return idMuber;
	}

	public void setIdMuber(int idMuber) {
		this.idMuber = idMuber;
	}


	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void addUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}
	
	public void addViaje(Viaje viaje){
		this.viajes.add(viaje);
	}
	
}
