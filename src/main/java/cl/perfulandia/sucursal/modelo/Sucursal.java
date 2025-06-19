package cl.perfulandia.sucursal.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
/* 
 * Clase que representa una sucursal en el sistema.
 * Contiene información sobre el ID de la sucursal, nombre, dirección, teléfono y correo electrónico.
 */
@Entity
@Data
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sucursalId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
}
