package cl.perfulandia.sucursal.controller;

import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.service.SucursalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

    @Test
    void testControllerObteneSucursales() throws Exception {
        Sucursal sucursal = new Sucursal();
        Mockito.when(sucursalService.obtenerSucursales()).thenReturn(Collections.singletonList(sucursal));

        mockMvc.perform(get("/api/sucursales/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testControllerObtenerSucursalPorId() throws Exception {
        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        Mockito.when(sucursalService.obtenerSucursalPorId(1L)).thenReturn(sucursal);

        mockMvc.perform(get("/api/sucursales/sucursal/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucursalId").value(1L));
    }

    @Test
    void testControllerGuardarSucursal() throws Exception {
        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        sucursal.setNombre("Sucursal Test");
        Mockito.when(sucursalService.guardarSucursal(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(post("/api/sucursales/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucursalId").value(1L))
                .andExpect(jsonPath("$.nombre").value("Sucursal Test"));
    }

    @Test
    void testControllerEliminarSucursal() throws Exception {
        Mockito.doNothing().when(sucursalService).eliminarSucursal(1L);

        mockMvc.perform(delete("/api/sucursales/eliminar/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testControllerRegistrarMovimiento() throws Exception {
        MovimientoRequest request = new MovimientoRequest();
        MovimientoDTO response = new MovimientoDTO();
        Mockito.when(sucursalService.registrarMovimientoEnInventario(any(MovimientoRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/sucursales/movimiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
