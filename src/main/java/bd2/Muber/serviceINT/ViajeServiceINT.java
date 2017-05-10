package bd2.Muber.serviceINT;

import java.util.List;

import bd2.Muber.DTO.ViajeDTO;

public interface ViajeServiceINT {
	
	List<ViajeDTO> getViajesAbiertos();
	boolean agregarViaje(String destino,String origen,int cantidadMaximaPasajeros,double costoTotal,int conductorID);
	boolean comentarViaje(int viajeID, int pasajeroID, double puntaje, String comentario);
	boolean agregarPasajero(int pasajeroID, int viajeID);
	boolean finalizarViaje(int viajeID);

}
