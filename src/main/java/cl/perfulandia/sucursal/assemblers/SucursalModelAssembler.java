package cl.perfulandia.sucursal.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cl.perfulandia.sucursal.controller.SucursalController;
import cl.perfulandia.sucursal.modelo.Sucursal;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Sucursal> toModel(Sucursal sucursal) {
        // Construir los enlaces
        Link selfLink = linkTo(methodOn(SucursalController.class).obtenerSucursalPorId(sucursal.getSucursalId()))
                        .withRel("Obtener Sucursal por ID").withType("GET").withTitle("Obtener Sucursal por ID");
        // Enlace para obtener todas las sucursales y agregar una nueva sucursal
        Link allLink = linkTo(methodOn(SucursalController.class).obteneSucursales())
                        .withRel("Todas las sucursales").withType("GET").withTitle("Obtener Todas las Sucursales");
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
