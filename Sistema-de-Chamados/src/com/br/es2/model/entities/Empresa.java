package com.br.es2.model.entities;

import java.io.Serializable;

/**
 *
 * @author Richard
 */
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    private long numeroContrato;
    private String nomeEmpresa;

    public Empresa(long numeroContrato, String nomeEmpresa) {
        this.numeroContrato = numeroContrato;
        this.nomeEmpresa = nomeEmpresa;
    }

    public long getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(long numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    @Override
    public String toString() {
        return numeroContrato + " - " + nomeEmpresa;
    }

}
