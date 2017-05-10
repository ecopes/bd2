package bd2.Muber.serviceIMP;

import java.util.ArrayList;
import java.util.List;

import bd2.Muber.DTO.ViajeDTO;
import bd2.Muber.daoHibernateImp.DAOLocator;
import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;
import bd2.Muber.serviceINT.ViajeServiceINT;

public class ViajeServiceIMP implements ViajeServiceINT {

	public List<ViajeDTO> getViajesAbiertos(){
		List<ViajeDTO> viajesDTO = new ArrayList<ViajeDTO>();
		for (Viaje viaje : DAOLocator.getInstance().getViajeDAO().getViajesAbiertos()) {
			viajesDTO.add(new ViajeDTO(viaje));
		}
		return viajesDTO;
	}

	@Override
	public boolean agregarViaje(String destino, String origen, int cantidadMaximaPasajeros, double costoTotal,
			int conductorID) {
		Conductor conductor = DAOLocator.getInstance().getConductorDAO().recuperar(conductorID);
		Viaje viaje = new Viaje(destino,origen,cantidadMaximaPasajeros,costoTotal,conductor);
		conductor.addViaje(viaje);
		DAOLocator.getInstance().getViajeDAO().persistir(viaje);
		return true;
	}

	@Override
	public boolean comentarViaje(int viajeID, int pasajeroID, double puntaje, String comentario) {
		Viaje viaje = DAOLocator.getInstance().getViajeDAO().recuperar(viajeID);
		Pasajero pasajero = DAOLocator.getInstance().getPasajeroDAO().recuperar(pasajeroID);

		Calificacion calificacion = new Calificacion(comentario,puntaje,pasajero);

		// me fijo que no haya calificado ya el viaje
		boolean yaCalifico = false;
		for (Calificacion calificacionAux : viaje.getCalificaciones()) {
			if (calificacionAux.getPasajero().getIdUsuario() == pasajero.getIdUsuario()){
				yaCalifico = true;
			}
		}
		// me fijo si el pasajero es pasajero del viaje y que no califico
		if (viaje.getPasajeros().contains(pasajero) && !yaCalifico){
			viaje.addCalificacion(calificacion);
			DAOLocator.getInstance().getViajeDAO().actualizar(viaje);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean agregarPasajero(int pasajeroID, int viajeID) {
		Pasajero pasajero = DAOLocator.getInstance().getPasajeroDAO().recuperar(pasajeroID);
		Viaje viaje = DAOLocator.getInstance().getViajeDAO().recuperar(viajeID);

		Boolean seAgregoPasajero = viaje.addPasajero(pasajero);

		if (seAgregoPasajero){
			DAOLocator.getInstance().getViajeDAO().actualizar(viaje);
			return true;
		}
		return false;
	}

	@Override
	public boolean finalizarViaje(int viajeID) {
		Viaje viaje = DAOLocator.getInstance().getViajeDAO().recuperar(viajeID);
		if (!viaje.isFinalizado()){
			viaje.finalizar();
			DAOLocator.getInstance().getViajeDAO().actualizar(viaje);
			return true;
		}
		return false;
	}
}