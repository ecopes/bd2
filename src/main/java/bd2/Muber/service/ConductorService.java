package bd2.Muber.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
		Query query = session.createQuery("select new bd2.Muber.model.ConductorDecorator(c) from Conductor c");
		List<ConductorDecorator> conductoresTop10 = (List<ConductorDecorator>) query.list();
		Collections.sort(conductoresTop10);
		if (conductoresTop10.size()<=10){
			return conductoresTop10;
		}

		return conductoresTop10.subList(0, 10);

	}
}

