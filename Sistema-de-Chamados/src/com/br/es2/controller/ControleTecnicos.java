package com.br.es2.controller;

import com.br.es2.model.TecnicoDAO;
import com.br.es2.view.TelaCadastroTecnico;
import com.br.es2.model.entities.Tecnico;

/**
 *
 * @author Richard
 */
public class ControleTecnicos implements IControlador {

    private TelaCadastroTecnico telaCadastroTecnico;
    private TecnicoDAO tecnicoDAO;

    public ControleTecnicos() {

        this.tecnicoDAO = new TecnicoDAO();

    }

    @Override
    public Tecnico inserir(long n, String nome) {
        Tecnico tec = new Tecnico(nome, n);
        tecnicoDAO.put(tec);
        return tec;
    }

    public void cadastrarTecnico() {
        this.telaCadastroTecnico = new TelaCadastroTecnico(this);
        this.telaCadastroTecnico.setVisible(true);
    }

    public void fecharTelaTecnico() {
        this.telaCadastroTecnico.setVisible(false);
    }

}
