package com.github.SarahTumenas.restintermediario.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.SarahTumenas.restintermediario.entity.SoldadoEntity;
import controller.SoldadoController;
import controller.reponse.SoldadoListResponse;
import controller.reponse.SoldadoResponse;
import org.springframework.hateoas.Link;

import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ResourceSoldado {


    private ObjectMapper objectMapper;

    public ResourceSoldado(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public SoldadoListResponse criarLink(SoldadoEntity soldadoEntity){

        SoldadoListResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoListResponse.class);

        Link link = linkTo(methodOn(SoldadoController.class).buscarSoldado(soldadoEntity.getId())).withSelfRel();

        soldadoListResponse.add(link);

        return soldadoListResponse;

    }

    public SoldadoResponse criarLinkDetalhe(SoldadoEntity soldadoEntity){

        Link link;

        SoldadoResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoResponse.class);

        if (soldadoEntity.getStatus().equals("Morto")){
            link = linkTo(methodOn(SoldadoController.class).deletarSoldado(soldadoEntity.getId()))
                    .withRel("remover")
                    .withTitle("Deletar soldado")
                    .withType("delete");
        } else if (soldadoEntity.getStatus().equals("Vivo")) {
            link = linkTo(methodOn(SoldadoController.class).patrulharCastelo(soldadoEntity.getId()))
                    .withRel("patrulha")
                    .withTitle("Patrulhar Castelo")
                    .withType("put");
        } else {
            link = linkTo(methodOn(SoldadoController.class).buscarSoldado(soldadoEntity.getId())).withSelfRel();
        }

        soldadoListResponse.add(link);

        return soldadoListResponse;

    }

}
