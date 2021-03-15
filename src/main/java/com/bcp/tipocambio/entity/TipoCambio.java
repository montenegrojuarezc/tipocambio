package com.bcp.tipocambio.entity;

import javax.persistence.*;

@Entity
@Table(name = "TASA_CAMBIO")
public class TipoCambio {

    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_moneda_origen")
    private Monedas origen;
    @ManyToOne
    @JoinColumn(name = "id_moneda_destino")
    private Monedas destino;
    private Double valor;

    public TipoCambio() {
    }

    public TipoCambio(Integer id, Monedas origen, Monedas destino, Double valor) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Monedas getOrigen() {
        return origen;
    }

    public void setOrigen(Monedas origen) {
        this.origen = origen;
    }

    public Monedas getDestino() {
        return destino;
    }

    public void setDestino(Monedas destino) {
        this.destino = destino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
