package cl.perfulandia.sucursal.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;

@FeignClient(name = "inventario-service", url = "http://localhost:8081")
public interface InventarioClient {
    @PostMapping("/api/movimientos/registrar")
    MovimientoDTO registrarMovimiento(@RequestBody MovimientoRequest request);
}


