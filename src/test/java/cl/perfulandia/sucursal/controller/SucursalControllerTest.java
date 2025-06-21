package cl.perfulandia.sucursal.controller;

import cl.perfulandia.sucursal.assemblers.SucursalModelAssembler;
import cl.perfulandia.sucursal.dto.AlertaInventario;
import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.service.SucursalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Clase de prueba para el controlador SucursalController.
 * Utiliza MockMvc para simular peticiones HTTP y verificar las respuestas del controlador.
 * Mockea el servicio SucursalService y el ensamblador SucursalModelAssembler.
 */
@WebMvcTest(SucursalController.class)
class SucursalControllerTest {
    /**
     * MockMvc para simular peticiones HTTP al controlador.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Servicio de sucursales que se mockea para simular la lógica de negocio.
     */
    @MockBean
    private SucursalService sucursalService;
    /**
     * Ensamblador de modelos que se mockea para simular la conversión de entidades a EntityModel.
     */
    @MockBean
    private SucursalModelAssembler assembler;
    /**
     * ObjectMapper para convertir objetos a JSON y viceversa.
     */
    @Autowired
    private ObjectMapper objectMapper;
/**
     * Logger de la clase para registrar eventos y errores.
     */
    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);
    /**
     * Test para verificar que el controlador puede obtener una lista de sucursales.
     * Mockea el servicio para devolver una lista de sucursales y verifica que la respuesta sea correcta.
     */
    @Test
    void testControllerObteneSucursales() throws Exception {
        logger.info("Iniciando test para obtener sucursales");

        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        sucursal.setNombre("Sucursal Test");

        Mockito.when(sucursalService.obtenerSucursales()).thenReturn(Collections.singletonList(sucursal));
        // Mockear assembler para que retorne EntityModel con el objeto
        Mockito.when(assembler.toModel(any(Sucursal.class)))
               .thenAnswer(invocation -> {
                   Sucursal s = invocation.getArgument(0);
                   return EntityModel.of(s);
               });

        logger.info("Mockeando el servicio para obtener sucursales y assembler");
        mockMvc.perform(get("/api/sucursales/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sucursalId").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Sucursal Test"));

        logger.info("Test para obtener sucursales finalizado");
    }
    /**
     * Test para verificar que el controlador puede obtener una sucursal por su ID.
     * Mockea el servicio para devolver una sucursal específica y verifica que la respuesta sea correcta.
     */
    @Test
    void testControllerObtenerSucursalPorId() throws Exception {
        logger.info("Iniciando test para obtener sucursal por ID");
        Mockito.reset(sucursalService);

        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        sucursal.setNombre("Sucursal Test");

        Mockito.when(sucursalService.obtenerSucursalPorId(1L)).thenReturn(sucursal);
        Mockito.when(assembler.toModel(any(Sucursal.class))).thenReturn(EntityModel.of(sucursal));

        logger.info("Mockeando el servicio y assembler para obtener sucursal por ID");

        mockMvc.perform(get("/api/sucursales/sucursal/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucursalId").value(1L))
                .andExpect(jsonPath("$.nombre").value("Sucursal Test"));

        logger.info("Test para obtener sucursal por ID finalizado");
    }   
/**
     * Test para verificar que el controlador maneja correctamente el caso en que se intenta obtener una sucursal por un ID no existente.
     * Mockea el servicio para devolver null y verifica que la respuesta sea 404 Not Found.
     */
    @Test
    void testControllerObtenerSucursalPorIdNotFound() throws Exception {
        logger.info("Iniciando test para obtener sucursal por ID no existente");
        Mockito.reset(sucursalService);

        Mockito.when(sucursalService.obtenerSucursalPorId(99L)).thenReturn(null);

        logger.info("Mockeando el servicio para sucursal no encontrada");

        mockMvc.perform(get("/api/sucursales/sucursal/99"))
                .andExpect(status().isNotFound());

        logger.info("Test para sucursal no encontrada finalizado");
    }
/**
     * Test para verificar que el controlador puede guardar una sucursal.
     * Mockea el servicio para devolver la sucursal guardada y verifica que la respuesta sea correcta.
     */
    @Test
    void testControllerGuardarSucursal() throws Exception {
        logger.info("Iniciando test para guardar sucursal");
        Mockito.reset(sucursalService);

        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        sucursal.setNombre("Sucursal Test");

        Mockito.when(sucursalService.guardarSucursal(any(Sucursal.class))).thenReturn(sucursal);
        Mockito.when(assembler.toModel(any(Sucursal.class))).thenReturn(EntityModel.of(sucursal));

        logger.info("Mockeando el servicio y assembler para guardar sucursal");

        mockMvc.perform(post("/api/sucursales/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucursalId").value(1L))
                .andExpect(jsonPath("$.nombre").value("Sucursal Test"));

        logger.info("Test para guardar sucursal finalizado");
    }
    /*
     * Test para eliminar sucursal
     */
    @Test
    void testControllerEliminarSucursal() throws Exception {
        logger.info("Iniciando test para eliminar sucursal");
        Mockito.reset(sucursalService);

        Mockito.doNothing().when(sucursalService).eliminarSucursal(1L);

        logger.info("Mockeando el servicio para eliminar sucursal");

        mockMvc.perform(delete("/api/sucursales/eliminar/1"))
                .andExpect(status().isOk());

        logger.info("Test para eliminar sucursal finalizado");
    }
    /**
     * Test para verificar que el controlador maneja correctamente el caso en que se intenta eliminar una sucursal que no existe.
     * Mockea el servicio para lanzar una excepción y verifica que la respuesta sea 404 Not Found.
     */
    @Test
    void testControllerRegistrarMovimiento() throws Exception {
        logger.info("Iniciando test para registrar movimiento en inventario");
        Mockito.reset(sucursalService);

        MovimientoRequest request = new MovimientoRequest();
        MovimientoDTO response = new MovimientoDTO();

        Mockito.when(sucursalService.registrarMovimientoEnInventario(any(MovimientoRequest.class))).thenReturn(response);

        logger.info("Mockeando el servicio para registrar movimiento en inventario");

        mockMvc.perform(post("/api/sucursales/movimiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        logger.info("Test para registrar movimiento en inventario finalizado");
    }
    /**
     * Test para verificar que el controlador puede listar alertas de inventario por sucursal y producto.
     * Mockea el servicio para devolver una lista de alertas y verifica que la respuesta sea correcta.
     */
    @Test
    void testControllerListarAlertas() throws Exception {
        logger.info("Iniciando test para listar alertas por sucursal y producto");
        Mockito.reset(sucursalService);

        Long sucursalId = 1L;
        Long productoId = 2L;

        AlertaInventario alerta = new AlertaInventario();
        alerta.setId(1L);
        alerta.setProductoId(productoId);
        alerta.setSucursalId(sucursalId);
        alerta.setStockActual(5);
        alerta.setMensaje("Stock bajo");
        alerta.setFechaHora(LocalDateTime.now());
        logger.info("Creando alerta de inventario: {}", alerta);
        // Mockear el servicio para devolver una lista con la alerta creada
        Mockito.when(sucursalService.obtenerAlertasPorSucursalYProducto(sucursalId, productoId))
                .thenReturn(Collections.singletonList(alerta));

        mockMvc.perform(get("/api/sucursales/alertas/sucursal/{sucursalId}/producto/{productoId}", sucursalId, productoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].productoId").value(productoId))
                .andExpect(jsonPath("$[0].sucursalId").value(sucursalId))
                .andExpect(jsonPath("$[0].mensaje").value("Stock bajo"));

        logger.info("Test para listar alertas finalizado");
    }
}
