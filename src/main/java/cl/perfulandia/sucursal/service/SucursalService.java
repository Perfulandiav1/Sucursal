package cl.perfulandia.sucursal.service;

import cl.perfulandia.sucursal.controller.SucursalController;
import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.feing.InventarioClient;
import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.repository.SucursalRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SucursalService {
    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);
    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        logger.info("Inicializando SucursalService");
        this.sucursalRepository = sucursalRepository;
        logger.info("SucursalService inicializado correctamente");
        logger.debug("SucursalRepository inyectado: {}", sucursalRepository != null);
    }

    public List<Sucursal> obtenerSucursales() {
        logger.info("Obteniendo lista de todas las sucursales");
        // Llama al repositorio para obtener la lista de sucursales
        return sucursalRepository.findAll();
    }

    public Sucursal obtenerSucursalPorId(Long id) {
        logger.info("Obteniendo sucursal con ID: {}", id);
        // Llama al repositorio para obtener la sucursal por ID     
        return sucursalRepository.findById(id).orElse(null);
    }


    public Sucursal guardarSucursal(Sucursal sucursal) {
        logger.info("Guardando sucursal: {}", sucursal);
        // Llama al repositorio para guardar la sucursal
        return sucursalRepository.save(sucursal);
    }

    public void eliminarSucursal(Long id) {
        logger.info("Eliminando sucursal con ID: {}", id);
        // Llama al repositorio para eliminar la sucursal por ID
        sucursalRepository.deleteById(id);
    }

    @Autowired
    private InventarioClient inventarioClient;

    public MovimientoDTO registrarMovimientoEnInventario(MovimientoRequest request) {
        logger.info("Registrando movimiento en inventario: {}", request);
        // Llama al cliente Feign para registrar el movimiento en el inventario
        return inventarioClient.registrarMovimiento(request);
    }

}
