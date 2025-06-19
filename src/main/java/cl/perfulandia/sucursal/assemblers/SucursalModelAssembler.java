package cl.perfulandia.sucursal.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cl.perfulandia.sucursal.controller.SucursalController;
import cl.perfulandia.sucursal.modelo.Sucursal;
/*
 * Ensamblador de modelos para la entidad Sucursal.
 * Este ensamblador convierte una entidad Sucursal en un EntityModel<Sucursal>
 */
@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {
    /*
     * Método que convierte una entidad Sucursal en un EntityModel<Sucursal>.
     * Este método agrega enlaces HATEOAS a la entidad Sucursal para facilitar la navegación
     */
    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Sucursal> toModel(Sucursal sucursal) {
        /*
         * Enlaces HATEOAS para la entidad Sucursal.
         * - Enlace para obtener la sucursal por ID
         */
        Link selfLink = linkTo(methodOn(SucursalController.class).obtenerSucursalPorId(sucursal.getSucursalId()))
                        .withRel("Obtener Sucursal por ID").withType("GET").withTitle("Obtener Sucursal por ID");
        /*
         * Enlaces adicionales para la entidad Sucursal.
         * - Enlace para obtener todas las sucursales
         */
        Link allLink = linkTo(methodOn(SucursalController.class).obteneSucursales())
                        .withRel("Todas las sucursales").withType("GET").withTitle("Obtener Todas las Sucursales");
        /*
         * Enlaces para crear, eliminar y registrar movimientos en la sucursal.
         * - Enlace para crear una nueva sucursal
         * - Enlace para eliminar la sucursal por ID
         * - Enlace para registrar un movimiento en inventario
         */
        Link crearLink = linkTo(methodOn(SucursalController.class).guardarSucursal(sucursal))
                        .withRel("Agregar Sucursal").withType("POST").withTitle("Agregar Nueva Sucursal");

        Link eliminarLink = linkTo(methodOn(SucursalController.class).eliminarSucursal(sucursal.getSucursalId()))
        .withRel("Eliminar Sucursal").withType("DELETE").withTitle("Eliminar Sucursal");

        Link crearMovimientoLink = linkTo(methodOn(SucursalController.class).registrarMovimiento(null))
        .withRel("Registrar Movimiento").withType("POST").withTitle("Registrar Movimiento en Inventario");

        // Retornar el EntityModel con todos los enlaces
        return EntityModel.of(sucursal, selfLink, allLink, crearLink, eliminarLink, crearMovimientoLink);
    }
}
