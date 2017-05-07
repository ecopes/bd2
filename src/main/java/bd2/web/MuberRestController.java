package bd2.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bd2.Muber.DTO.ConductorDTO;
import bd2.Muber.DTO.PasajeroDTO;
import bd2.Muber.DTO.ViajeDTO;
import bd2.Muber.daoHibernateImp.DAOFactory;
import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;
import bd2.Muber.service.ServiceFactory;

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
		List<PasajeroDTO> pasajerosDTO = new ArrayList<PasajeroDTO>();
		for (Pasajero pasajero : DAOFactory.getInstance().getPasajeroDAO().recuperarTodos()) {
		 pasajerosDTO.add(new PasajeroDTO(pasajero));
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(pasajerosDTO);
	}

	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		List<ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();  
		for (Conductor conductor : DAOFactory.getInstance().getConductorDAO().recuperarTodos()) {
			conductoresDTO.add(new ConductorDTO(conductor));
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(conductoresDTO);
	}

	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String viajesAbiertos() {
		
		List<ViajeDTO> viajesDTO = new ArrayList<ViajeDTO>();
		for (Viaje viaje : DAOFactory.getInstance().getViajeDAO().getViajesAbiertos()) {
			viajesDTO.add(new ViajeDTO(viaje));
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(viajesDTO);
	}

	@RequestMapping(value = "/conductores/detalle/", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresDetalle(@RequestParam(value="conductorId") int id) {
		ConductorDTO conductorDTO = new ConductorDTO(DAOFactory.getInstance().getConductorDAO().recuperar(id));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(conductorDTO);
	}

	@RequestMapping(value = "/viajes/nuevo/", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String viajesNuevo(
			@RequestParam(value="conductorId") int conductorID
			,@RequestParam(value="origen") String origen
			,@RequestParam(value="destino") String destino
			,@RequestParam(value="costoTotal") float costoTotal
			,@RequestParam(value="cantidadPasajeros") int cantPasajeros){

		Conductor conductor = DAOFactory.getInstance().getConductorDAO().recuperar(conductorID);
		Viaje viaje = new Viaje(destino,origen,cantPasajeros,costoTotal,conductor);
		conductor.addViaje(viaje);
		DAOFactory.getInstance().getViajeDAO().persistir(viaje);
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

		Viaje viaje = DAOFactory.getInstance().getViajeDAO().recuperar(viajeID);
		Pasajero pasajero = DAOFactory.getInstance().getPasajeroDAO().recuperar(pasajeroID);

		Calificacion calificacion = new Calificacion(comentario,puntaje,pasajero);

		viaje.addCalificacion(calificacion);
		
		DAOFactory.getInstance().getViajeDAO().actualizar(viaje);
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Response", "Calificacion agregada con exito");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresTop10() {
		List<Conductor> conductoresTop10 = ServiceFactory.getInstance().getConductorService().getTop10Conductors();
		List<ConductorDTO> conductoresDTOTop10 = new ArrayList<ConductorDTO>();
		for (Conductor conductor : conductoresTop10) {
			conductoresDTOTop10.add(new ConductorDTO(conductor));
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(conductoresDTOTop10);
	}

	@RequestMapping(value = "/viajes/agregarPasajero", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarPasajero(@RequestParam int pasajeroId, @RequestParam int viajeId) {

		Pasajero pasajero = DAOFactory.getInstance().getPasajeroDAO().recuperar(pasajeroId);
		Viaje viaje = DAOFactory.getInstance().getViajeDAO().recuperar(viajeId);

		Boolean seAgregoPasajero = viaje.addPasajero(pasajero);
		
		Map<String, Object> aMap = new HashMap<String, Object>();
		if (!viaje.isFinalizado()){
			if (seAgregoPasajero){
				DAOFactory.getInstance().getViajeDAO().actualizar(viaje);
				aMap.put("Respuesta","Se pudo agregar el pasajero");
			}else{
				aMap.put("Respuesta","No se pudo agregar el pasajero");
			}
		}else{
			aMap.put("Respuesta","El viaje esta finalizado");
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/pasajeros/cargarCredito", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarCreditoAPasajero(@RequestParam int pasajeroId, @RequestParam double monto) {
		
		Pasajero pasajero = DAOFactory.getInstance().getPasajeroDAO().recuperar(pasajeroId);

		pasajero.addCredito(monto);
		
		DAOFactory.getInstance().getPasajeroDAO().actualizar(pasajero);
		
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("Respuesta","Se agrego el credito");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}

	@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String finalizarViaje(@RequestParam int viajeId) {

		Viaje viaje = DAOFactory.getInstance().getViajeDAO().recuperar(viajeId);
		Map<String, Object> aMap = new HashMap<String, Object>();
		if (viaje.isFinalizado()){
			aMap.put("Respuesta","El viaje ya estaba finalizado");
		}else{
			viaje.finalizar();
			DAOFactory.getInstance().getViajeDAO().actualizar(viaje);
			aMap.put("Respuesta","Se finalizo el viaje");
		}


		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(aMap);
	}
	
}