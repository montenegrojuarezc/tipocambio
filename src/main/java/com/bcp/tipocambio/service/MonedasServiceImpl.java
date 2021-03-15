package com.bcp.tipocambio.service;

import com.bcp.tipocambio.entity.Monedas;
import com.bcp.tipocambio.repository.MonedasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MonedasServiceImpl implements MonedasService{

    @Autowired
    private MonedasRepository monedasRepository;

    @Override
    public Flux<Monedas> findAll() {
        return Flux.fromIterable(monedasRepository.findAll());
    }

    @Override
    public Mono<Monedas> findById(Integer id) {
        try {
            return Mono.just(monedasRepository.findById(id).get());
        }catch(Exception e) {
            return Mono.empty();
        }
    }

    @Override
    public Mono<Monedas> save(Monedas monedas) {
        return Mono.just(monedasRepository.save(monedas));
    }
}
