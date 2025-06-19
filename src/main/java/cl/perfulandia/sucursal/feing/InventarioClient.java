package cl.perfulandia.sucursal.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;

/**
 * Cliente Feign para interactuar con el servicio de inventario.
 * Utiliza la URL base del servicio de inventario y define un endpoint para registrar movimientos.
 * 
 * @author Perfulandia
 * @version 1.0
 * @since 1.0
 * * Este cliente se utiliza para registrar movimientos de inventario en una sucursal */
@FeignClient(name = "inventario-service", url = "http://localhost:8081")
public interface InventarioClient {
    @PostMapping("/api/movimientos/registrar")
    MovimientoDTO registrarMovimiento(@RequestBody MovimientoRequest request);
}


