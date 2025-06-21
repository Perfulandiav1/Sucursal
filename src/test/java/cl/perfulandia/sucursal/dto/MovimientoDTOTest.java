package cl.perfulandia.sucursal.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
/**
 * MovimientoDTOTest.java
 * Clase de prueba para la clase MovimientoDTO.
 * Verifica el correcto funcionamiento de los métodos getters, setters, equals, hashCode y canEqual.
 */
class MovimientoDTOTest {
    /**
     * Test para verificar que los métodos getters y setters funcionan correctamente.
     * Crea una instancia de MovimientoDTO, establece valores y verifica que los getters devuelvan los valores correctos.
     */
    @Test
    void testGettersAndSetters() {
        MovimientoDTO movimiento = new MovimientoDTO();
        Long id = 10L;
        Long sucursalId = 20L;
        Long productoId = 30L;
        Integer cantidad = 5;
        String tipo = "ENTRADA";
        LocalDateTime fecha = LocalDateTime.now();

        movimiento.setId(id);
        movimiento.setSucursalId(sucursalId);
        movimiento.setProductoId(productoId);
        movimiento.setCantidad(cantidad);
        movimiento.setTipo(tipo);
        movimiento.setFechaMovimiento(fecha);

        assertEquals(id, movimiento.getId());
        assertEquals(sucursalId, movimiento.getSucursalId());
        assertEquals(productoId, movimiento.getProductoId());
        assertEquals(cantidad, movimiento.getCantidad());
        assertEquals(tipo, movimiento.getTipo());
        assertEquals(fecha, movimiento.getFechaMovimiento());
    }
    /**
     * Test para verificar que los métodos equals, hashCode y canEqual funcionan correctamente.
     * Crea dos instancias de MovimientoDTO con los mismos valores y verifica que son iguales.
     * También verifica que dos instancias diferentes no son iguales.
     */
    @Test
    void testEqualsHashCodeAndCanEqual() {
        LocalDateTime fecha = LocalDateTime.now();
        MovimientoDTO dto1 = new MovimientoDTO();
        dto1.setId(1L);
        dto1.setSucursalId(2L);
        dto1.setProductoId(3L);
        dto1.setCantidad(5);
        dto1.setTipo("ENTRADA");
        dto1.setFechaMovimiento(fecha);

        MovimientoDTO dto2 = new MovimientoDTO();
        dto2.setId(1L);
        dto2.setSucursalId(2L);
        dto2.setProductoId(3L);
        dto2.setCantidad(5);
        dto2.setTipo("ENTRADA");
        dto2.setFechaMovimiento(fecha);

        MovimientoDTO dto3 = new MovimientoDTO();
        dto3.setId(99L);

        // equals
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());

        // canEqual
        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual("no es un MovimientoDTO"));
    }
}
