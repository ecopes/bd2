package bd2.Muber.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bd2.Muber.model.Conductor;
import bd2.Muber.model.ConductorDecorator;

public class ConductorService {

	protected Session getSession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		@SuppressWarnings("deprecation")
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	@SuppressWarnings("unchecked")
	public List<ConductorDecorator> getTop10Conductors(){
		Session session = getSession();
		Query query = session.createQuery("from Conductor");
		List<Conductor> conductoresTop10 = (List<Conductor>) query.list();
		ArrayList<ConductorDecorator> conductoresDecorator = new ArrayList<ConductorDecorator>();
		for (Conductor conductorAux : conductoresTop10) {
			ConductorDecorator conductor = new ConductorDecorator(conductorAux);
			conductoresDecorator.add(conductor);
		}
		Collections.sort(conductoresDecorator);
		if (conductoresDecorator.size()<=10){
			return conductoresDecorator;
		}

		return conductoresDecorator.subList(0, 10);

	}
}

