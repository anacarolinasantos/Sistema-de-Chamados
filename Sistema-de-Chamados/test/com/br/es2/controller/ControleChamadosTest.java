/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.es2.controller;

import com.br.es2.model.entities.Chamado;
import com.br.es2.model.entities.ClienteEmpresa;
import com.br.es2.model.entities.RegistroChamado;
import com.br.es2.model.entities.Tecnico;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 31525962
 */
public class ControleChamadosTest {
    
    public ControleChamadosTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testAlterarChamadoExistente() {
        
    }
    
    @Test
    public void testAlterarChamadoInexistente() {
        
    }
    
    @Test
    public void testInserirChamadoRede() {
        
    }
    
    @Test
    public void testInserirChamadoBancoDeDados() {
        
    }
    
    @Test
    public void testInserirChamadoDesempenho() {
        
    }
    
    @Test
    public void testInserirRegistroChamado() {
        
    }
    
    @Test
    public void testCadastrarChamado() {
        System.out.println("cadastrarChamado");
        ControleChamados instance = new ControleChamados();
        instance.cadastrarChamado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    @Test
    public void testVoltaChamadoCodigo() {
        System.out.println("voltaChamadoCodigo");
        Integer codigo = null;
        ControleChamados instance = new ControleChamados();
        Chamado expResult = null;
        Chamado result = instance.voltaChamadoCodigo(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testSetChamadoAlterado() {
        System.out.println("setChamadoAlterado");
        Chamado c = null;
        ControleChamados instance = new ControleChamados();
        instance.setChamadoAlterado(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetChamadoAlterado() {
        System.out.println("getChamadoAlterado");
        ControleChamados instance = new ControleChamados();
        Chamado expResult = null;
        Chamado result = instance.getChamadoAlterado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testEmitirRelatorios() {
        System.out.println("emitirRelatorios");
        int tipoproblema = 0;
        ControleChamados instance = new ControleChamados();
        String expResult = "";
        String result = instance.emitirRelatorios(tipoproblema);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testBuscaPeloCodigo() {
        System.out.println("buscaPeloCodigo");
        int codigoChamado = 0;
        ControleChamados instance = new ControleChamados();
        Chamado expResult = null;
        Chamado result = instance.buscaPeloCodigo(codigoChamado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testRetornaDetalhesChamado() {
        System.out.println("retornaDetalhesChamado");
        Chamado c = null;
        ControleChamados instance = new ControleChamados();
        String expResult = "";
        String result = instance.retornaDetalhesChamado(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testAlterarChamado_0args() {
        System.out.println("alterarChamado");
        ControleChamados instance = new ControleChamados();
        instance.alterarChamado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testAbrirRelatorio() {
        System.out.println("abrirRelatorio");
        ControleChamados instance = new ControleChamados();
        instance.abrirRelatorio();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testValidarQtdChamados() {
        System.out.println("validarQtdChamados");
        ClienteEmpresa cliente = null;
        ControleChamados instance = new ControleChamados();
        int expResult = 0;
        int result = instance.validarQtdChamados(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
