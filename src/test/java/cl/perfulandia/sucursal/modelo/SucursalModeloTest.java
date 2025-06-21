package cl.perfulandia.sucursal.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SucursalModeloTest.java
 * Clase de prueba para la clase Sucursal.
 * Verifica el correcto funcionamiento de los métodos getters, setters, equals, hashCode y canEqual.
 */
class SucursalModeloTest {
    /**
     * Test para verificar que los métodos getters y setters funcionan correctamente.
     * Crea una instancia de Sucursal, establece valores y verifica que los getters devuelvan los valores correctos.
     */
    @Test
    void testGettersAndSetters() {
        Sucursal sucursal = new Sucursal();
        Long id = 1L;
        String nombre = "Sucursal Centro";
        String direccion = "Calle Falsa 123";
        String telefono = "123456789";
        String correo = "centro@sucursal.cl";

        sucursal.setSucursalId(id);
        sucursal.setNombre(nombre);
        sucursal.setDireccion(direccion);
        sucursal.setTelefono(telefono);
        sucursal.setCorreo(correo);

        assertEquals(id, sucursal.getSucursalId());
        assertEquals(nombre, sucursal.getNombre());
        assertEquals(direccion, sucursal.getDireccion());
        assertEquals(telefono, sucursal.getTelefono());
        assertEquals(correo, sucursal.getCorreo());
    }
    /**
     * Test para verificar que los métodos equals, hashCode y canEqual funcionan correctamente.
     * Crea dos instancias de Sucursal con los mismos valores y verifica que son iguales.
     * También verifica que dos instancias diferentes no son iguales.
     */
    @Test
    void testEqualsHashCodeAndCanEqual() {
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setSucursalId(1L);
        sucursal1.setNombre("Sucursal Centro");
        sucursal1.setDireccion("Calle Falsa 123");
        sucursal1.setTelefono("123456789");
        sucursal1.setCorreo("centro@sucursal.cl");

        Sucursal sucursal2 = new Sucursal();
        sucursal2.setSucursalId(1L);
        sucursal2.setNombre("Sucursal Centro");
        sucursal2.setDireccion("Calle Falsa 123");
        sucursal2.setTelefono("123456789");
        sucursal2.setCorreo("centro@sucursal.cl");

        Sucursal sucursal3 = new Sucursal();
        sucursal3.setSucursalId(2L);

        // equals
        assertEquals(sucursal1, sucursal2);
        assertNotEquals(sucursal1, sucursal3);

        // hashCode
        assertEquals(sucursal1.hashCode(), sucursal2.hashCode());
        assertNotEquals(sucursal1.hashCode(), sucursal3.hashCode());

        // canEqual
        assertTrue(sucursal1.canEqual(sucursal2));
        assertFalse(sucursal1.canEqual("no es un Sucursal"));
    }
}
