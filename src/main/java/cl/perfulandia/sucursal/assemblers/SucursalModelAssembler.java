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
    public EntityModel<Sucursal> toModel(Sucursal sucursal) {
        // Aquí puedes construir el EntityModel para la sucursal
        Link selfLink = linkTo(methodOn(SucursalController.class).obtenerSucursalPorId(sucursal.getSucursalId())).withSelfRel();
        Link allLink = linkTo(methodOn(SucursalController.class).obteneSucursales()).withRel("Sucursales");
        return EntityModel.of(sucursal);
    }


}
