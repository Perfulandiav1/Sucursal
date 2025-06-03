package cl.perfulandia.sucursal.controller;

import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.dto.SucursalDTO;
import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.service.SucursalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;   
import java.util.List;


@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping("/listar")
    public List<Sucursal> obteneSucursales() {
        return sucursalService.obtenerSucursales();
    }

    @GetMapping("/sucursal/{id}")
    public SucursalDTO obtenerSucursalPorId(@PathVariable Long id) {
        return sucursalService.obtenerSucursalPorId(id);
    }

    @PostMapping("/guardar")
    public Sucursal guardarSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.guardarSucursal(sucursal);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarSucursal(@PathVariable Long sucursalId) {
        sucursalService.eliminarSucursal(sucursalId);
    }

    @PostMapping("/movimiento")
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@RequestBody MovimientoRequest request) {
        MovimientoDTO movimiento = sucursalService.registrarMovimientoEnInventario(request);
        return ResponseEntity.ok(movimiento);
    }   

}
