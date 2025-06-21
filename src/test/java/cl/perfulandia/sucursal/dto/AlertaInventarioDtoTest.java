package cl.perfulandia.sucursal.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
/**
 * AlertaInventarioTest.java
 * Clase de prueba para la clase AlertaInventario.
 * Verifica el correcto funcionamiento de los métodos getters, setters, equals, hashCode y canEqual.
 */
class AlertaInventarioTest {
    /**
     * Test para verificar que los métodos getters y setters funcionan correctamente.
     * Crea una instancia de AlertaInventario, establece valores y verifica que los getters devuelvan los valores correctos.
     */
    @Test
    void testGettersAndSetters() {
        AlertaInventario alerta = new AlertaInventario();
        Long id = 1L;
        Long productoId = 2L;
        Long sucursalId = 3L;
        int stockActual = 10;
        String mensaje = "Stock bajo";
        LocalDateTime fechaHora = LocalDateTime.now();

        alerta.setId(id);
        alerta.setProductoId(productoId);
        alerta.setSucursalId(sucursalId);
        alerta.setStockActual(stockActual);
        alerta.setMensaje(mensaje);
        alerta.setFechaHora(fechaHora);

        assertEquals(id, alerta.getId());
        assertEquals(productoId, alerta.getProductoId());
        assertEquals(sucursalId, alerta.getSucursalId());
        assertEquals(stockActual, alerta.getStockActual());
        assertEquals(mensaje, alerta.getMensaje());
        assertEquals(fechaHora, alerta.getFechaHora());
    }
    /**
     * Test para verificar que los métodos equals, hashCode y canEqual funcionan correctamente.
     * Crea dos instancias de AlertaInventario con los mismos valores y verifica que son iguales.
     * También verifica que dos instancias diferentes no son iguales.
     */
    @Test
    void testEqualsHashCodeAndCanEqual() {
        LocalDateTime fecha = LocalDateTime.now();
        AlertaInventario dto1 = new AlertaInventario();
        dto1.setId(1L);
        dto1.setProductoId(2L);
        dto1.setSucursalId(3L);
        dto1.setStockActual(10);
        dto1.setMensaje("Alerta");
        dto1.setFechaHora(fecha);

        AlertaInventario dto2 = new AlertaInventario();
        dto2.setId(1L);
        dto2.setProductoId(2L);
        dto2.setSucursalId(3L);
        dto2.setStockActual(10);
        dto2.setMensaje("Alerta");
        dto2.setFechaHora(fecha);

        AlertaInventario dto3 = new AlertaInventario();
        dto3.setId(99L);

        // equals
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());

        // canEqual
        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual("no es un AlertaInventario"));
    }

}
