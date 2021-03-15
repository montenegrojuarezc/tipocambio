package com.bcp.tipocambio.repository;

import com.bcp.tipocambio.entity.Monedas;
import com.bcp.tipocambio.entity.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;


@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Integer> {
    List<TipoCambio> findByOrigenAndDestino(Monedas origen, Monedas destino);
}
