package cl.perfulandia.sucursal.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MovimientoRequestDtoTest.java
 * Clase de prueba para la clase MovimientoRequest.
 * Verifica el correcto funcionamiento de los métodos getters, setters, equals, hashCode y canEqual.
 */
class MovimientoRequestDtoTest {
    /**
     * Test para verificar que los métodos getters y setters funcionan correctamente.
     * Crea una instancia de MovimientoRequest, establece valores y verifica que los getters devuelvan los valores correctos.
     */
    @Test
    void testGettersAndSetters() {
        MovimientoRequest request = new MovimientoRequest();
        MovimientoRequest.ProductoDTO producto = new MovimientoRequest.ProductoDTO();

        Long sucursalId = 1L;
        Long productoId = 2L;
        Integer cantidad = 10;
        String tipo = "SALIDA";

        producto.setId(productoId);
        request.setSucursalId(sucursalId);
        request.setProducto(producto);
        request.setCantidad(cantidad);
        request.setTipo(tipo);

        assertEquals(sucursalId, request.getSucursalId());
        assertEquals(producto, request.getProducto());
        assertEquals(productoId, request.getProducto().getId());
        assertEquals(cantidad, request.getCantidad());
        assertEquals(tipo, request.getTipo());
    }
    /**
     * Test para verificar que los métodos equals, hashCode y canEqual funcionan correctamente.
     * Crea dos instancias de MovimientoRequest con los mismos valores y verifica que son iguales.
     * También verifica que dos instancias diferentes no son iguales.
     */
    @Test
    void testProductoDtoEqualsHashCodeAndCanEqual() {
        MovimientoRequest.ProductoDTO prod1 = new MovimientoRequest.ProductoDTO();
        prod1.setId(1L);

        MovimientoRequest.ProductoDTO prod2 = new MovimientoRequest.ProductoDTO();
        prod2.setId(1L);

        MovimientoRequest.ProductoDTO prod3 = new MovimientoRequest.ProductoDTO();
        prod3.setId(2L);

        // equals
        assertEquals(prod1, prod2);
        assertNotEquals(prod1, prod3);

        // hashCode
        assertEquals(prod1.hashCode(), prod2.hashCode());
        assertNotEquals(prod1.hashCode(), prod3.hashCode());

        // canEqual
        assertTrue(prod1.canEqual(prod2));
        assertFalse(prod1.canEqual("no es un ProductoDTO"));
    }
}
