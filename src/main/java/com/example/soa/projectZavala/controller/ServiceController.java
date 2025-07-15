package com.example.soa.projectZavala.controller;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.soa.projectZavala.dto.ReporteUtilizacionDTO;
import com.example.soa.projectZavala.entity.Consulta;
import com.example.soa.projectZavala.entity.Mantenimiento;
import com.example.soa.projectZavala.entity.mensaje.HttpResponseModel;
import com.example.soa.projectZavala.entity.mensaje.MensajeMeta;
import com.example.soa.projectZavala.entity.mensaje.MetaModel;
import com.example.soa.projectZavala.entity.mensaje.ResponseCte;
import com.example.soa.projectZavala.service.ConsultaService;
import com.example.soa.projectZavala.service.EstadoVehiculoService;
import com.example.soa.projectZavala.service.MantenimientoService;

@RestController
@RequestMapping("/api/zavala")
public class ServiceController {

	// CONTROLLER CONSULTA
	@Autowired
	private ConsultaService consultaService;

	@GetMapping("/ConsultaServicios")
	public ResponseEntity<?> getVehiculosMantenimientoPrevio(
			@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date fecha,
			@RequestParam("servicioId") int servicioId) {

		List<Consulta> resultados = consultaService.obtenerVehiculosMantenimientoPrevio(new Date(fecha.getTime()),
				servicioId);

		if (resultados.isEmpty()) {
			return ResponseEntity.ok("No se encontraron vehículos con mantenimiento previo.");
		}

		return ResponseEntity.ok(resultados);
	}

	// SERVICIOS DE MANTENIMIENTO
	@Autowired
	private MantenimientoService mantenimientoService;

	@GetMapping("/mantenimiento/historial/{vehiculoId}")
	public ResponseEntity<HttpResponseModel> obtenerHistorialPorVehiculo(@PathVariable("vehiculoId") int vehiculoId) {
		List<Mantenimiento> lista = mantenimientoService.getHistorialPorVehiculo(vehiculoId);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		MensajeMeta mensaje;
		MetaModel meta;

		if (lista == null || lista.isEmpty()) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "No hay historial para el vehículo.",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Historial obtenido correctamente.",
					ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, lista.size(), idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, lista));
	}

	@PostMapping("/mantenimiento/programar-proximo")
	public ResponseEntity<HttpResponseModel> programarProximo(@RequestParam(name = "vehiculoId") int vehiculoId,
			@RequestParam(name = "adminId") int adminId) {

		boolean resultado = mantenimientoService.programarProximoMantenimiento(vehiculoId, adminId);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		MensajeMeta mensaje;
		MetaModel meta;

		if (!resultado) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(),
					"No se pudo programar el próximo mantenimiento", ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
					"Próximo mantenimiento programado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
	}

	// SERVICIOS DE ESTADO DE VEHICULO
	@Autowired
	private EstadoVehiculoService estadoVehiculoService;

	@GetMapping("/estado-vehiculo/reporte-utilizacion-vehiculos")
	public ResponseEntity<HttpResponseModel> obtenerReporteUtilizacionVehiculos() {
		List<ReporteUtilizacionDTO> lista = estadoVehiculoService.obtenerReporteUtilizacionVehiculos();
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		MensajeMeta mensaje;
		MetaModel meta;

		if (lista == null || lista.isEmpty()) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(),
					"No se encontraron datos para el reporte de utilización", ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
					"Reporte de utilización obtenido correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, lista.size(), idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, lista));
	}

	@PostMapping("/estado-vehiculo/actualizar-estado-mantenimiento")
	public ResponseEntity<HttpResponseModel> actualizarEstadoPorMantenimiento() {
	    String idTransaccion = UUID.randomUUID().toString().toUpperCase();

	    MensajeMeta mensaje;
	    MetaModel meta;

	    try {
	        estadoVehiculoService.actualizarEstadoPorMantenimiento();

			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
	                "Estados de vehículos actualizados correctamente.",
	                ResponseCte.OPERACION_CORRECTA.getTipo()
	        );
	        meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
	        return ResponseEntity.ok(new HttpResponseModel(meta, true));
	    } catch (Exception e) {
	        mensaje = new MensajeMeta(
	                ResponseCte.ERROR_GENERAL.getCodigo(),
	                "Error al actualizar estados de vehículos: " + e.getMessage(),
	                ResponseCte.ERROR_GENERAL.getTipo()
	        );
	        meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
	        return ResponseEntity.status(500).body(new HttpResponseModel(meta, false));
	    }
	}

}
