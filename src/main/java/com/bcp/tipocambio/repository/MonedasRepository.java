package com.bcp.tipocambio.repository;

import com.bcp.tipocambio.entity.Monedas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonedasRepository extends JpaRepository<Monedas,Integer> {
}
