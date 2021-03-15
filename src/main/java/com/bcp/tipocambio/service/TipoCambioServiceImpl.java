package com.bcp.tipocambio.service;

import com.bcp.tipocambio.entity.CambioResponse;
import com.bcp.tipocambio.entity.Monedas;
import com.bcp.tipocambio.entity.TipoCambio;
import com.bcp.tipocambio.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoCambioServiceImpl implements TipoCambioService{

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public Mono<TipoCambio> save(TipoCambio tipoCambio) {
        return Mono.just(tipoCambioRepository.save(tipoCambio));
    }

    @Override
    public Mono<TipoCambio> findById(Integer id) {
        try {
            return Mono.just(tipoCambioRepository.findById(id).get());
        }catch(Exception e) {
            return Mono.empty();
        }
    }

    @Override
    public Flux<TipoCambio> findAll() {
        return Flux.fromIterable(tipoCambioRepository.findAll());
    }

    @Override
    public Mono<CambioResponse> calculateChange(CambioResponse cambio) 	{

        try {

            return Flux.fromIterable(tipoCambioRepository.
                    findByOrigenAndDestino(cambio.getTipoCambio().getOrigen(),cambio.getTipoCambio().getDestino()))
                    .collectList()
                    .flatMap(tc -> {
                        CambioResponse cambioResponse = new CambioResponse();

                        cambioResponse.setMontoInicial(cambio.getMontoInicial());
                        cambioResponse.setMontoFinal(cambio.getMontoInicial() * tc.get(0).getValor());
                        cambioResponse.setTipoCambio(tc.get(0));

                        return Mono.just(cambioResponse);
                    });
        }catch(Exception e) {
            return Mono.empty();
        }
    }


}
