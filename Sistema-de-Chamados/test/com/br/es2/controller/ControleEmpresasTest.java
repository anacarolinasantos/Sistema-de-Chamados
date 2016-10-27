package com.br.es2.controller;

import com.br.es2.model.entities.Empresa;
import java.io.File;
import org.junit.After;
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

    @After
    public void tearDown() {
        File file = new File("empresas.dat");
        file.delete();
    }

    @Test
    public void testBuscaEmpresaExistente() {
        //cenário onde a Empresa buscada existe na base de dados (no arquivo empresas.dat)
        ce.inserir(100001, "Mackenzie");

        Empresa empresaRetornada = ce.retorna(100001, "Mackenzie");

        assertEquals("Mackenzie", empresaRetornada.getNomeEmpresa());
        assertEquals(100001, empresaRetornada.getNumeroContrato());
    }

    @Test
    public void testBuscaEmpresaQueNaoExiste() {
        //cenário onde a Empresa não existe na base de dados
        assertNull(ce.retorna(200000, "Mack"));
    }

    @Test
    public void testVerificaNomeENumeroContratoEmUsoNaMesmaEmpresaERetorna1() {
        //cenário onde não é possivel cadastrar a Empresa, pois número do contrato e o nome da empresa ja estao em uso
        //método deve retorna 1
        ce.inserir(100002, "Citrosuco");

        assertEquals(1, ce.validar(100002, "Citrosuco"));
    }

    @Test
    public void testVerificaNomeENumeroContratoEmUsoEmEmpresasDiferentesERetorna1() {
        //cenário onde não é possivel cadastrar a Empresa, pois número do contrato e o nome da empresa ja estao em uso
        //método deve retorna 1
        ce.inserir(100003, "Del Valle");
        ce.inserir(100004, "Facebook");

        assertEquals(1, ce.validar(100003, "Facebook"));
    }

    @Test
    public void testVerificaNomeEmUsoERetorna2() {
        //cenário onde não é possivel cadastrar a Empresa, pois nome da empresa ja esta em uso
        //método retorna 2
        ce.inserir(100005, "Forever 21");

        assertEquals(2, ce.validar(200000, "Forever 21"));

    }

    @Test
    public void testVerificaNumeroContratoEmUsoERetorna3() {
        //cenário onde não é possivel cadastrar a Empresa, pois numero de contrato ja esta em uso
        //método retorna 3
        ce.inserir(100006, "Cerrefour");

        assertEquals(3, ce.validar(100006, "IBM"));
    }

    @Test
    public void testVerificaNomeENumeroContratoDisponivelERetorna4() {
        //cenário onde é possível cadastrar a Empresa e metódo retorna 4
        assertEquals(4, ce.validar(100007, "Lenovo"));
    }

    @Test
    public void testInserirEmpresaJaCadastrada() {
        //cenário que a Empresa já foi cadastrada na base de dados 
        //método deve retornar null
        Empresa empCadastrada = ce.inserir(100008, "Skype");
        
        assertNull(ce.inserir(empCadastrada.getNumeroContrato(), empCadastrada.getNomeEmpresa()));
    }

    @Test
    public void testInserirEmpresaCorretamente() {
        //cenário onde a Empresa ainda não foi cadastrada na base de dados 
        //método deve retornar uma empresa que acabou de ser cadastrada
        Empresa empCadastrada = ce.inserir(100009, "WhatsApp");
        
        assertEquals(100009, empCadastrada.getNumeroContrato());
        assertEquals("WhatsApp", empCadastrada.getNomeEmpresa());
    }

    //método checar faz a mesma coisa que método validar
    @Test
    public void testChecarEmpresaExistente() {
        //cenário onde a Empresa existe na base de dados
        //método deve retornar true
        Empresa empCadastrada = ce.inserir(100010, "Apple");
        
        assertTrue(ce.checar(empCadastrada.getNumeroContrato(), empCadastrada.getNomeEmpresa()));
    }

    @Test
    public void testChecarEmpresaInexistente() {
        //cenário onde a Empresa não existe na base de dados
        //método deve retornar false
        Empresa empNova = new Empresa(100011, "LG");
        
        assertFalse(ce.checar(empNova.getNumeroContrato(), empNova.getNomeEmpresa()));
    }
}
