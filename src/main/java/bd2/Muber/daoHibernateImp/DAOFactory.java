package bd2.Muber.daoHibernateImp;

public class DAOFactory {
	
	protected static DAOFactory instance = null;
	
	protected CalificacionHibernateDaoImp calificacionDAO;
	protected ConductorHibernateDaoImp conductorDAO;
	protected MuberHibernateDaoImp muberDAO;
	protected PasajeroHibernateDaoImp pasajeroDAO;
	protected ViajeHibernateDaoImp viajeDAO;
	
	public static DAOFactory getInstance()
	{
		if (instance == null){
			instance = new DAOFactory();
		}
			return instance;
	}

	public DAOFactory() {
	}

	public CalificacionHibernateDaoImp getCalificacionDAO() {
		return calificacionDAO;
	}

	public void setCalificacionDAO(CalificacionHibernateDaoImp calificacionDAO) {
		this.calificacionDAO = calificacionDAO;
	}

	public ConductorHibernateDaoImp getConductorDAO() {
		return conductorDAO;
	}

	public void setConductorDAO(ConductorHibernateDaoImp conductorDAO) {
		this.conductorDAO = conductorDAO;
	}

	public MuberHibernateDaoImp getMuberDAO() {
		return muberDAO;
	}

	public void setMuberDAO(MuberHibernateDaoImp muberDAO) {
		this.muberDAO = muberDAO;
	}

	public PasajeroHibernateDaoImp getPasajeroDAO() {
		return pasajeroDAO;
	}

	public void setPasajeroDAO(PasajeroHibernateDaoImp pasajeroDAO) {
		this.pasajeroDAO = pasajeroDAO;
	}

	public ViajeHibernateDaoImp getViajeDAO() {
		return viajeDAO;
	}

	public void setViajeDAO(ViajeHibernateDaoImp viajeDAO) {
		this.viajeDAO = viajeDAO;
	}
	
}
