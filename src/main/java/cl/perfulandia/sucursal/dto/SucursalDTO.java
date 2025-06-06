package cl.perfulandia.sucursal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDTO {
    private Long sucursalId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    // Puedes agregar más campos si necesitas
}
