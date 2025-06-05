package cl.perfulandia.sucursal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import cl.perfulandia.sucursal.modelo.Sucursal;
import cl.perfulandia.sucursal.repository.SucursalRepository;

@SpringBootTest
@ActiveProfiles("test")
public class SucursalServiceTest {

    @Autowired
    private SucursalService sucursalService;

    @MockBean
    private SucursalRepository sucursalRepository;

    @Test
    public void testGetSucursal() {
        // GIVEN
        List<Sucursal> sucursales = new ArrayList<>();

        // WHEN
        when(sucursalRepository.findAll()).thenReturn(sucursales);

        // THEN
        List<Sucursal> result = sucursalService.obtenerSucursales();

        assertEquals(sucursales, result);
        assertEquals(0, result.size());
    }
}

