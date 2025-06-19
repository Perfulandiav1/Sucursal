package cl.perfulandia.sucursal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
/**
 * Clase principal de la aplicación Sucursal.
 * Esta clase inicia la aplicación Spring Boot y habilita el uso de Feign para realizar llamadas a otros servicios.
 * 
 * @author Perfulandia
 * @version 1.0
 * @since 1.0
 */
public class SucursalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SucursalApplication.class, args);
	}

}
