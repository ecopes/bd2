package bd2.Muber.serviceINT;

import java.util.List;

import bd2.Muber.DTO.PasajeroDTO;

public interface PasajeroServiceINT {
	
	List<PasajeroDTO> getPasajeros();
	void cargarCredito(int pasajeroID,double monto);
}