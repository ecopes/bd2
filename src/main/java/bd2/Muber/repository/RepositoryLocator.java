package bd2.Muber.repository;

public class RepositoryLocator {
	
	protected static RepositoryLocator instance = null;
	
	protected CalificacionRepositoryImp calificacionRepository;
	protected ConductorRepositoryImp conductorRepository;
	protected MuberRepositoryImp muberRepository;
	protected PasajeroRepositoryImp pasajeroRepository;
	protected ViajeRepositoryImp viajeRepository;
	
	public static RepositoryLocator getInstance()
	{
		if (instance == null){
			instance = new RepositoryLocator();
		}
			return instance;
	}

	public RepositoryLocator() {
	}

	public CalificacionRepositoryImp getCalificacionRepository() {
		return calificacionRepository;
	}

	public void setCalificacionRepository(CalificacionRepositoryImp calificacionRepository) {
		this.calificacionRepository = calificacionRepository;
	}

	public ConductorRepositoryImp getConductorRepository() {
		return conductorRepository;
	}

	public void setConductorRepository(ConductorRepositoryImp conductorRepository) {
		this.conductorRepository = conductorRepository;
	}

	public MuberRepositoryImp getMuberRepository() {
		return muberRepository;
	}

	public void setMuberRepository(MuberRepositoryImp muberRepository) {
		this.muberRepository = muberRepository;
	}

	public PasajeroRepositoryImp getPasajeroRepository() {
		return pasajeroRepository;
	}

	public void setPasajeroRepository(PasajeroRepositoryImp pasajeroRepository) {
		this.pasajeroRepository = pasajeroRepository;
	}

	public ViajeRepositoryImp getViajeRepository() {
		return viajeRepository;
	}

	public void setViajeRepository(ViajeRepositoryImp viajeRepository) {
		this.viajeRepository = viajeRepository;
	}

	public static void setInstance(RepositoryLocator instance) {
		RepositoryLocator.instance = instance;
	}
	
}