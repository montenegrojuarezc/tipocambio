package com.bcp.tipocambio.controller;

import com.bcp.tipocambio.entity.CambioResponse;
import com.bcp.tipocambio.entity.Monedas;
import com.bcp.tipocambio.entity.TipoCambio;
import com.bcp.tipocambio.service.MonedasService;
import com.bcp.tipocambio.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("api/")
public class TipoCambioController {

    @Autowired
    private MonedasService monedasService;

    @Autowired
    private TipoCambioService tipoCambioService;

    @GetMapping("monedas")
    public Mono<ResponseEntity<Flux<Monedas>>> findAll() {
        return Mono.just(ResponseEntity.ok()
                .body(monedasService.findAll())
        );
    }

    @GetMapping("monedas/{id}")
    public Mono<ResponseEntity<Monedas>> findById(@PathVariable Integer id) {
        return monedasService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("monedas")
    public Mono<ResponseEntity<Monedas>> save(@RequestBody Monedas monedas){
        return monedasService.save(monedas).map(p ->
                ResponseEntity.created(URI.create("/api/monedas/".concat(p.getId().toString())))
                        .body(p)
        );
    }

    @GetMapping("TipoCambio")
    public Mono<ResponseEntity<Flux<TipoCambio>>> findAllCambio() {
        return Mono.just(ResponseEntity.ok()
                .body(tipoCambioService.findAll())
        );
    }

    @GetMapping("TipoCambio/{id}")
    public Mono<ResponseEntity<TipoCambio>> findByIdCambio(@PathVariable Integer id) {
        return tipoCambioService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("TipoCambio")
    public Mono<ResponseEntity<TipoCambio>> saveTipoCambio(@RequestBody TipoCambio tipoCambio){
        return tipoCambioService.save(tipoCambio).map(p ->
                ResponseEntity.created(URI.create("/api/TipoCambio/".concat(p.getId().toString())))
                        .body(p)
        );
    }

    @PutMapping("TipoCambio/{id}")
    public Mono<ResponseEntity<TipoCambio>> updateValue(@RequestBody TipoCambio tipoCambio, @PathVariable Integer id) {

        return tipoCambioService.findById(id).flatMap(tc -> {
            tc.setValor(tipoCambio.getValor());
            return tipoCambioService.save(tc);
        }).map(tc ->
                ResponseEntity.created(URI.create("/api/TipoCambio/".concat(tc.getId().toString())))
                        .body(tc)
        )
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("calcularTipoCambio")
    public Mono<ResponseEntity<CambioResponse>> calculateChange(@RequestBody CambioResponse cambioResponse)
    {
        return tipoCambioService.calculateChange(cambioResponse)
                .map(mapper -> ResponseEntity.ok()
                        .body(mapper))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
