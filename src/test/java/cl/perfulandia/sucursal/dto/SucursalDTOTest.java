package cl.perfulandia.sucursal.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SucursalDTOTest.java
 * Clase de prueba para la clase SucursalDTO.
 * Verifica el correcto funcionamiento de los métodos getters, setters, equals, hashCode y canEqual.
 */
class SucursalDTOTest {
    /**
     * Test para verificar que los métodos getters y setters funcionan correctamente.
     * Crea una instancia de SucursalDTO, establece valores y verifica que los getters devuelvan los valores correctos.
     */
    @Test
    void testAllArgsConstructorAndGetters() {
        Long id = 1L;
        String nombre = "Sucursal Centro";
        String direccion = "Calle Falsa 123";
        String telefono = "123456789";
        String correo = "centro@sucursal.cl";

        SucursalDTO dto = new SucursalDTO(id, nombre, direccion, telefono, correo);

        assertEquals(id, dto.getSucursalId());
        assertEquals(nombre, dto.getNombre());
        assertEquals(direccion, dto.getDireccion());
        assertEquals(telefono, dto.getTelefono());
        assertEquals(correo, dto.getCorreo());
    }
    /**
     * Test para verificar que el constructor sin argumentos y los setters funcionan correctamente.
     * Crea una instancia de SucursalDTO, establece valores a través de los setters y verifica que los getters devuelvan los valores correctos.
     */
    @Test
    void testNoArgsConstructorAndSetters() {
        SucursalDTO dto = new SucursalDTO();

        dto.setSucursalId(2L);
        dto.setNombre("Sucursal Norte");
        dto.setDireccion("Avenida Siempre Viva 742");
        dto.setTelefono("987654321");
        dto.setCorreo("norte@sucursal.cl");

        assertEquals(2L, dto.getSucursalId());
        assertEquals("Sucursal Norte", dto.getNombre());
        assertEquals("Avenida Siempre Viva 742", dto.getDireccion());
        assertEquals("987654321", dto.getTelefono());
        assertEquals("norte@sucursal.cl", dto.getCorreo());
    }
    /**
     * Test para verificar que los métodos equals, hashCode y canEqual funcionan correctamente.
     * Crea dos instancias de SucursalDTO con los mismos valores y verifica que son iguales.
     * También verifica que dos instancias diferentes no son iguales.
     */
    @Test
    void testEqualsAndHashCode() {
        SucursalDTO dto1 = new SucursalDTO(1L, "Sucursal", "Dir", "123", "mail@mail.com");
        SucursalDTO dto2 = new SucursalDTO(1L, "Sucursal", "Dir", "123", "mail@mail.com");
        SucursalDTO dto3 = new SucursalDTO(2L, "Otra", "Otra", "456", "otro@mail.com");

        // equals
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());

        // canEqual
        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual("no es un SucursalDTO"));
    }
}
