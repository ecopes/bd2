package bd2.Muber.repository;

import java.util.List;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Conductor;

public class ConductorRepositoryImp extends GenericRepositoryImp<Conductor> {

	public ConductorRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory, Conductor.class);
	}

	public List<Conductor> getConductoresSinViajesAbiertos(){
		List<Conductor> conductores = this.recuperarTodos();
		conductores.removeIf(conductor -> conductor.getViajes().stream().anyMatch(viaje -> !viaje.isFinalizado()));
		return conductores;
	}
}