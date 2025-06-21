package cl.perfulandia.sucursal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * SucursalApplicationTests.java
 * Clase de prueba para la aplicación Sucursal.
 * Verifica que la aplicación se inicie correctamente en el perfil de prueba.
 */
@SpringBootTest
@ActiveProfiles("test")
class SucursalApplicationTests {

	@Test
    void mainTest() {
        SucursalApplication.main(new String[] {});
    }

}
