package cl.perfulandia.sucursal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * Clase DTO para representar una sucursal.
 * Contiene información sobre el ID de la sucursal, nombre, dirección, teléfono y correo electrónico.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDTO {
    private Long sucursalId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
}
