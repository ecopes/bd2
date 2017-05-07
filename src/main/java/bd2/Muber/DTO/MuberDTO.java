package bd2.Muber.DTO;

import java.util.ArrayList;
import java.util.Collection;

import bd2.Muber.model.Muber;
import bd2.Muber.model.Usuario;
import bd2.Muber.model.Viaje;

public class MuberDTO {
	
	private Collection<Integer> viajes;
	private Collection<Integer> usuarios;
	
	public MuberDTO(Muber muber)
	{
		this.viajes = new ArrayList<Integer>();
		for (Viaje viaje : muber.getViajes()) {
			this.viajes.add(viaje.getIdViaje());
		}
		this.usuarios = new ArrayList<Integer>();
		for (Usuario usuario : muber.getUsuarios()) {
			this.usuarios.add(usuario.getIdUsuario());
		}
	}
}