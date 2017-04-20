package bd2.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
				+ "<form action='http://127.0.0.1:8080/MuberRESTful/rest/services/----------TODO----------' method='post'>"
				+ "<input type='text' name='origen' placeholder='Origen' required><br>"
				+ "<input type='text' name='destino' placeholder='Destino' required><br>"
				+ "<input type='number' name='conductorId' placeholder='ID del conductor' required><br>"
				+ "<input type='number' step='0.01' name='costoTotal' placeholder='Costo total' required><br>"
				+ "<input type='number' name='cantidadPasajeros' placeholder='Cantidad de pasajeros' required><br>"
				+ "<input type='submit' value='Enviar'></form> "

				+ "<br><h3>Crear una calificación de un pasajero para un viaje en particular:</h3>"
				+ "<form action='http://127.0.0.1:8080/MuberRESTful/rest/services/----------TODO----------' method='post'>"
				+ "<input type='number' name='viajeId' placeholder='ID del Viaje' required><br>"
				+ "<input type='number' name='pasajeroId' placeholder='ID del Pasajero' required><br>"
				+ "<input type='number' step='0.01' name='puntaje' placeholder='Puntaje' required><br>"
				+ "<input type='text' name='comentario' placeholder='Comentario' required><br>"
				+ "<input type='submit' value='Enviar'></form> "
				
				+"<br><h3>Listar los 10 conductores mejor calificados que no tengan viajes abiertos registrados:</h3>"
				+"<a href='http://127.0.0.1:8080/MuberRESTful/rest/services/----------TODO----------'>Listar</a><br>"

				+"</body><html>";
		
		return pagina;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		Criteria criteria  = this.getSession().createCriteria(Pasajero.class);
		Map<String, Object> aMap = new HashMap<String, Object>();
		List<Pasajero> pasajeros = criteria.list();
		int i = 1;
		for (Pasajero pasajero : pasajeros) {
			aMap.put(Integer.toString(i),pasajero.getNombre());
			i++;
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
		int i = 1;
		for (Conductor conductor : conductores) {
			aMap.put(Integer.toString(i),conductor.getNombre());
			i++;
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
	public String conductorDetalle(@RequestParam(value="conductorId") int id) {
		Conductor conductor = (Conductor) getSession().get(Conductor.class,id);
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Nombre de Usuario", conductor.getNombre());
		aMap.put("Viajes realizados", conductor.getViajes().size());
		aMap.put("Puntaje promedio", (new ConductorService().getCalificacionPromedio(conductor)));
		aMap.put("Fecha de Licencia", conductor.getFechaLicencia());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}


}