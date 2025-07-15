package com.example.soa.projectZavala.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soa.projectZavala.dto.LoginResponse;
import com.example.soa.projectZavala.dto.MantenimientoDTO;
import com.example.soa.projectZavala.entity.Administrador;
import com.example.soa.projectZavala.entity.EstadoVehiculo;
import com.example.soa.projectZavala.entity.Mantenimiento;
import com.example.soa.projectZavala.entity.Vehiculo;
import com.example.soa.projectZavala.entity.mensaje.HttpResponseModel;
import com.example.soa.projectZavala.entity.mensaje.MensajeMeta;
import com.example.soa.projectZavala.entity.mensaje.MetaModel;
import com.example.soa.projectZavala.entity.mensaje.ResponseCte;
import com.example.soa.projectZavala.entity.response.EstadoVehiculoResponse;
import com.example.soa.projectZavala.entity.response.MantenimientoResponse;
import com.example.soa.projectZavala.entity.response.VehiculoResponse;
import com.example.soa.projectZavala.service.AdministradorService;
import com.example.soa.projectZavala.service.EstadoVehiculoService;
import com.example.soa.projectZavala.service.MantenimientoService;
import com.example.soa.projectZavala.service.VehiculoService;

@RestController
@RequestMapping("/api/zavala")
public class AdministradorController {

	// CONTROLLER LOGIN
	@Autowired
	private AdministradorService administradorService;

	@PostMapping("/login")
	public ResponseEntity<?> logeoYObtieneDatos(@RequestBody Administrador administrador) {
		LoginResponse response = administradorService.logeo(administrador.getCorreo(), administrador.getContraseña());

		if (response != null) {
			return ResponseEntity.ok(response); // devolverá id + nombre
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
		}
	}

	// CONTROLLER ESTADO VEHICULO
	@Autowired
	private EstadoVehiculoService estadoVehiculoService;

