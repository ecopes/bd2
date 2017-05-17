package bd2.Muber.RepositoryHibernateImp;

import java.util.List;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Conductor;

public class ConductorHibernateRepositoryImp extends GenericRepositoryHibernateImp<Conductor> {

	public ConductorHibernateRepositoryImp(SessionFactory sessionFactory) {
		super(sessionFactory, Conductor.class);
	}

	public List<Conductor> getConductoresSinViajesAbiertos(){
		List<Conductor> conductores = this.recuperarTodos();
		conductores.removeIf(conductor -> conductor.getViajes().stream().anyMatch(viaje -> !viaje.isFinalizado()));
		return conductores;
	}
}