/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.Chamado;
import entidade.ClienteEmpresa;
import entidade.Empresa;
import entidade.Pessoa;
import entidade.RegistroChamado;
import entidade.Tecnico;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana Carolina
 */
public class ControleChamadosTest {

    private ControleChamados controleChamados;
    private final long time_max = 1000;

    private ClienteEmpresa ce;
    private Tecnico t;
    private Chamado c;

    public ControleChamadosTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        ce = new ClienteEmpresa(001, new Empresa(1010, "Dell"), 00000000000, "Bárbara", 12341234);
        t = new Tecnico("João", 43214321);
        c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi", 1, t, ce, "Windows", "10", "wireless", "192.168.1.1");
        
        try (FileOutputStream fos = new FileOutputStream("chamados.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            HashMap<Integer, Chamado> cacheChamados = new HashMap<>();

            cacheChamados.put(1, new Chamado("Problema de Banco de Dados", "Dados de clientes duplicados", 4, t, ce, "Linux", "15.9", "SQL"));

            oos.writeObject(cacheChamados);

            oos.flush();
            fos.flush();

            oos.close();
        }

        try (FileOutputStream fosR = new FileOutputStream("registroChamados.dat")) {
            ObjectOutputStream oosR = new ObjectOutputStream(fosR);

            HashMap<Integer, RegistroChamado> cacheRegistros = new HashMap<>();

            cacheRegistros.put(1, new RegistroChamado("Sem serviço de rede", c, t));

            oosR.writeObject(cacheRegistros);

            oosR.flush();
            fosR.flush();

            oosR.close();
        }
        
    }

    @After
    public void tearDown() {
        File fileChamado = new File("chamados.dat");
        fileChamado.delete();

        File fileRegistro = new File("registroChamados.dat");
        fileRegistro.delete();

    }

    @Test(timeout = time_max)
    public void testIncuirNovoChamadoDeRede() {
        controleChamados = new ControleChamados();

        //cenário onde se inclui um novo chamado na base de dados(chamados.dat)
        Chamado chCadastrado = controleChamados.InserirChamadoRede("Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "wireless", "192.168.1.1");

        assertEquals("Problema de Rede", chCadastrado.getTitulo());
        assertEquals("Sem serviço de conexão wi-fi", chCadastrado.getDescricao());
        assertEquals(1, chCadastrado.getPrioridade());
        assertEquals(t, chCadastrado.getTecnico());
        assertEquals(ce, chCadastrado.getCliente());
        assertEquals("Windows", chCadastrado.getSistemaOperacional());
        assertEquals("10", chCadastrado.getSistemaOperacional());
        assertEquals("wireless", chCadastrado.getTipoConexao());
        assertEquals("192.168.1.1", chCadastrado.getEnderecoRede());
    }

    @Test(timeout = time_max)
    public void testIncuirNovoChamadoDeBancoDeDados() {
        controleChamados = new ControleChamados();

        //cenário onde se inclui um novo chamado na base de dados(chamados.dat)
        Chamado chCadastrado = controleChamados.InserirChamadoBancoDeDados("Problema de Banco de Dados", "Dados de clientes duplicados",
                4, t, ce, "Linux", "15.9", "SQL");
        assertEquals("Problema de Banco de Dados", chCadastrado.getTitulo());
        assertEquals("Dados de clientes duplicados", chCadastrado.getDescricao());
        assertEquals(4, chCadastrado.getPrioridade());
        assertEquals(t, chCadastrado.getTecnico());
        assertEquals(ce, chCadastrado.getCliente());
        assertEquals("Linux", chCadastrado.getSistemaOperacional());
        assertEquals("15.9", chCadastrado.getVersaoSO());
        assertEquals("SQL", chCadastrado.getBancoDeDados());
    }

    @Test(timeout = time_max)
    public void testIncuirNovoChamadoDeOperacao() {
        controleChamados = new ControleChamados();

        //cenário onde se inclui um novo chamado na base de dados(chamados.dat)
        Chamado chCadastrado = controleChamados.InserirChamadoDesempenho("Problema de Impressão", "Impressão extremamente lenta",
                3, t, ce, "Linux", "15.9", "Impressão lenta", 20.10);
        assertEquals("Problema de Impressão", chCadastrado.getTitulo());
        assertEquals("Impressão extremamente lenta", chCadastrado.getDescricao());
        assertEquals(3, chCadastrado.getPrioridade());
        assertEquals(t, chCadastrado.getTecnico());
        assertEquals(ce, chCadastrado.getCliente());
        assertEquals("Linux", chCadastrado.getSistemaOperacional());
        assertEquals("15.9", chCadastrado.getVersaoSO());
        assertEquals("Impressão lenta", chCadastrado.getOperacao());
        assertEquals(20.10, chCadastrado.getDuracaoOperacao(), 0.0);
    }

    @Test(timeout = time_max)
    public void testEditarChamado() {
        controleChamados = new ControleChamados();

        //cenário onde se edita um chamado já existente na base de dados
        Chamado chCadastrado = controleChamados.alterarChamado(c, c.getStatus(), c.getCausaProblema(), c.getSolucaoProblema());
        ce.setCodigo(100);
        chCadastrado.setStatus("Resolvido");
        chCadastrado.setCausaProblema("Erro de conexão");
        chCadastrado.setSolucaoProblema("Instalação de novo roteador");

        assertEquals("Resolvido", chCadastrado.getStatus());
        assertEquals("Erro de conexão", chCadastrado.getCausaProblema());
        assertEquals("Instalação de novo roteador", chCadastrado.getSolucaoProblema());

    }

    @Test(timeout = time_max)
    public void testLerChamado() {
        controleChamados = new ControleChamados();

        //cenário onde se lê um chamado que se encontra na base de dados
        Chamado chCadastrado = controleChamados.InserirChamadoRede("Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "wireless", "192.168.1.1");

        controleChamados.buscaPeloCodigo(chCadastrado.getCodigo());

        Assert.fail("Uma excessão é lançada ao realizar o método, assim não funciona corretamente!");

    }

    @Test(timeout = time_max)
    public void testValidarQuantidadeDeChamadosExistentes() {
        //cenário onde valida as empresas existentes na base de dados

        Empresa e = new Empresa(123456789, "Facebook");
        ce = new ClienteEmpresa(2, e, 1234567, "Bárbara", 123456789);
        c = new Chamado("Problema de Banco de Dados", "Dados de clientes duplicados",
                4, t, ce, "Linux", "15.9", "SQL");

        int i = controleChamados.validarQtdChamados(ce);

        Assert.assertEquals(1, i);
    }

}
