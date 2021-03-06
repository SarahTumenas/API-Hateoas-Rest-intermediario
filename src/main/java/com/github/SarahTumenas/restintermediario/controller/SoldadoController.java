package com.github.SarahTumenas.restintermediario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.SarahTumenas.restintermediario.controller.reponse.SoldadoResponse;
import com.github.SarahTumenas.restintermediario.controller.request.SoldadoEditRequest;
import com.github.SarahTumenas.restintermediario.dto.Soldado;
import com.github.SarahTumenas.restintermediario.service.SoldadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Resources;

@RestController
@RequestMapping("/v1/soldado")
public class SoldadoController {

    private SoldadoService soldadoService;
    private ObjectMapper objectMapper;

    public SoldadoController(SoldadoService soldadoService, ObjectMapper objectMapper) {
        this.soldadoService = soldadoService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoldadoResponse> buscarSoldado(@PathVariable() Long id) {
        SoldadoResponse soldadoResponse = soldadoService.buscarSoldado(id);
        return ResponseEntity.status(HttpStatus.OK).body(soldadoResponse);
    }

    @PostMapping
    public ResponseEntity criarSoldado(@RequestBody Soldado soldado) {
        soldadoService.criarSoldado(soldado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editarSoldado(@PathVariable() Long id,
                                        @RequestBody SoldadoEditRequest soldadoEditRequest) {
        soldadoService.alterarSoldado(id, soldadoEditRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarSoldado(@PathVariable Long id) {
        soldadoService.deletarSoldado(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity patrulharCastelo(@PathVariable Long id) {
        //soldadoService.deletarSoldado(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Resources<SoldadoListResponse>> buscarSoldados() {
        Resources<SoldadoListResponse>  soldados = soldadoService.buscarSoldados();
        return ResponseEntity.status(HttpStatus.OK).body(soldados);
    }
}
