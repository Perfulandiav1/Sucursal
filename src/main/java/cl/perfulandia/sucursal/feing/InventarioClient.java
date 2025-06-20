package cl.perfulandia.sucursal.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.dto.AlertaInventario;

import java.util.List;

/**
 * Cliente Feign para interactuar con el servicio de inventario.
 * Utiliza la URL base del servicio de inventario y define endpoints para registrar movimientos y listar alertas.
 * 
 * @author Perfulandia
 * @version 1.0
 * @since 1.0
 */
@FeignClient(name = "inventario-service", url = "http://localhost:8081")
public interface InventarioClient {

    @PostMapping("/api/movimientos/registrar")
    MovimientoDTO registrarMovimiento(@RequestBody MovimientoRequest request);

    @GetMapping("/api/alertas/obtener/sucursal/{sucursalId}/producto/{productoId}")
    List<AlertaInventario> listarAlertasPorSucursalYProducto(
        @PathVariable("sucursalId") Long sucursalId,
        @PathVariable("productoId") Long productoId
    );
}


