package cl.perfulandia.sucursal.service;

import cl.perfulandia.sucursal.controller.SucursalController;
import cl.perfulandia.sucursal.dto.AlertaInventario;
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

/**
 * Servicio para manejar la lógica de negocio relacionada con las sucursales.
 * Proporciona métodos para obtener, guardar, eliminar sucursales y registrar movimientos en inventario.
 * Utiliza un repositorio para acceder a los datos de las sucursales y un cliente Feign para interactuar con el servicio de inventario.
 * 
 * @author Perfulandia
 * @version 1.0
 * @since 1.0
 */
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
    
    
    /**
     * Obtiene una lista de todas las sucursales.
     * 
     * @return Lista de Sucursal
     */
    public List<Sucursal> obtenerSucursales() {
        logger.info("Obteniendo lista de todas las sucursales");
        // Llama al repositorio para obtener la lista de sucursales
        return sucursalRepository.findAll();
    }
    /**
     * Obtiene una sucursal por su ID.
     * 
     * @param id ID de la sucursal a obtener
     * @return Sucursal con el ID especificado, o null si no se encuentra
     */
    public Sucursal obtenerSucursalPorId(Long id) {
        logger.info("Obteniendo sucursal con ID: {}", id);
        // Llama al repositorio para obtener la sucursal por ID     
        return sucursalRepository.findById(id).orElse(null);
    }
    /**
     * Guarda una sucursal en la base de datos.
     * 
     * @param sucursal Sucursal a guardar
     * @return Sucursal guardada
     */

    public Sucursal guardarSucursal(Sucursal sucursal) {
        logger.info("Guardando sucursal: {}", sucursal);
        // Llama al repositorio para guardar la sucursal
        return sucursalRepository.save(sucursal);
    }
    /**
     * Elimina una sucursal por su ID.
     * 
     * @param id ID de la sucursal a eliminar
     */
    public void eliminarSucursal(Long id) {
        logger.info("Eliminando sucursal con ID: {}", id);
        // Llama al repositorio para eliminar la sucursal por ID
        sucursalRepository.deleteById(id);
    }
    /**
     * Registra un movimiento en el inventario utilizando el cliente Feign.
     * 
     * @param request Objeto MovimientoRequest que contiene los detalles del movimiento
     * @return MovimientoDTO que representa el movimiento registrado
     */
    @Autowired
    private InventarioClient inventarioClient;

    public MovimientoDTO registrarMovimientoEnInventario(MovimientoRequest request) {
        logger.info("Registrando movimiento en inventario: {}", request);
        // Llama al cliente Feign para registrar el movimiento en el inventario
        return inventarioClient.registrarMovimiento(request);
    }
/**
     * Registra un movimiento en el inventario de una sucursal específica.
     * 
     * @param sucursalId ID de la sucursal donde se registra el movimiento
     * @param productoId ID del producto involucrado en el movimiento
     * @param cantidad Cantidad del producto en el movimiento
     * @param tipo Tipo de movimiento (por ejemplo, "entrada" o "salida")
     * @return MovimientoDTO que representa el movimiento registrado
     */
    public MovimientoDTO registrarMovimientoEnInventario(Long sucursalId, Long productoId, Integer cantidad, String tipo) {
        MovimientoRequest req = new MovimientoRequest();
        req.setSucursalId(sucursalId);

        MovimientoRequest.ProductoDTO prod = new MovimientoRequest.ProductoDTO();
        prod.setId(productoId);
        req.setProducto(prod);

        req.setCantidad(cantidad);
        req.setTipo(tipo);

        // Llamada al Feign Client
        return inventarioClient.registrarMovimiento(req);
    }
    /**
     * Obtiene una lista de alertas de inventario para una sucursal específica y un producto específico.
     * 
     * @param sucursalId ID de la sucursal para la cual se desean listar las alertas
     * @param productoId ID del producto para el cual se desean listar las alertas
     * @return Lista de AlertaInventario que representan las alertas de inventario
     */
    public List<AlertaInventario> obtenerAlertasPorSucursalYProducto(Long sucursalId, Long productoId) {
        return inventarioClient.listarAlertasPorSucursalYProducto(sucursalId, productoId);
    }
}
