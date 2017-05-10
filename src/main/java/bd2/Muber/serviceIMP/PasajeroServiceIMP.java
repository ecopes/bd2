package bd2.Muber.serviceIMP;

import java.util.ArrayList;
import java.util.List;

import bd2.Muber.DTO.PasajeroDTO;
import bd2.Muber.daoHibernateImp.DAOLocator;
import bd2.Muber.model.Pasajero;
import bd2.Muber.serviceINT.PasajeroServiceINT;

public class PasajeroServiceIMP implements PasajeroServiceINT{
	
	public List<PasajeroDTO> getPasajeros(){
		List<PasajeroDTO> pasajerosDTO = new ArrayList<PasajeroDTO>();
		for (Pasajero pasajero : DAOLocator.getInstance().getPasajeroDAO().recuperarTodos()) {
		 pasajerosDTO.add(new PasajeroDTO(pasajero));
		}
		return pasajerosDTO;
	}

	@Override
	public void cargarCredito(int pasajeroID,double monto) {
		Pasajero pasajero = DAOLocator.getInstance().getPasajeroDAO().recuperar(pasajeroID);
		pasajero.addCredito(monto);
		DAOLocator.getInstance().getPasajeroDAO().actualizar(pasajero);
	}
}