package cl.perfulandia.sucursal.dto;

import lombok.Data;
import java.time.LocalDateTime;
/*
 * Clase DTO para representar un movimiento de inventario en una sucursal.
 * Contiene información sobre el ID del movimiento, sucursal, producto, cantidad,
 */
@Data
public class MovimientoDTO {
    private Long id;
    private Long sucursalId;
    private Long productoId;
    private Integer cantidad;
    private String tipo;
    private LocalDateTime fechaMovimiento;
}