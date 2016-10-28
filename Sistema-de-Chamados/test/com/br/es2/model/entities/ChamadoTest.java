package com.br.es2.model.entities;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class ChamadoTest {

    @Test
    public void TestCadastrarChamado() {
        //cenário para a criação de um chamado
        Empresa e = new Empresa(1010, "Dell");
        Pessoa p = new Pessoa("Bárbara", 12341234);
        Tecnico t = new Tecnico("João", 43214321);
        ClienteEmpresa ce = new ClienteEmpresa(001, e, 00000000000, p.getNome(), p.getTelefone());
        Chamado c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "wireless", "192.168.1.1");

        assertEquals("Problema de Rede", c.getTitulo());
        assertEquals("Sem serviço de conexão wi-fi", c.getDescricao());
        assertEquals(1, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Windows", c.getSistemaOperacional());
        assertEquals("wireless", c.getTipoConexao());
        assertEquals("192.168.1.1", c.getEnderecoRede());

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
