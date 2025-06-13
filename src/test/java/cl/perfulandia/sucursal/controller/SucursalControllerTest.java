package cl.perfulandia.sucursal.controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SucursalController.class)
class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SucursalService sucursalService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);
    @Test
    void testControllerObteneSucursales() throws Exception {
        logger.info("Iniciando test para obtener sucursales");
        Sucursal sucursal = new Sucursal();
        Mockito.when(sucursalService.obtenerSucursales()).thenReturn(Collections.singletonList(sucursal));
        logger.info("Mockeando el servicio para obtener sucursales");
        mockMvc.perform(get("/api/sucursales/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
                logger.info("Test para obtener sucursales finalizado");
    }

    @Test
    void testControllerObtenerSucursalPorId() throws Exception {
        logger.info("Iniciando test para obtener sucursal por ID");
        Mockito.reset(sucursalService); // Resetear el mock para evitar interferencias
        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        Mockito.when(sucursalService.obtenerSucursalPorId(1L)).thenReturn(sucursal);
        logger.info("Mockeando el servicio para obtener sucursal por ID");
        // Realizar la petición GET y verificar la respuesta
        mockMvc.perform(get("/api/sucursales/sucursal/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucursalId").value(1L));
        logger.info("Test para obtener sucursal por ID finalizado");
    }

    @Test
    void testControllerGuardarSucursal() throws Exception {
        logger.info("Iniciando test para guardar sucursal");
        Mockito.reset(sucursalService); // Resetear el mock para evitar interferencias
        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        sucursal.setNombre("Sucursal Test");
        Mockito.when(sucursalService.guardarSucursal(any(Sucursal.class))).thenReturn(sucursal);
        logger.info("Mockeando el servicio para guardar sucursal");
        // Realizar la petición POST y verificar la respuesta
        mockMvc.perform(post("/api/sucursales/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucursalId").value(1L))
                .andExpect(jsonPath("$.nombre").value("Sucursal Test"));
                logger.info("Test para guardar sucursal finalizado");
    }

    @Test
    void testControllerEliminarSucursal() throws Exception {
        logger.info("Iniciando test para eliminar sucursal");
        Mockito.reset(sucursalService); // Resetear el mock para evitar interferencias  
        Mockito.doNothing().when(sucursalService).eliminarSucursal(1L);
        logger.info("Mockeando el servicio para eliminar sucursal");
        // Realizar la petición DELETE y verificar la respuesta
        mockMvc.perform(delete("/api/sucursales/eliminar/1"))
                .andExpect(status().isOk());
        logger.info("Test para eliminar sucursal finalizado");  
    }

    @Test
    void testControllerRegistrarMovimiento() throws Exception {
        logger.info("Iniciando test para registrar movimiento en inventario");
        Mockito.reset(sucursalService); // Resetear el mock para evitar interferencias
        MovimientoRequest request = new MovimientoRequest();
        MovimientoDTO response = new MovimientoDTO();
        Mockito.when(sucursalService.registrarMovimientoEnInventario(any(MovimientoRequest.class))).thenReturn(response);
        logger.info("Mockeando el servicio para registrar movimiento en inventario");
        // Realizar la petición POST y verificar la respuesta
        mockMvc.perform(post("/api/sucursales/movimiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
                logger.info("Test para registrar movimiento en inventario finalizado");
    }
}
