package bd2.Muber.serviceIMP;

import java.util.ArrayList;
import java.util.List;

import bd2.Muber.DTO.ViajeDTO;
import bd2.Muber.RepositoryHibernateImp.RepositoryLocator;
import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;
import bd2.Muber.serviceINT.ViajeServiceINT;

public class ViajeServiceIMP implements ViajeServiceINT {

	public List<ViajeDTO> getViajesAbiertos(){
		List<ViajeDTO> viajesDTO = new ArrayList<ViajeDTO>();
		for (Viaje viaje : RepositoryLocator.getInstance().getViajeRepository().getViajesAbiertos()) {
			viajesDTO.add(new ViajeDTO(viaje));
		}
		return viajesDTO;
	}

	@Override
	public boolean agregarViaje(String destino, String origen, int cantidadMaximaPasajeros, double costoTotal,
			int conductorID) {
		Conductor conductor = RepositoryLocator.getInstance().getConductorRepository().recuperar(conductorID);
		Viaje viaje = new Viaje(destino,origen,cantidadMaximaPasajeros,costoTotal,conductor);
		conductor.addViaje(viaje);
		RepositoryLocator.getInstance().getMuberRepository().getMuber().addViaje(viaje);
		return true;
	}

	@Override
	public boolean comentarViaje(int viajeID, int pasajeroID, double puntaje, String comentario) {
		Viaje viaje = RepositoryLocator.getInstance().getViajeRepository().recuperar(viajeID);
		Pasajero pasajero = RepositoryLocator.getInstance().getPasajeroRepository().recuperar(pasajeroID);

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
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean agregarPasajero(int pasajeroID, int viajeID) {
		Pasajero pasajero = RepositoryLocator.getInstance().getPasajeroRepository().recuperar(pasajeroID);
		Viaje viaje = RepositoryLocator.getInstance().getViajeRepository().recuperar(viajeID);

		Boolean seAgregoPasajero = viaje.addPasajero(pasajero);

		if (seAgregoPasajero){
			return true;
		}
		return false;
	}

	@Override
	public boolean finalizarViaje(int viajeID) {
		Viaje viaje = RepositoryLocator.getInstance().getViajeRepository().recuperar(viajeID);
		if (!viaje.isFinalizado()){
			viaje.finalizar();
			return true;
		}
		return false;
	}
}