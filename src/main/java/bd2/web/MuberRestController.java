package bd2.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String viajesNuevo(@RequestBody Map<String,String> viaje){
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().agregarViaje(
				viaje.get("destino"), 
				viaje.get("origen"), 
				Integer.parseInt(viaje.get("cantidadPasajeros")), 
				Double.parseDouble(viaje.get("costoTotal")), 
				Integer.parseInt(viaje.get("conductorId"))));
	}

	@RequestMapping(value = "/viajes/calificar/", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String viajesCalificar(@RequestBody Map<String,String> calificacion){

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().comentarViaje(
				Integer.parseInt(calificacion.get("viajeId")), 
				Integer.parseInt(calificacion.get("pasajeroId")), 
				Double.parseDouble(calificacion.get("puntaje")), 
				calificacion.get("comentario")));
	}

	@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresTop10() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getConductorService().getTop10Conductors());
	}

	@RequestMapping(value = "/viajes/agregarPasajero", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarPasajero(@RequestBody Map<String,String> pasajeroViaje) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().agregarPasajero(
				Integer.parseInt(pasajeroViaje.get("pasajeroId")), 
				Integer.parseInt(pasajeroViaje.get("viajeId"))));
	}

	@RequestMapping(value = "/pasajeros/cargarCredito", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarCreditoAPasajero(@RequestBody Map<String,String> credito) {		
		ServiceLocator.getInstance().getPasajeroService().cargarCredito(
				Integer.parseInt(credito.get("pasajeroId")), 
				Double.parseDouble(credito.get("monto")));
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Respuesta","Se agrego el credito");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String finalizarViaje(@RequestBody Map<String,String> viaje) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(ServiceLocator.getInstance().getViajeService().finalizarViaje(Integer.parseInt(viaje.get("viajeId"))));
	}
	
}