package cl.perfulandia.sucursal.service;

import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.dto.SucursalDTO;
import cl.perfulandia.sucursal.feing.InventarioClient;
import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.repository.SucursalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public List<Sucursal> obtenerSucursales() {
        return sucursalRepository.findAll();
    }

    public SucursalDTO obtenerSucursalPorId(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + id));
        return convertirASucursalDTO(sucursal);
    }

    private SucursalDTO convertirASucursalDTO(Sucursal sucursal) {
        return new SucursalDTO(
                sucursal.getSucursalId(),
                sucursal.getNombre(),
                sucursal.getDireccion(),
                sucursal.getTelefono(),
                sucursal.getCorreo()// Aquí puedes agregar más campos si lo necesitas
        );
    }


    public Sucursal guardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void eliminarSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }

    @Autowired
    private InventarioClient inventarioClient;

    public MovimientoDTO registrarMovimientoEnInventario(MovimientoRequest request) {
        return inventarioClient.registrarMovimiento(request);
    }

}
