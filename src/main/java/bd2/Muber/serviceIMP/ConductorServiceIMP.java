package bd2.Muber.serviceIMP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bd2.Muber.DTO.ConductorDTO;
import bd2.Muber.daoHibernateImp.DAOLocator;
import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Viaje;
import bd2.Muber.serviceINT.ConductorServiceINT;

@Transactional
public class ConductorServiceIMP implements ConductorServiceINT{

	public ConductorServiceIMP()
	{
	}
	public List<ConductorDTO> getTop10Conductors(){
		List<Conductor> conductoresTop10 = DAOLocator.getInstance().getConductorDAO().recuperarTodos();
		Collections.sort(conductoresTop10, (c1, c2) -> compareTo(c1, c2));
		List<ConductorDTO> conductoresDTOTop10 = new ArrayList<ConductorDTO>();
		if (conductoresTop10.size()<=10){
			for (Conductor conductor : conductoresTop10) {
				conductoresDTOTop10.add(new ConductorDTO(conductor));
			}
		}else{
			for (Conductor conductor : conductoresTop10.subList(0, 10)) {
				conductoresDTOTop10.add(new ConductorDTO(conductor));
			}
		}
		return conductoresDTOTop10;
	}

	public double getCalificacion(Conductor conductor) {
		double calificacionTotal = 0;
		int cantidadTotal = 0;
		for (Viaje viaje : conductor.getViajes()) {
			List<Calificacion> calificaciones = (List<Calificacion>) viaje.getCalificaciones();
			for (Calificacion calificacion : calificaciones) {
				calificacionTotal = calificacionTotal + calificacion.getPuntaje();
				cantidadTotal++;
			}
		}
		if (cantidadTotal == 0){
			return 0;
		}
		return (calificacionTotal / cantidadTotal);

	}

	public int compareTo(Conductor c1, Conductor c2) {
		if (getCalificacion(c1) > getCalificacion(c2)) {
			return -1;
		}
		if (getCalificacion(c1) < getCalificacion(c2)) {
			return 1;
		}
		return 0;
	}

	public List<ConductorDTO> getConductores() {
		List<ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();  
		for (Conductor conductor : DAOLocator.getInstance().getConductorDAO().recuperarTodos()) {
			conductoresDTO.add(new ConductorDTO(conductor));
		}
		return conductoresDTO;
	}
	@Override
	public ConductorDTO getConductor(int idConductor) {
		try{
			ConductorDTO conductorDTO = new ConductorDTO(DAOLocator.getInstance().getConductorDAO().recuperar(idConductor));
			return conductorDTO;
		}catch (NullPointerException e){
			return null;
		}
	}
}

