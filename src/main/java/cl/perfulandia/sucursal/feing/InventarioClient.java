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
@FeignClient(name = "inventario-service", url = "https://inventario-8c66.onrender.com")
public interface InventarioClient {
    /**
     * Registra un movimiento de inventario.
     *
     * @param request El objeto MovimientoRequest que contiene los detalles del movimiento.
     * @return Un objeto MovimientoDTO que representa el movimiento registrado.
     */
    @PostMapping("/api/movimientos/registrar")
    MovimientoDTO registrarMovimiento(@RequestBody MovimientoRequest request);

    /**
     * Lista las alertas de inventario para una sucursal específica y un producto específico.
     *
     * @param sucursalId El ID de la sucursal para la cual se desean listar las alertas.
     * @param productoId El ID del producto para el cual se desean listar las alertas.
     * @return Una lista de objetos AlertaInventario que representan las alertas de inventario.
     */
    @GetMapping("/api/alertas/obtener/sucursal/{sucursalId}/producto/{productoId}")
    List<AlertaInventario> listarAlertasPorSucursalYProducto(
        @PathVariable("sucursalId") Long sucursalId,
        @PathVariable("productoId") Long productoId
    );
}


