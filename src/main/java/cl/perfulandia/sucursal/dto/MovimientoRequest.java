package cl.perfulandia.sucursal.dto;

import lombok.Data;

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

