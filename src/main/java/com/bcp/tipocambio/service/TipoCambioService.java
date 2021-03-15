package com.bcp.tipocambio.service;

import com.bcp.tipocambio.entity.CambioResponse;
import com.bcp.tipocambio.entity.Monedas;
import com.bcp.tipocambio.entity.TipoCambio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoCambioService {

    public Mono<TipoCambio> save(TipoCambio tipoCambio);
    public Mono<TipoCambio> findById(Integer id);
    public Flux<TipoCambio> findAll();
    public Mono<CambioResponse> calculateChange(CambioResponse cambio);
}
