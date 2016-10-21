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
    public void testRetornaEmpresaExistente() {
        //cenário onde a Empresa buscada existe na base de dados (no arquivo empresas.dat)
        Empresa esperado = ce.retorna(1, "Mackenzie");
        
        assertEquals("Mackenzie", esperado.getNomeEmpresa());
        assertEquals(1, esperado.getNumeroContrato());
    }
    
    @Test
    public void testRetornaEmpresaInexistente() {
        //cenário onde a Empresa não existe na base de dados
        Empresa esperado = ce.retorna(2, "Universidade");
        
        assertEquals(null, esperado);
    }
    
    @Test
    public void testValidarRetorno1() {
        //cenário onde não é possivel cadastrar a Empresa, pois número do contrato e o nome da empresa ja estao em uso
        //método retorna 1
    }
    
    @Test
    public void testValidarRetorno2() {
        //cenário onde não é possivel cadastrar a Empresa, pois nome da empresa ja esta em uso
        //método retorna 2
    }
    
    @Test
    public void testValidarRetorno3() {
        //cenário onde não é possivel cadastrar a Empresa, pois numero de contrato ja esta em uso
        //método retorna 3
    }
    
    @Test
    public void testValidarRetorno4() {
        //cenário onde é possível cadastrar a Empresa e metódo retorna 4
    }
    
    //método checar faz a mesma coisa que método validar
    //método validar não é utilizado 

    @Test
    public void testInserirCadastrada() {
        //cenário que a Empresa já foi cadastrada na base de dados 
        //método deve retornar null
    }
    
    @Test
    public void testInserirCorretamente() {
        //cenário onde a Empresa ainda não foi cadastrada na base de dados 
        //método deve retornar uma empresa que acabou de ser cadastrada
    }

    @Test
    public void testChecarTrue() {
        //cenário onde a Empresa existe na base de dados
        //método deve retornar true
    }
    
    @Test
    public void testChecarFalse() {
        //cenário onde a Empresa não existe na base de dados
        //método deve retornar false
    }
}