	@GetMapping("/estado-vehiculo/listar")
    public ResponseEntity<HttpResponseModel> listarEstados() {
        EstadoVehiculoResponse response = estadoVehiculoService.getList();
        List<EstadoVehiculo> lista = response.getListaEstadosVehiculo();

        String idTransaccion = UUID.randomUUID().toString().toUpperCase();
        MensajeMeta mensaje;
        MetaModel meta;

        if (lista == null || lista.isEmpty()) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "No se encontraron estados de vehículo.",
                    ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
                    "Estados de vehículo obtenidos correctamente.", ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, lista.size(), idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, response));
    }

    @GetMapping("/estado-vehiculo/listar/{id}")
    public ResponseEntity<HttpResponseModel> obtenerEstadoPorId(@PathVariable("id") int id) {
        EstadoVehiculo estado = estadoVehiculoService.getById(id);
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();

        MensajeMeta mensaje;
        MetaModel meta;

        if (estado == null || estado.getIdEstado() == null) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Estado de vehículo no encontrado",
                    ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Estado de vehículo encontrado",
                    ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, estado));
    }

    @PostMapping("/estado-vehiculo/insertar")
    public ResponseEntity<HttpResponseModel> insertarEstado(@RequestBody EstadoVehiculo estado) {
        boolean resultado = estadoVehiculoService.insert(estado);
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();

        MensajeMeta mensaje;
        MetaModel meta;

        if (!resultado) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al insertar estado de vehículo",
                    ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
                    "Estado de vehículo insertado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
    }

    @PutMapping("/estado-vehiculo/actualizar/{id}")
    public ResponseEntity<HttpResponseModel> actualizarEstado(@PathVariable("id") int id,
            @RequestBody EstadoVehiculo estado) {
        boolean resultado = estadoVehiculoService.update(estado, id);
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();

        MensajeMeta mensaje;
        MetaModel meta;

        if (!resultado) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al actualizar estado de vehículo",
                    ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
                    "Estado de vehículo actualizado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
    }

    @DeleteMapping("/estado-vehiculo/eliminar/{id}")
    public ResponseEntity<HttpResponseModel> eliminarEstado(@PathVariable("id") int id) {
        boolean resultado = estadoVehiculoService.delete(id);
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();

        MensajeMeta mensaje;
        MetaModel meta;

        if (!resultado) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al eliminar estado de vehículo",
                    ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
                    "Estado de vehículo eliminado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
    }


	// CONTROLLER VEHICULO
	@Autowired
	private VehiculoService vehiculoService;

	// ejemplo de Controller RESTful con Response Wrapper
	@GetMapping("/vehiculo/listar")
	public ResponseEntity<HttpResponseModel> listarVehiculos() {
		List<Vehiculo> listaVehiculos = vehiculoService.getList().getListaVehiculos(); // suponiendo que el método
																						// devuelve VehiculoResponse
		VehiculoResponse response = vehiculoService.getList();

		MensajeMeta mensaje;
		MetaModel meta;
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		if (listaVehiculos == null || listaVehiculos.isEmpty()) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), ResponseCte.ERROR_GENERAL.getMensaje(),
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
			return ResponseEntity.ok(new HttpResponseModel(meta, response));
		}

		mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
				ResponseCte.OPERACION_CORRECTA.getMensaje(), ResponseCte.OPERACION_CORRECTA.getTipo());
		meta = new MetaModel(mensaje, listaVehiculos.size(), idTransaccion, 0, true);

		return ResponseEntity.ok(new HttpResponseModel(meta, response));
	}

	@GetMapping("/vehiculo/{id}")
	public ResponseEntity<HttpResponseModel> obtenerVehiculoPorId(@PathVariable("id") int id) {
		Vehiculo vehiculo = vehiculoService.getById(id);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();
		MensajeMeta mensaje;
		MetaModel meta;

		if (vehiculo == null || vehiculo.getIdVehiculo() == null) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Vehículo no encontrado",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo encontrado",
					ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, vehiculo));
	}

	// Insertar
	@PostMapping("/vehiculo/insertar")
	public ResponseEntity<HttpResponseModel> insertarVehiculo(@RequestBody Vehiculo vehiculo) {
		boolean resultado = vehiculoService.insert(vehiculo);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();
		MensajeMeta mensaje;
		MetaModel meta;

		if (!resultado) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al insertar vehículo",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo insertado correctamente",
					ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
	}

	// Actualizar
	@PutMapping("/vehiculo/actualizar/{id}")
	public ResponseEntity<HttpResponseModel> actualizarVehiculo(@PathVariable("id") int id,
			@RequestBody Vehiculo vehiculo) {
		boolean resultado = vehiculoService.update(vehiculo, id);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();
		MensajeMeta mensaje;
		MetaModel meta;

		if (!resultado) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al actualizar vehículo",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo actualizado correctamente",
					ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
	}

	// Eliminar
	@DeleteMapping("/vehiculo/eliminar/{id}")
	public ResponseEntity<HttpResponseModel> eliminarVehiculo(@PathVariable("id") int id) {
		boolean resultado = vehiculoService.delete(id);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();
		MensajeMeta mensaje;
		MetaModel meta;

		if (!resultado) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al eliminar vehículo",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo eliminado correctamente",
					ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
	}

	// CONTROLLER MANTENIMIENTO
	@Autowired
	private MantenimientoService mantenimientoService;

	@GetMapping("/mantenimiento/listar")
	public ResponseEntity<HttpResponseModel> listarMantenimientos() {
		List<Mantenimiento> lista = mantenimientoService.getList().getListaMantenimientos();
		MantenimientoResponse response = mantenimientoService.getList();

		String idTransaccion = UUID.randomUUID().toString().toUpperCase();
		MensajeMeta mensaje;
		MetaModel meta;

		if (lista == null || lista.isEmpty()) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "No se encontraron mantenimientos.",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
					"Mantenimientos obtenidos correctamente.", ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, lista.size(), idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, response));
	}

	@GetMapping("/mantenimiento/{id}")
	public ResponseEntity<HttpResponseModel> obtenerMantenimientoPorId(@PathVariable("id") int id) {
		Mantenimiento mantenimiento = mantenimientoService.getById(id);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		MensajeMeta mensaje;
		MetaModel meta;

		if (mantenimiento == null || mantenimiento.getIdMantenimiento() == null) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Mantenimiento no encontrado",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Mantenimiento encontrado",
					ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, mantenimiento));
	}

	@PostMapping("/mantenimiento/insertar")
	public ResponseEntity<HttpResponseModel> insertarMantenimiento(@RequestBody MantenimientoDTO dto) {
		Mantenimiento mantenimiento = new Mantenimiento();

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehiculo(dto.getVehiculoId());

		Administrador admin = new Administrador();
		admin.setIdAdministrador(dto.getAdministradorId());

		mantenimiento.setVehiculo(vehiculo);
		mantenimiento.setFechaProgramada(dto.getFechaProgramada());
		mantenimiento.setRealizado(dto.getRealizado());
		mantenimiento.setDescripcion(dto.getDescripcion());
		mantenimiento.setEstado(dto.getEstado());
		mantenimiento.setAdministrador(admin);

		boolean resultado = mantenimientoService.insert(mantenimiento);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		MensajeMeta mensaje;
		MetaModel meta;

		if (!resultado) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al insertar mantenimiento",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
					"Mantenimiento insertado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
	}

	@PutMapping("/mantenimiento/actualizar/{id}")
	public ResponseEntity<HttpResponseModel> actualizarMantenimiento(@PathVariable("id") int id,
			@RequestBody MantenimientoDTO dto) {

		Mantenimiento mantenimiento = new Mantenimiento();

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehiculo(dto.getVehiculoId());

		Administrador admin = new Administrador();
		admin.setIdAdministrador(dto.getAdministradorId());

		mantenimiento.setVehiculo(vehiculo);
		mantenimiento.setFechaProgramada(dto.getFechaProgramada());
		mantenimiento.setRealizado(dto.getRealizado());
		mantenimiento.setDescripcion(dto.getDescripcion());
		mantenimiento.setEstado(dto.getEstado());
		mantenimiento.setAdministrador(admin);

		boolean resultado = mantenimientoService.update(mantenimiento, id);

		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		MensajeMeta mensaje;
		MetaModel meta;

		if (!resultado) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al actualizar mantenimiento",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
					"Mantenimiento actualizado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
	}

	@DeleteMapping("/mantenimiento/eliminar/{id}")
	public ResponseEntity<HttpResponseModel> eliminarMantenimiento(@PathVariable("id") int id) {
		boolean resultado = mantenimientoService.delete(id);
		String idTransaccion = UUID.randomUUID().toString().toUpperCase();

		MensajeMeta mensaje;
		MetaModel meta;

		if (!resultado) {
			mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al eliminar mantenimiento",
					ResponseCte.ERROR_GENERAL.getTipo());
			meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
		} else {
			mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(),
					"Mantenimiento eliminado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
			meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
		}

		return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
	}



}
