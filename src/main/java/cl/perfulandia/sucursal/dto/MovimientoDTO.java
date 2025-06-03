package cl.perfulandia.sucursal.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MovimientoDTO {
    private Long id;
    private Long sucursalId;
    private Long productoId;
    private Integer cantidad;
    private String tipo;
    private LocalDateTime fechaMovimiento;
}