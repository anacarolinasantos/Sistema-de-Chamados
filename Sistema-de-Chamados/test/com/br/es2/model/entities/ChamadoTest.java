package com.br.es2.model.entities;

import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class ChamadoTest {

    @Test
    public void TestCadastrarChamado() {
        //cenário para a criação de um chamado
    }

    @Test
    public void testEditarChamado() {
        //cenário de edição de um chamado
    }

    @Test(expected = Exception.class)
    public void testVerificarTituloDoChamadoENulo() throws Exception {
        //cenário onde se verifica se o número de chamado existe e se é nulo
    }

    @Test(expected = Exception.class)
    public void testVerificarDescricaoDoChamadoENulo() throws Exception {
        //cenário onde se verifica se a descrição do chamado existe e se é nulo
    }

    @Test(expected = Exception.class)
    public void testVerificarPrioridadeDoChamadoENegativa() throws Exception {
        //cenário onde se verifica se a prioridade do chamado existe e se é negativa
    }

    @Test(expected = Exception.class)
    public void testVerificarNomeSstemaOperacionalDoChamadoENulo() throws Exception {
        //cenário onde se verifica se o nome do Sistema Operacional existe e se é nulo
    }

    @Test(expected = Exception.class)
    public void testVerificarVersaoDoSistemaOperacionalENulo() throws Exception {
        //cenário onde se verifica se a versão do Sistema Operacional existe e se é nula
    }

    @Test(expected = Exception.class)
    public void testVerificarTipoDaConexaoENulo() throws Exception {
        //cenário onde se verifica se a conexão existe e se é nula
    }

    @Test(expected = Exception.class)
    public void testVerificarEnderecoDeRedeENulo() throws Exception {
        //cenário onde se verifica se o endereço de rede existe e se é nulo
    }
}
