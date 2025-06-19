package cl.perfulandia.sucursal.dto;

import lombok.Data;
/* 
 *  Clase DTO para representar una solicitud de movimiento de inventario en una sucursal.
 *  Contiene información sobre la sucursal, producto, cantidad y tipo de movimiento.
 */
@Data
public class MovimientoRequest {
    private Long sucursalId;
    private ProductoDTO producto;
    private Integer cantidad;
    private String tipo;

    @Data
    public static class ProductoDTO {
        private Long id;
    }
}

