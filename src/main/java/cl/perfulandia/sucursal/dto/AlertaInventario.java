package cl.perfulandia.sucursal.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AlertaInventario {

    private Long id;
    private Long productoId;
    private Long sucursalId;
    private int stockActual;
    private String mensaje;
    private LocalDateTime fechaHora;
}
