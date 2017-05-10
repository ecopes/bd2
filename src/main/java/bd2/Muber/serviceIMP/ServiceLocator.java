package bd2.Muber.serviceIMP;

import bd2.Muber.serviceINT.ConductorServiceINT;
import bd2.Muber.serviceINT.PasajeroServiceINT;
import bd2.Muber.serviceINT.ViajeServiceINT;

public class ServiceLocator {
	
	protected static ServiceLocator instance = null;
	
	protected ConductorServiceINT conductorService;
	protected PasajeroServiceINT pasajeroService;
	protected ViajeServiceINT viajeService;
	
	public static ServiceLocator getInstance()
	{
		if (instance == null){
			instance = new ServiceLocator();
		}
			return instance;
	}
	
	public ServiceLocator(){}

	public ConductorServiceINT getConductorService() {
		return conductorService;
	}

	public void setConductorService(ConductorServiceINT conductorService) {
		this.conductorService = conductorService;
	}

	public static void setInstance(ServiceLocator instance) {
		ServiceLocator.instance = instance;
	}

	public PasajeroServiceINT getPasajeroService() {
		return pasajeroService;
	}

	public void setPasajeroService(PasajeroServiceINT pasajeroService) {
		this.pasajeroService = pasajeroService;
	}

	public ViajeServiceINT getViajeService() {
		return viajeService;
	}

	public void setViajeService(ViajeServiceINT viajeService) {
		this.viajeService = viajeService;
	}	
	
}