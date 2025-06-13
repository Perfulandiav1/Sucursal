package cl.perfulandia.sucursal.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;   
import java.util.List;


@RestController
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursales", description = "Operaciones relacionadas con las sucursales")
public class SucursalController {

    /**
     * Logger de la clase para registrar eventos y errores.
     */
    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @Operation(summary = "Listar todas las sucursales", description = "Obtiene una lista con todas las sucursales registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    @GetMapping("/listar")
    public List<Sucursal> obteneSucursales() {
        logger.info("Obteniendo lista de todas las sucursales");
        // Llama al servicio para obtener la lista de sucursales
        return sucursalService.obtenerSucursales();
    }

    @Operation(summary = "Obtener la sucursal por id ", description = "Obtiene una sucursal por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    @GetMapping("/sucursal/{id}")
    public Sucursal obtenerSucursalPorId(@PathVariable Long id) {
        logger.info("Obteniendo sucursal con ID: {}", id);
        return sucursalService.obtenerSucursalPorId(id);
    }
    @Operation(summary = "Guardar sucursal ", description = "Crea y guarda una nueva sucursal.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal creada/guardad correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    @PostMapping("/guardar")
    public Sucursal guardarSucursal(@RequestBody Sucursal sucursal) {
        logger.info("Guardando sucursal: {}", sucursal);
        if (sucursal.getSucursalId() != null) {
            logger.warn("La sucursal ya tiene un ID asignado, se actualizará en lugar de crear una nueva.");
        }
        return sucursalService.guardarSucursal(sucursal);
    }
    @Operation(summary = "Eliminar sucursal", description = "Elimina una sucursal por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal eliminada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    })
    @DeleteMapping("/eliminar/{id}")
    public void eliminarSucursal(@PathVariable("id") Long sucursalId) {
        logger.info("Eliminando sucursal con ID: {}", sucursalId);
        // Llama al servicio para eliminar la sucursal
        sucursalService.eliminarSucursal(sucursalId);
    }
    @Operation(summary = "Registrar Movimiento", description = "Registra un movimiento en el inventario de una sucursal.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoDTO.class)))
    })
    @PostMapping("/movimiento")
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@RequestBody MovimientoRequest request) {
        logger.info("Registrando movimiento en inventario: {}", request);
        MovimientoDTO movimiento = sucursalService.registrarMovimientoEnInventario(request);
        logger.info("Movimiento registrado: {}", movimiento);
        return ResponseEntity.ok(movimiento);
    }   

}
