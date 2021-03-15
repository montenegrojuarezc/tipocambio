package com.bcp.tipocambio.service;

import com.bcp.tipocambio.entity.Monedas;
import com.bcp.tipocambio.entity.TipoCambio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonedasService {
    public Flux<Monedas> findAll();
    public Mono<Monedas> findById(Integer id);
    public Mono<Monedas> save(Monedas monedas);
}
