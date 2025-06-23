package com.example.soa.projectZavala.controller;

//import java.util.ArrayList;
//import java.util.Date;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.soa.projectZavala.entity.Administrador;
import com.example.soa.projectZavala.entity.Consulta;
import com.example.soa.projectZavala.entity.EstadoVehiculo;
import com.example.soa.projectZavala.entity.Vehiculo;
import com.example.soa.projectZavala.entity.mensaje.HttpResponseModel;
import com.example.soa.projectZavala.entity.mensaje.MensajeMeta;
import com.example.soa.projectZavala.entity.mensaje.MetaModel;
import com.example.soa.projectZavala.entity.mensaje.ResponseCte;
import com.example.soa.projectZavala.entity.response.EstadoVehiculoResponse;
import com.example.soa.projectZavala.entity.response.VehiculoResponse;
import com.example.soa.projectZavala.service.AdministradorService;
import com.example.soa.projectZavala.service.ConsultaService;
import com.example.soa.projectZavala.service.EstadoVehiculoService;
import com.example.soa.projectZavala.service.VehiculoService;

@RestController
@RequestMapping("/api/zavala")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/login")
    public ResponseEntity<String> logeoYObtieneNombre(@RequestBody Administrador administrador) {
        String mensaje = administradorService.logeoYObtieneNombre(administrador.getCorreo(), administrador.getContraseña());
        return ResponseEntity.ok(mensaje);
    }

    // controller de estado de vehiculos
    @Autowired
    private EstadoVehiculoService estadoVehiculoService;
    
    // Listar todos los estados de evhiculo 
    @GetMapping("/listar")
    public ResponseEntity<List<EstadoVehiculo>> listarEstados() {
        EstadoVehiculoResponse response = estadoVehiculoService.getList();
        return ResponseEntity.ok(response.getListaEstadosVehiculo());
    }
    
 // Controller de Vehiculo
    @Autowired
    private VehiculoService vehiculoService;

    // ejemplo de Controller RESTful con Response Wrapper
    @GetMapping("/vehiculo/listar")
    public ResponseEntity<HttpResponseModel> listarVehiculos() {
        List<Vehiculo> listaVehiculos = vehiculoService.getList().getListaVehiculos(); // suponiendo que el método devuelve VehiculoResponse
        VehiculoResponse response = vehiculoService.getList();

        MensajeMeta mensaje;
        MetaModel meta;
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();

        if (listaVehiculos == null || listaVehiculos.isEmpty()) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), 
                                      ResponseCte.ERROR_GENERAL.getMensaje(), 
                                      ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
            return ResponseEntity.ok(new HttpResponseModel(meta, response));
        }

        mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), 
                                  ResponseCte.OPERACION_CORRECTA.getMensaje(), 
                                  ResponseCte.OPERACION_CORRECTA.getTipo());
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
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Vehículo no encontrado", ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo encontrado", ResponseCte.OPERACION_CORRECTA.getTipo());
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
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al insertar vehículo", ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo insertado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
    }

    // Actualizar
    @PutMapping("/vehiculo/actualizar/{id}")
    public ResponseEntity<HttpResponseModel> actualizarVehiculo(@PathVariable("id") int id, @RequestBody Vehiculo vehiculo) {
        boolean resultado = vehiculoService.update(vehiculo, id);
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();
        MensajeMeta mensaje;
        MetaModel meta;

        if (!resultado) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al actualizar vehículo", ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo actualizado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
    }

    // Eliminar
    @DeleteMapping("/vehiculo/eliminar/{id}")
    public ResponseEntity<HttpResponseModel> eliminarVehiculo(@PathVariable ("id") int id) {
        boolean resultado = vehiculoService.delete(id);
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();
        MensajeMeta mensaje;
        MetaModel meta;

        if (!resultado) {
            mensaje = new MensajeMeta(ResponseCte.ERROR_GENERAL.getCodigo(), "Error al eliminar vehículo", ResponseCte.ERROR_GENERAL.getTipo());
            meta = new MetaModel(mensaje, 0, idTransaccion, 0, false);
        } else {
            mensaje = new MensajeMeta(ResponseCte.OPERACION_CORRECTA.getCodigo(), "Vehículo eliminado correctamente", ResponseCte.OPERACION_CORRECTA.getTipo());
            meta = new MetaModel(mensaje, 1, idTransaccion, 0, true);
        }

        return ResponseEntity.ok(new HttpResponseModel(meta, resultado));
    }
    
    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/vehiculos-mantenimiento-previo")
    public ResponseEntity<?> getVehiculosMantenimientoPrevio(
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date fecha,
            @RequestParam("servicioId") int servicioId) {

        List<Consulta> resultados = consultaService.obtenerVehiculosMantenimientoPrevio(new Date(fecha.getTime()), servicioId);

        if (resultados.isEmpty()) {
            return ResponseEntity.ok("No se encontraron vehículos con mantenimiento previo.");
        }

        return ResponseEntity.ok(resultados);
    }
    
}









