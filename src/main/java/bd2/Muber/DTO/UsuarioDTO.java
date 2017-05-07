package bd2.Muber.DTO;

import java.text.Format;
import java.text.SimpleDateFormat;

import bd2.Muber.model.Usuario;

public class UsuarioDTO {
	
	protected int idUsuario;
	protected String nombre;
	protected String fechaIngreso;
	
	public UsuarioDTO(Usuario usuario)
	{
		this.idUsuario = usuario.getIdUsuario();
		this.nombre = usuario.getNombre();
		Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.fechaIngreso = formatter.format(usuario.getFechaIngreso());
	}

}
