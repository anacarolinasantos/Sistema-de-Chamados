package com.br.es2.controller;

import com.br.es2.model.entities.Empresa;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana Carolina
 */
public class ControleEmpresasTest {

    public ControleEmpresas ce;

    public ControleEmpresasTest() {
    }

    @Before
    public void setUp() {
        ce = new ControleEmpresas();
    }

    @Test
    public void testBuscaEmpresaExistente() {
        //cenário onde a Empresa buscada existe na base de dados (no arquivo empresas.dat)
        Empresa esperado = ce.retorna(1, "Mackenzie");

        assertEquals("Mackenzie", esperado.getNomeEmpresa());
        assertEquals(1, esperado.getNumeroContrato());
    }

    @Test
    public void testBuscaEmpresaQueNaoExiste() {
        //cenário onde a Empresa não existe na base de dados
        Empresa esperado = ce.retorna(2, "Universidade");

        assertEquals(null, esperado);
    }

    @Test
    public void testVerificaNomeENumeroContratoEmUsoERetorna1() {
        //cenário onde não é possivel cadastrar a Empresa, pois número do contrato e o nome da empresa ja estao em uso
        //método retorna 1
    }

    @Test
    public void testVerificaNomeEmUsoERetorna2() {
        //cenário onde não é possivel cadastrar a Empresa, pois nome da empresa ja esta em uso
        //método retorna 2
    }

    @Test
    public void testVerificaNumeroContratoEmUsoERetorna3() {
        //cenário onde não é possivel cadastrar a Empresa, pois numero de contrato ja esta em uso
        //método retorna 3
    }

    @Test
    public void testVerificaNomeENumeroContratoDisponivelERetorna4() {
        //cenário onde é possível cadastrar a Empresa e metódo retorna 4
    }

    @Test
    public void testInserirEmpresaJaCadastrada() {
        //cenário que a Empresa já foi cadastrada na base de dados 
        //método deve retornar null
    }

    @Test
    public void testInserirEmpresaCorretamente() {
        //cenário onde a Empresa ainda não foi cadastrada na base de dados 
        //método deve retornar uma empresa que acabou de ser cadastrada
    }

    //método checar faz a mesma coisa que método validar
    @Test
    public void testChecarEmpresaExistente() {
        //cenário onde a Empresa existe na base de dados
        //método deve retornar true
    }

    @Test
    public void testChecarEmpresaInexistente() {
        //cenário onde a Empresa não existe na base de dados
        //método deve retornar false
    }
}
