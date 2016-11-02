package entidade;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class ChamadoTest {

    private Empresa e;
    private Pessoa p;
    private Tecnico t;
    private ClienteEmpresa ce;
    private Chamado c;

    @Before
    public void setUp() {
        e = new Empresa(1010, "Dell");
        p = new Pessoa("Bárbara", 12341234);
        t = new Tecnico("João", 43214321);
        ce = new ClienteEmpresa(001, e, 00000000000, p.getNome(), p.getTelefone());
    }

    @Test
    public void TestCadastrarChamadoDeRedeComDadosValidos() {
        //cenário para a criação de um chamado com problema de rede
        c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "wireless", "192.168.1.1");
        assertEquals("Problema de Rede", c.getTitulo());
        assertEquals("Sem serviço de conexão wi-fi", c.getDescricao());
        assertEquals(1, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Windows", c.getSistemaOperacional());
        assertEquals("10", c.getVersaoSO());
        assertEquals("wireless", c.getTipoConexao());
        assertEquals("192.168.1.1", c.getEnderecoRede());
    }

    @Test
    public void TestCadastrarChamadoDeBandoDeDadosComDadosValidos() {
        //cenário para a criação de um chamado com problema de banco de dados
        c = new Chamado("Problema de Banco de Dados", "Dados de clientes duplicados",
                4, t, ce, "Linux", "15.9", "SQL");
        assertEquals("Problema de Banco de Dados", c.getTitulo());
        assertEquals("Dados de clientes duplicados", c.getDescricao());
        assertEquals(4, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Linux", c.getSistemaOperacional());
        assertEquals("15.9", c.getVersaoSO());
        assertEquals("SQL", c.getBancoDeDados());
    }

    @Test
    public void TestCadastrarChamadoDeDesempenhoComDadosValidos() {
        //cenário para a criação de um chamado com problema de desempenho
        c = new Chamado("Problema de Impressão", "Impressão extremamente lenta",
                3, t, ce, "Linux", "15.9", "Impressão lenta", 20.10);
        assertEquals("Problema de Impressão", c.getTitulo());
        assertEquals("Impressão extremamente lenta", c.getDescricao());
        assertEquals(3, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Linux", c.getSistemaOperacional());
        assertEquals("15.9", c.getVersaoSO());
        assertEquals("Impressão lenta", c.getOperacao());
        assertEquals(20.10, c.getDuracaoOperacao(), 0.0);
    }

    @Test
    public void testEditarChamadoDeRedeComDadosValidos() {
        //cenário de edição de um chamado de problema de rede com dados válidos
        t = new Tecnico("Daniel", 43218765);
        ce = new ClienteEmpresa(001, e, 00000000001, p.getNome(), p.getTelefone());
        c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "wireless", "192.168.1.1");
        ce.setCodigo(100);
        c.setTitulo("Problema de Rede2");
        c.setDescricao("Serviço de conexão wi-fi inexistente");
        c.setPrioridade(2);
        c.setTecnico(t);
        c.setCliente(ce);
        c.setSistemaOperacional("Linux");
        c.setVersaoSO("14.8");
        c.setTipoConexao("wi-fi");
        c.setEnderecoRede("192.168.2.2");

        assertEquals("Problema de Rede2", c.getTitulo());
        assertEquals("Serviço de conexão wi-fi inexistente", c.getDescricao());
        assertEquals(2, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Linux", c.getSistemaOperacional());
        assertEquals("14.8", c.getVersaoSO());
        assertEquals("wi-fi", c.getTipoConexao());
        assertEquals("192.168.2.2", c.getEnderecoRede());

    }

    @Test
    public void testEditarChamadoDeBandoDeDadosComDadosValidos() {
        //cenário de edição de um chamado de problema de banco de dados com dados válidos
        t = new Tecnico("Daniel", 43218765);
        ce = new ClienteEmpresa(001, e, 00000000001, p.getNome(), p.getTelefone());
        c = new Chamado("Problema de Banco de Dados", "Dados de clientes duplicados",
                4, t, ce, "Linux", "15.9", "SQL");
        c.setTitulo("Problema de BD");
        c.setDescricao("Dados de clientes incorretos");
        c.setPrioridade(5);
        c.setTecnico(t);
        c.setCliente(ce);
        c.setSistemaOperacional("Windows");
        c.setVersaoSO("7");
        c.setBancoDeDados("PhpMyAdmin");

        assertEquals("Problema de BD", c.getTitulo());
        assertEquals("Dados de clientes incorretos", c.getDescricao());
        assertEquals(5, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Windows", c.getSistemaOperacional());
        assertEquals("7", c.getVersaoSO());
        assertEquals("PhpMyAdmin", c.getBancoDeDados());

    }

    @Test
    public void testEditarChamadoDeDesempenhoComDadosValidos() {
        //cenário de edição de um chamado de problema de desempenho com dados válidos
        t = new Tecnico("Daniel", 43218765);
        ce = new ClienteEmpresa(001, e, 00000000001, p.getNome(), p.getTelefone());
        c = new Chamado("Problema de Impressão", "Impressão extremamente lenta",
                3, t, ce, "Linux", "15.9", "Impressão lenta", 20.10);

        c.setTitulo("Problema de Impressora");
        c.setDescricao("Impressora sem toner");
        c.setPrioridade(4);
        c.setTecnico(t);
        c.setCliente(ce);
        c.setSistemaOperacional("Windows");
        c.setVersaoSO("Vista");
        c.setOperacao("Trocar toner");
        c.setDuracaoOperacao(10.20);

        assertEquals("Problema de Impressora", c.getTitulo());
        assertEquals("Impressora sem toner", c.getDescricao());
        assertEquals(4, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Windows", c.getSistemaOperacional());
        assertEquals("Vista", c.getVersaoSO());
        assertEquals("Trocar toner", c.getOperacao());
        assertEquals(10.20, c.getDuracaoOperacao(), 0.0);

    }

    @Test(expected = Exception.class)
    public void testCriarTituloDeChamadoNulo() {
        //cenário onde se cria número de chamado nulo
        c = new Chamado("", "Impressão extremamente lenta",
                3, t, ce, "Linux", "15.9", "Impressão lenta", 20.10);
    }

    @Test(expected = Exception.class)
    public void testCriarDescricaoDeChamadoNulo() {
        //cenário onde se cria descrição do chamado nulo
        c = new Chamado("Problema de Impressão", "",
                3, t, ce, "Linux", "15.9", "Impressão lenta", 20.10);
    }

    @Test(expected = Exception.class)
    public void testCriarPrioridadeDeChamadoNegativa() {
        ////cenário onde se cria prioridade de chamado negativo
        c = new Chamado("Problema de Impressão", "Impressão extremamente lenta",
                -3, t, ce, "Linux", "15.9", "Impressão lenta", 20.10);
    }

    @Test(expected = Exception.class)
    public void testCriarNomeDeSistemaOperacionalDeChamadoNulo() throws Exception {
        //cenário onde se cria o nome do Sistema Operacional nulo
        c = new Chamado("Problema de Impressão", "Impressão extremamente lenta",
                3, t, ce, "", "15.9", "Impressão lenta", 20.10);
    }

    @Test(expected = Exception.class)
    public void testCriarVersaoDoSistemaOperacionalNulo() throws Exception {
        //cenário onde se cria versão do Sistema Operacional nula
        c = new Chamado("Problema de Impressão", "Impressão extremamente lenta",
                3, t, ce, "Linux", "", "Impressão lenta", 20.10);
    }

    @Test(expected = Exception.class)
    public void testCriarTipoDaConexaoNula() throws Exception {
        //cenário onde se cria conexão nula
        c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "", "192.168.1.1");
    }

    @Test(expected = Exception.class)
    public void testCriarEnderecoDeRedeNulo() throws Exception {
        //cenário onde se cria endereço de rede nulo
        c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "wireless", "");
    }
}
