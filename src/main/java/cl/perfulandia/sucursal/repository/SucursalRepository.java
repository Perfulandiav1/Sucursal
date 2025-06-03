package cl.perfulandia.sucursal.repository;

import cl.perfulandia.sucursal.modelo.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
