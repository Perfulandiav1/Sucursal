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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import cl.perfulandia.sucursal.dto.MovimientoDTO;
import cl.perfulandia.sucursal.dto.MovimientoRequest;
import cl.perfulandia.sucursal.feing.InventarioClient;
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
    public void testObtenerSucursales() {
        // GIVEN
        List<Sucursal> sucursales = new ArrayList<>();

        // WHEN
        when(sucursalRepository.findAll()).thenReturn(sucursales);

        // THEN
        List<Sucursal> result = sucursalService.obtenerSucursales();

        assertEquals(sucursales, result);
        assertEquals(0, result.size());
    }

    @Test
    public void testObtenerSucursalPorId() {
        Long id = 1L;
        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(id);

        when(sucursalRepository.findById(id)).thenReturn(Optional.of(sucursal));

        Sucursal result = sucursalService.obtenerSucursalPorId(id);

        assertNotNull(result);
        assertEquals(id, result.getSucursalId());
    }

    @Test
    public void testGuardarSucursal() {
        Sucursal sucursal = new Sucursal();
        Long sucursalId = 1L;
        sucursal.setSucursalId(sucursalId);
        sucursal.setNombre("Sucursal Test");
        sucursal.setDireccion("123 Test St");
        sucursal.setTelefono("123456789");
        sucursal.setCorreo("afaf");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(sucursalRepository.save(sucursal)).thenReturn(sucursal);

        // Llama al método save() del servicio.
        Sucursal saved = sucursalService.guardarSucursal(sucursal);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertEquals(sucursal.getSucursalId(), saved.getSucursalId());
        assertEquals(sucursal.getNombre(), saved.getNombre());
        assertEquals(sucursal.getDireccion(), saved.getDireccion());
        assertEquals(sucursal.getTelefono(), saved.getTelefono());
        assertEquals(sucursal.getCorreo(), saved.getCorreo());
    }

    @Test
    public void testEliminarSucursal() {
        Long id = 1L;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(sucursalRepository).deleteById(id);

        // Llama al método deleteByCodigo() del servicio.
        sucursalService.eliminarSucursal(id);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(sucursalRepository, times(1)).deleteById(id);
    }

    @Test
    void testRegistrarMovimientoEnInventario() {
        // Mock de dependencias
        SucursalRepository sucursalRepository = mock(SucursalRepository.class);
        InventarioClient inventarioClient = mock(InventarioClient.class);

        // Instancia del servicio con el mock de repository
        SucursalService sucursalService = new SucursalService(sucursalRepository);
        // Inyecta el mock de InventarioClient usando reflexión (ya que es private)
        java.lang.reflect.Field field;
        try {
            field = SucursalService.class.getDeclaredField("inventarioClient");
            field.setAccessible(true);
            field.set(sucursalService, inventarioClient);
        } catch (Exception e) {
            fail("No se pudo inyectar el mock de InventarioClient");
        }
        
        // Datos de prueba
        MovimientoRequest request = new MovimientoRequest();
        MovimientoDTO expectedResponse = new MovimientoDTO();

        // Define el comportamiento del mock
        when(inventarioClient.registrarMovimiento(request)).thenReturn(expectedResponse);

        // Ejecuta el método
        MovimientoDTO result = sucursalService.registrarMovimientoEnInventario(request);

        // Verifica el resultado
        assertEquals(expectedResponse, result);
        verify(inventarioClient, times(1)).registrarMovimiento(request);
    }
    
}





