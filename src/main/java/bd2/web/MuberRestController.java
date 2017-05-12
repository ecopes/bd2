package bd2.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bd2.Muber.serviceIMP.ServiceLocator;

@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {
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

	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getPasajeroService().getPasajeros());
	}

	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getConductorService().getConductores());
	}

	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String viajesAbiertos() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().getViajesAbiertos());
	}

	@RequestMapping(value = "/conductores/detalle/", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresDetalle(@RequestParam(value="conductorId") int id) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getConductorService().getConductor(id));
	}

	@RequestMapping(value = "/viajes/nuevo/", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String viajesNuevo(
			@RequestParam(value="conductorId") int conductorID
			,@RequestParam(value="origen") String origen
			,@RequestParam(value="destino") String destino
			,@RequestParam(value="costoTotal") float costoTotal
			,@RequestParam(value="cantidadPasajeros") int cantPasajeros){

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().agregarViaje(destino, origen, cantPasajeros, costoTotal, conductorID));
	}

	@RequestMapping(value = "/viajes/calificar/", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String viajesCalificar(
			@RequestParam(value="viajeId") int viajeID
			,@RequestParam(value="pasajeroId") int pasajeroID
			,@RequestParam(value="puntaje") double puntaje
			,@RequestParam(value="comentario") String comentario){

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().comentarViaje(viajeID, pasajeroID, puntaje, comentario));
	}

	@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresTop10() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getConductorService().getTop10Conductors());
	}

	@RequestMapping(value = "/viajes/agregarPasajero", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarPasajero(@RequestParam int pasajeroId, @RequestParam int viajeId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().agregarPasajero(pasajeroId, viajeId));
	}

	@RequestMapping(value = "/pasajeros/cargarCredito", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarCreditoAPasajero(@RequestParam int pasajeroId, @RequestParam double monto) {
		
		ServiceLocator.getInstance().getPasajeroService().cargarCredito(pasajeroId, monto);
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Respuesta","Se agrego el credito");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String finalizarViaje(@RequestParam int viajeId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().finalizarViaje(viajeId));
	}
	
}