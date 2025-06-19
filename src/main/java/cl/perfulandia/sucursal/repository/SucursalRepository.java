package cl.perfulandia.sucursal.repository;

import cl.perfulandia.sucursal.modelo.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio para la entidad Sucursal.
 * Proporciona métodos para realizar operaciones CRUD sobre las sucursales en la base de datos.
 * 
 * @author Perfulandia
 * @version 1.0
 * @since 1.0
 */
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
