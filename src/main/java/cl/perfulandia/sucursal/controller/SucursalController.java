package cl.perfulandia.sucursal.controller;

import cl.perfulandia.sucursal.assemblers.SucursalModelAssembler;
import cl.perfulandia.sucursal.dto.AlertaInventario;
import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.service.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;   
import java.util.List;
import java.util.stream.Collectors;
/**
 * Clase controller de sucursales , CRUD
 */
@RestController
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursales", description = "Operaciones relacionadas con las sucursales")
public class SucursalController {

    /**
     * Logger de la clase para registrar eventos y errores.
     */
    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);

    /**
     * Servicio de sucursales que maneja la lógica de negocio.
     */
    private final SucursalService sucursalService;
    /**
     * Ensamblador de modelos para convertir entidades a EntityModel.
     */
    private final SucursalModelAssembler assembler;

    /**
     * Constructor de la clase SucursalController.
     * 
     * @param sucursalService Servicio de sucursales para manejar la lógica de negocio.
     * @param assembler Ensamblador de modelos para convertir entidades a EntityModel.
     */
    public SucursalController(SucursalService sucursalService, SucursalModelAssembler assembler) {
        this.sucursalService = sucursalService;
        this.assembler = assembler;
    }

    /**
     * Endpoint para listar todas las sucursales.
     * 
     * @return ResponseEntity con una lista de EntityModel<Sucursal>.
     */
    @Operation(summary = "Listar todas las sucursales", description = "Obtiene una lista con todas las sucursales registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    /*
     * Método para listar todas las sucursales.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<EntityModel<Sucursal>>> obteneSucursales() {
        logger.info("Obteniendo lista de todas las sucursales");        
        List<EntityModel<Sucursal>> sucursales = sucursalService.obtenerSucursales().stream().map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sucursales);
    }
    /**
     * Endpoint para obtener una sucursal por su ID.
     * 
     * @param id ID de la sucursal a obtener.
     * @return ResponseEntity con el EntityModel<Sucursal> correspondiente.
     */
    @Operation(summary = "Obtener la sucursal por id ", description = "Obtiene una sucursal por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    /*
     * Método para obtener una sucursal por su ID.
     */
    @GetMapping("/sucursal/{id}")
    public ResponseEntity<EntityModel<Sucursal>> obtenerSucursalPorId(@PathVariable Long id) {
        logger.info("Obteniendo sucursal con ID: {}", id);
        Sucursal sucursal = sucursalService.obtenerSucursalPorId(id);
        if (sucursal == null) {
            logger.warn("Sucursal con ID {} no encontrada", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(sucursal));
    }
    /**
     * Endpoint para guardar una nueva sucursal o actualizar una existente.
     * 
     * @param sucursal Sucursal a guardar o actualizar.
     * @return ResponseEntity con el EntityModel<Sucursal> guardado o actualizado.
     */
    @Operation(summary = "Guardar sucursal ", description = "Crea y guarda una nueva sucursal.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal creada/guardad correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    /*
     * Método para guardar una nueva sucursal o actualizar una existente.
     * Si la sucursal ya tiene un ID asignado, se actualizará en lugar de crear una nueva.
     */
    @PostMapping("/guardar")
    public ResponseEntity<EntityModel<Sucursal>> guardarSucursal(@RequestBody Sucursal sucursal) {
        logger.info("Guardando sucursal: {}", sucursal);
        if (sucursal.getSucursalId() != null) {
            logger.warn("La sucursal ya tiene un ID asignado, se actualizará en lugar de crear una nueva.");
        }
        return ResponseEntity.ok(assembler.toModel(sucursalService.guardarSucursal(sucursal)));
    }
    /*
     * Método para eliminar una sucursal por su ID.
     */
    @Operation(summary = "Eliminar sucursal", description = "Elimina una sucursal por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal eliminada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    /*
     * Método para eliminar una sucursal por su ID.
     * Si la sucursal no existe, se devolverá un error 404 Not Found.
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable("id") Long sucursalId) {
        logger.info("Eliminando sucursal con ID: {}", sucursalId);
        sucursalService.eliminarSucursal(sucursalId);
        return ResponseEntity.ok().build();
    }
    /* 
     * Método para registrar un movimiento en el inventario de una sucursal.
     * Este endpoint recibe un objeto MovimientoRequest que contiene la información del movimiento a registrar.
     */
    @Operation(summary = "Registrar Movimiento", description = "Registra un movimiento en el inventario de una sucursal.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoDTO.class)))
    })
    /*
     * Método para registrar un movimiento en el inventario de una sucursal.
     * Este endpoint recibe un objeto MovimientoRequest que contiene la información del movimiento a registrar.
     */
    @PostMapping("/movimiento")
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@RequestBody MovimientoRequest request) {
        logger.info("Registrando movimiento en inventario: {}", request);
        MovimientoDTO movimiento = sucursalService.registrarMovimientoEnInventario(request);
        logger.info("Movimiento registrado: {}", movimiento);
        return ResponseEntity.ok(movimiento);
    }   

    @GetMapping("/alertas/sucursal/{sucursalId}/producto/{productoId}")
    public ResponseEntity<List<AlertaInventario>> listarAlertas(
            @PathVariable Long sucursalId,
            @PathVariable Long productoId) {
        List<AlertaInventario> alertas = sucursalService.obtenerAlertasPorSucursalYProducto(sucursalId, productoId);
        return ResponseEntity.ok(alertas);
    }
}
