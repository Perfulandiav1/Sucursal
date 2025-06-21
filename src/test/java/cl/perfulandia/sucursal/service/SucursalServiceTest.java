package cl.perfulandia.sucursal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import cl.perfulandia.sucursal.controller.SucursalController;
import cl.perfulandia.sucursal.dto.AlertaInventario;
import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.feing.InventarioClient;
import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.repository.SucursalRepository;

/**
 * SucursalServiceTest.java
 * Clase de prueba para el servicio de sucursales.
 * Verifica el comportamiento del servicio al interactuar con el repositorio y el cliente de inventario.
 */
@SpringBootTest
@ActiveProfiles("test")
public class SucursalServiceTest {
    /**
     * Servicio de sucursales que maneja la lógica de negocio.
     */
    @Autowired
    private SucursalService sucursalService;
    /**
     * Repositorio de sucursales que interactúa con la base de datos.
     */
    @MockBean
    private SucursalRepository sucursalRepository;

    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);


    @Test
    public void testObtenerSucursales() {
        // GIVEN
        logger.info("Iniciando test para obtener sucursales");
        // Crea una lista vacía de sucursales para simular el caso donde no hay sucursales registradas.
        List<Sucursal> sucursales = new ArrayList<>(); 
        logger.info("Lista de sucursales creada: {}", sucursales);
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve la lista de sucursales.

        // WHEN
        when(sucursalRepository.findAll()).thenReturn(sucursales);
        logger.info("Mockeando el repositorio para devolver la lista de sucursales");
        // Llama al método del servicio para obtener las sucursales.    

        // THEN
        List<Sucursal> result = sucursalService.obtenerSucursales();
        logger.info("Obteniendo lista de sucursales del servicio");
        // Verifica que el resultado no sea nulo y que la lista de sucursales sea igual a la lista esperada.
        assertEquals(sucursales, result);
        assertEquals(0, result.size());
        logger.info("Test para obtener sucursales finalizado"); 
    }

    /**
     * Test para obtener una sucursal por su ID.
     * Verifica que el servicio retorne la sucursal correcta cuando se le proporciona un ID válido.
     */
    @Test
    public void testObtenerSucursalPorId() {
        logger.info("Iniciando test para obtener sucursal por ID");
        // Crea una sucursal de prueba con un ID específico.
        Long id = 1L;
        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(id);
        logger.info("Sucursal creada con ID: {}", id);
        when(sucursalRepository.findById(id)).thenReturn(Optional.of(sucursal));

        Sucursal result = sucursalService.obtenerSucursalPorId(id);
        logger.info("Obteniendo sucursal por ID: {}", id);
        // Verifica que el resultado no sea nulo y que el ID de la sucursal coincida con el ID esperado.
        assertNotNull(result);
        assertEquals(id, result.getSucursalId());
        logger.info("Test para obtener sucursal por ID finalizado");
    }

    /**
     * Test para obtener una sucursal por ID que no existe.
     * Verifica que el servicio retorne null cuando se le proporciona un ID no válido.
     */
    @Test
    public void testGuardarSucursal() {
        logger.info("Iniciando test para guardar sucursal");
        // Crea una sucursal de prueba con un ID específico y otros atributos.
        Sucursal sucursal = new Sucursal();
        Long sucursalId = 1L;
        sucursal.setSucursalId(sucursalId);
        sucursal.setNombre("Sucursal Test");
        sucursal.setDireccion("123 Test St");
        sucursal.setTelefono("123456789");
        sucursal.setCorreo("afaf");
        logger.info("Sucursal creada: {}", sucursal);
        // Mock del repositorio de sucursales.
        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(sucursalRepository.save(sucursal)).thenReturn(sucursal);
        logger.info("Mockeando el repositorio para guardar la sucursal");
        // WHEN
        // Llama al método save() del servicio.
        logger.info("Llamando al servicio para guardar la sucursal");   
        Sucursal saved = sucursalService.guardarSucursal(sucursal);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertEquals(sucursal.getSucursalId(), saved.getSucursalId());
        assertEquals(sucursal.getNombre(), saved.getNombre());
        assertEquals(sucursal.getDireccion(), saved.getDireccion());
        assertEquals(sucursal.getTelefono(), saved.getTelefono());
        assertEquals(sucursal.getCorreo(), saved.getCorreo());
        logger.info("Test para guardar sucursal finalizado");   
    }

    /**
     * Test para eliminar una sucursal.
     * Verifica que el servicio llame al método deleteById() del repositorio.
     */
    @Test
    public void testEliminarSucursal() {
        logger.info("Iniciando test para eliminar sucursal");
        // Mock del repositorio de sucursales.  
        Long id = 1L;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(sucursalRepository).deleteById(id);
        logger.info("Mockeando el repositorio para eliminar la sucursal con ID: {}", id);
        // WHEN 
        // Llama al método deleteByCodigo() del servicio.
        sucursalService.eliminarSucursal(id);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(sucursalRepository, times(1)).deleteById(id);
        logger.info("Test para eliminar sucursal finalizado");
    }

    /**
     * Test para registrar un movimiento en el inventario.
     * Verifica que el servicio llame al método registrarMovimiento() del cliente de inventario.
     */
    @Test
    void testRegistrarMovimientoEnInventario() {
        logger.info("Iniciando test para registrar movimiento en inventario");
        // Asegúrate de que el servicio y sus dependencias estén correctamente configurados.
        // Mock de dependencias
        SucursalRepository sucursalRepository = mock(SucursalRepository.class);
        InventarioClient inventarioClient = mock(InventarioClient.class);
        
        // Instancia del servicio con el mock de repository
        SucursalService sucursalService = new SucursalService(sucursalRepository);
        // Inyecta el mock de InventarioClient usando reflexión (ya que es private)
        java.lang.reflect.Field field;
        logger.info("Inyectando mock de InventarioClient en SucursalService");
        assertNotNull(sucursalService, "SucursalService no debe ser nulo");
        try {
            field = SucursalService.class.getDeclaredField("inventarioClient");
            field.setAccessible(true);
            field.set(sucursalService, inventarioClient);
        } catch (Exception e) {
            fail("No se pudo inyectar el mock de InventarioClient");
        }
        logger.info("Mock de InventarioClient inyectado correctamente");
        // Verifica que el servicio no sea nulo
        
        // Datos de prueba
        MovimientoRequest request = new MovimientoRequest();
        MovimientoDTO expectedResponse = new MovimientoDTO();
        logger.info("Datos de prueba creados: {}", request);
        // Define el comportamiento del mock
        when(inventarioClient.registrarMovimiento(request)).thenReturn(expectedResponse);

        // Ejecuta el método
        MovimientoDTO result = sucursalService.registrarMovimientoEnInventario(request);

        // Verifica el resultado
        assertEquals(expectedResponse, result);
        verify(inventarioClient, times(1)).registrarMovimiento(request);
        logger.info("Test para registrar movimiento en inventario finalizado");
    }
    
    /**
     * Test para registrar un movimiento en el inventario con parámetros específicos.
     * Verifica que el servicio construya correctamente el objeto MovimientoRequest y lo envíe al cliente de inventario.
     */
    @Test
    void testRegistrarMovimientoEnInventarioConParametros() throws Exception {
        // Arrange
        SucursalRepository sucursalRepository = mock(SucursalRepository.class);
        InventarioClient inventarioClient = mock(InventarioClient.class);
        SucursalService sucursalService = new SucursalService(sucursalRepository);

        // Inyecta el mock de InventarioClient usando reflexión
        java.lang.reflect.Field field = SucursalService.class.getDeclaredField("inventarioClient");
        field.setAccessible(true);
        field.set(sucursalService, inventarioClient);

        Long sucursalId = 1L;
        Long productoId = 2L;
        Integer cantidad = 5;
        String tipo = "ENTRADA";

        MovimientoRequest expectedRequest = new MovimientoRequest();
        expectedRequest.setSucursalId(sucursalId);
        MovimientoRequest.ProductoDTO prod = new MovimientoRequest.ProductoDTO();
        prod.setId(productoId);
        expectedRequest.setProducto(prod);
        expectedRequest.setCantidad(cantidad);
        expectedRequest.setTipo(tipo);

        MovimientoDTO expectedResponse = new MovimientoDTO();

        when(inventarioClient.registrarMovimiento(
                org.mockito.ArgumentMatchers.refEq(expectedRequest)
        )).thenReturn(expectedResponse);

        // Act
        MovimientoDTO result = sucursalService.registrarMovimientoEnInventario(sucursalId, productoId, cantidad, tipo);

        // Assert
        assertEquals(expectedResponse, result);
        verify(inventarioClient, times(1)).registrarMovimiento(org.mockito.ArgumentMatchers.refEq(expectedRequest));
    }

    /**
     * Test para obtener alertas de inventario por sucursal y producto.
     * Verifica que el servicio llame al método listarAlertasPorSucursalYProducto del cliente de inventario.
     */
    @Test
    void testObtenerAlertasPorSucursalYProducto() throws Exception {
        // Arrange
        SucursalRepository sucursalRepository = mock(SucursalRepository.class);
        InventarioClient inventarioClient = mock(InventarioClient.class);
        SucursalService sucursalService = new SucursalService(sucursalRepository);

        // Inyecta el mock de InventarioClient usando reflexión
        java.lang.reflect.Field field = SucursalService.class.getDeclaredField("inventarioClient");
        field.setAccessible(true);
        field.set(sucursalService, inventarioClient);

        Long sucursalId = 1L;
        Long productoId = 2L;
        List<AlertaInventario> expectedAlertas = new ArrayList<>();
        when(inventarioClient.listarAlertasPorSucursalYProducto(sucursalId, productoId)).thenReturn(expectedAlertas);

        // Act
        List<AlertaInventario> result = sucursalService.obtenerAlertasPorSucursalYProducto(sucursalId, productoId);

        // Assert
        assertEquals(expectedAlertas, result);
        verify(inventarioClient, times(1)).listarAlertasPorSucursalYProducto(sucursalId, productoId);
    }
}





