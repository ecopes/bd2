package bd2.Muber.RepositoryHibernateImp;

public class RepositoryLocator {
	
	protected static RepositoryLocator instance = null;
	
	protected CalificacionHibernateRepositoryImp calificacionRepository;
	protected ConductorHibernateRepositoryImp conductorRepository;
	protected MuberHibernateRepositoryImp muberRepository;
	protected PasajeroHibernateRepositoryImp pasajeroRepository;
	protected ViajeHibernateRepositoryImp viajeRepository;
	
	public static RepositoryLocator getInstance()
	{
		if (instance == null){
			instance = new RepositoryLocator();
		}
			return instance;
	}

	public RepositoryLocator() {
	}

	public CalificacionHibernateRepositoryImp getCalificacionRepository() {
		return calificacionRepository;
	}

	public void setCalificacionRepository(CalificacionHibernateRepositoryImp calificacionRepository) {
		this.calificacionRepository = calificacionRepository;
	}

	public ConductorHibernateRepositoryImp getConductorRepository() {
		return conductorRepository;
	}

	public void setConductorRepository(ConductorHibernateRepositoryImp conductorRepository) {
		this.conductorRepository = conductorRepository;
	}

	public MuberHibernateRepositoryImp getMuberRepository() {
		return muberRepository;
	}

	public void setMuberRepository(MuberHibernateRepositoryImp muberRepository) {
		this.muberRepository = muberRepository;
	}

	public PasajeroHibernateRepositoryImp getPasajeroRepository() {
		return pasajeroRepository;
	}

	public void setPasajeroRepository(PasajeroHibernateRepositoryImp pasajeroRepository) {
		this.pasajeroRepository = pasajeroRepository;
	}

	public ViajeHibernateRepositoryImp getViajeRepository() {
		return viajeRepository;
	}

	public void setViajeRepository(ViajeHibernateRepositoryImp viajeRepository) {
		this.viajeRepository = viajeRepository;
	}

	public static void setInstance(RepositoryLocator instance) {
		RepositoryLocator.instance = instance;
	}
	
}