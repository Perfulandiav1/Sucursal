package cl.perfulandia.sucursal.dto;

import lombok.Data;

@Data
public class MovimientoRequest {
    private Long sucursalId;
    private Long productoId;
    private Integer cantidad;
    private String tipo; // "ENTRADA" o "SALIDA"
}

