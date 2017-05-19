package bd2.Muber.repository;

public class RepositoryLocator {
	
	protected static RepositoryLocator instance = null;
	
	protected CalificacionRepositoryIMP calificacionRepository;
	protected ConductorRepositoryIMP conductorRepository;
	protected MuberRepositoryIMP muberRepository;
	protected PasajeroRepositoryIMP pasajeroRepository;
	protected ViajeRepositoryIMP viajeRepository;
	
	public static RepositoryLocator getInstance()
	{
		if (instance == null){
			instance = new RepositoryLocator();
		}
			return instance;
	}

	public RepositoryLocator() {
	}

	public CalificacionRepositoryIMP getCalificacionRepository() {
		return calificacionRepository;
	}

	public void setCalificacionRepository(CalificacionRepositoryIMP calificacionRepository) {
		this.calificacionRepository = calificacionRepository;
	}

	public ConductorRepositoryIMP getConductorRepository() {
		return conductorRepository;
	}

	public void setConductorRepository(ConductorRepositoryIMP conductorRepository) {
		this.conductorRepository = conductorRepository;
	}

	public MuberRepositoryIMP getMuberRepository() {
		return muberRepository;
	}

	public void setMuberRepository(MuberRepositoryIMP muberRepository) {
		this.muberRepository = muberRepository;
	}

	public PasajeroRepositoryIMP getPasajeroRepository() {
		return pasajeroRepository;
	}

	public void setPasajeroRepository(PasajeroRepositoryIMP pasajeroRepository) {
		this.pasajeroRepository = pasajeroRepository;
	}

	public ViajeRepositoryIMP getViajeRepository() {
		return viajeRepository;
	}

	public void setViajeRepository(ViajeRepositoryIMP viajeRepository) {
		this.viajeRepository = viajeRepository;
	}

	public static void setInstance(RepositoryLocator instance) {
		RepositoryLocator.instance = instance;
	}
	
}