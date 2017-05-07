package bd2.Muber.service;

public class ServiceFactory {
	
	protected static ServiceFactory instance = null;
	
	protected ConductorService conductorService;
	
	public static ServiceFactory getInstance()
	{
		if (instance == null){
			instance = new ServiceFactory();
		}
			return instance;
	}
	
	public ServiceFactory(){}

	public ConductorService getConductorService() {
		return conductorService;
	}

	public void setConductorService(ConductorService conductorService) {
		this.conductorService = conductorService;
	}	
}