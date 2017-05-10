package bd2.Muber.serviceINT;

import java.util.List;

import bd2.Muber.DTO.ConductorDTO;
import bd2.Muber.model.Conductor;

public interface ConductorServiceINT {
		int compareTo(Conductor c1,Conductor c2);
		double getCalificacion(Conductor c);
		List<ConductorDTO> getTop10Conductors();
		List<ConductorDTO> getConductores();
		ConductorDTO getConductor(int idConductor);
}
