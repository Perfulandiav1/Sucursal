package cl.perfulandia.sucursal.assemblers;

import cl.perfulandia.sucursal.modelo.Sucursal;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/*
 * assemblersSucursalTest.java
 * Clase de prueba para el ensamblador de modelos de Sucursal.
 */
class assemblersSucursalTest {
    /**
     * Test para verificar que el ensamblador de modelos de Sucursal crea correctamente un EntityModel
     * con los enlaces esperados.
     */
    @Test
    void testToModelLinks() {
        // Arrange
        Sucursal sucursal = new Sucursal();
        sucursal.setSucursalId(1L);
        sucursal.setNombre("Sucursal Centro");
        sucursal.setDireccion("Calle Falsa 123");
        sucursal.setTelefono("123456789");
        sucursal.setCorreo("centro@sucursal.cl");

        SucursalModelAssembler assembler = new SucursalModelAssembler();

        // Act
        EntityModel<Sucursal> model = assembler.toModel(sucursal);

        // Assert
        assertNotNull(model);
        assertEquals(sucursal, model.getContent());

        List<Link> links = model.getLinks().toList();
        assertEquals(5, links.size()); // 6 enlaces agregados

        assertTrue(links.stream().anyMatch(link -> link.getRel().value().equals("Obtener Sucursal por ID")));
        assertTrue(links.stream().anyMatch(link -> link.getRel().value().equals("Todas las sucursales")));
        assertTrue(links.stream().anyMatch(link -> link.getRel().value().equals("Agregar Sucursal")));
        assertTrue(links.stream().anyMatch(link -> link.getRel().value().equals("Eliminar Sucursal")));
        assertTrue(links.stream().anyMatch(link -> link.getRel().value().equals("Registrar Movimiento")));
    }
}
