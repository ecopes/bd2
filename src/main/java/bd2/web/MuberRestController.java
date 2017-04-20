package bd2.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;
import bd2.Muber.service.ConductorService;

@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {

	protected Session getSession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		@SuppressWarnings("deprecation")
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	@RequestMapping(value = "/listarWS", method = RequestMethod.GET)
	public String example() {
		String pagina = "<!DOCTYPE html><html><head><title>Listado de los ws</title></head><body>"
				+"<br><h3>Listar todos los pasajeros registrados en Muber:</h3>"
				+"<a href='http://127.0.0.1:8080/MuberRESTful/rest/services/pasajeros'>Listar</a><br>"

				+"<br><h3>Listar todos los conductores registrados en Muber:</h3>"
				+"<a href='http://127.0.0.1:8080/MuberRESTful/rest/services/conductores'>Listar</a><br>"

				+"<br><h3>Listar todos los viajes abiertos en Muber:</h3>"
				+"<a href='http://127.0.0.1:8080/MuberRESTful/rest/services/viajes/abiertos'>Listar</a><br>"

				+"<br><h3>Obtener la información de un conductor:</h3>"
				+ "<form action='http://127.0.0.1:8080/MuberRESTful/rest/services/conductores/detalle/' method='get'>"
				+ "<input type='text' name='conductorId' placeholder='ID del conductor' required><br>"
				+ "<input type='submit' value='Enviar'></form> "

				+ "<br><h3>Crear Viaje:</h3>"
				+ "<form action='http://127.0.0.1:8080/MuberRESTful/rest/services/viajes/nuevo/' method='post'>"
				+ "<input type='text' name='origen' placeholder='Origen' required><br>"
				+ "<input type='text' name='destino' placeholder='Destino' required><br>"
				+ "<input type='number' name='conductorId' placeholder='ID del conductor' required><br>"
				+ "<input type='number' step='0.01' name='costoTotal' placeholder='Costo total' required><br>"
				+ "<input type='number' name='cantidadPasajeros' placeholder='Cantidad de pasajeros' required><br>"
				+ "<input type='submit' value='Enviar'></form> "

				+ "<br><h3>Crear una calificación de un pasajero para un viaje en particular:</h3>"
				+ "<form action='http://127.0.0.1:8080/MuberRESTful/rest/services/viajes/calificar/' method='post'>"
				+ "<input type='number' name='viajeId' placeholder='ID del Viaje' required><br>"
				+ "<input type='number' name='pasajeroId' placeholder='ID del Pasajero' required><br>"
				+ "<input type='number' step='0.01' name='puntaje' placeholder='Puntaje' required><br>"
				+ "<input type='text' name='comentario' placeholder='Comentario' required><br>"
				+ "<input type='submit' value='Enviar'></form> "

				+"<br><h3>Listar los 10 conductores mejor calificados que no tengan viajes abiertos registrados:</h3>"
				+"<a href='http://127.0.0.1:8080/MuberRESTful/rest/services/conductores/top10'>Listar</a><br>"

				+"</body><html>";

		return pagina;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		Criteria criteria  = this.getSession().createCriteria(Pasajero.class);
		Map<String, Object> aMap = new HashMap<String, Object>();
		List<Pasajero> pasajeros = criteria.list();
		for (Pasajero pasajero : pasajeros) {
			aMap.put(Integer.toString(pasajero.getIdUsuario()),pasajero.getNombre());
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		Criteria criteria  = this.getSession().createCriteria(Conductor.class);
		Map<String, Object> aMap = new HashMap<String, Object>();
		List<Conductor> conductores = criteria.list();
		for (Conductor conductor : conductores) {
			aMap.put(Integer.toString(conductor.getIdUsuario()),conductor.getNombre());
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String viajesAbiertos() {
		Query query = getSession().createQuery("from Viaje where finalizado = false");
		@SuppressWarnings("unchecked")
		List<Viaje> list = (List<Viaje>) query.list();
		Map<String, Object> aMap = new HashMap<String, Object>();
		int i = 1;
		for (Viaje viaje : list) {
			aMap.put("Viaje",Integer.toString(i));
			aMap.put("Destino",viaje.getDestino());
			aMap.put("Origen",viaje.getOrigen());
			aMap.put("Cantidad maxima pasajeros",viaje.getCantidadMaximaPasajeros());
			aMap.put("Fecha",viaje.getFecha());
			aMap.put("Costo total",viaje.getCostoTotal());
			aMap.put("Calificacion promedio conductor",(new ConductorService().getCalificacionPromedio(viaje.getConductor())));
			i++;
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/conductores/detalle/", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresDetalle(@RequestParam(value="conductorId") int id) {
		Conductor conductor = (Conductor) getSession().get(Conductor.class,id);
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Nombre de Usuario", conductor.getNombre());
		aMap.put("Viajes realizados", conductor.getViajes().size());
		aMap.put("Puntaje promedio", (new ConductorService().getCalificacionPromedio(conductor)));
		aMap.put("Fecha de Licencia", conductor.getFechaLicencia());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/viajes/nuevo/", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String viajesNuevo(
			@RequestParam(value="conductorId") int conductorID
			,@RequestParam(value="origen") String origen
			,@RequestParam(value="destino") String destino
			,@RequestParam(value="costoTotal") float costoTotal
			,@RequestParam(value="cantidadPasajeros") int cantPasajeros){

		Session session = getSession();
		Conductor conductor = (Conductor) session.get(Conductor.class,conductorID);
		Viaje viaje = new Viaje(destino,origen,cantPasajeros,costoTotal,conductor);
		conductor.addViaje(viaje);
		Transaction tx = session.beginTransaction();
		session.persist(viaje);
		tx.commit();

		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Response", "Viaje generado con exito");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/viajes/calificar/", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String viajesCalificar(
			@RequestParam(value="viajeId") int viajeID
			,@RequestParam(value="pasajeroId") int pasajeroID
			,@RequestParam(value="puntaje") double puntaje
			,@RequestParam(value="comentario") String comentario){

		Session session = getSession();
		Viaje viaje = (Viaje) session.get(Viaje.class, viajeID);
		Pasajero pasajero = (Pasajero) session.get(Pasajero.class, pasajeroID);

		Calificacion calificacion = new Calificacion(comentario,puntaje,pasajero);
		viaje.addCalificacion(calificacion);

		Transaction tx = session.beginTransaction();
		session.merge(viaje);
		tx.commit();

		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Response", "Calificacion agregada con exito");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresTop10() {
		ArrayList<Conductor> conductoresTop10 = (new ConductorService().getTop10Conductors());
		Map<String, Object> aMap2 = new HashMap<String, Object>();
		int i = 1;
		for (Conductor conductor : conductoresTop10) {
			Map<String, Object> aMap = new HashMap<String, Object>();
			aMap.put("Nombre de Usuario", conductor.getNombre());
			aMap.put("Viajes realizados", conductor.getViajes().size());
			aMap.put("Puntaje promedio", (new ConductorService().getCalificacionPromedio(conductor)));
			aMap.put("Fecha de Licencia", conductor.getFechaLicencia());
			aMap2.put("Puesto : "+Integer.toString(i),aMap);
			i++;
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap2);
	}
}