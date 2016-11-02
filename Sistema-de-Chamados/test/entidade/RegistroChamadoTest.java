package entidade;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class RegistroChamadoTest {

    private Empresa e;
    private Pessoa p;
    private Tecnico t;
    private ClienteEmpresa ce;
    private Chamado c;
    private RegistroChamado rc;

    @Before
    public void setUp() {
        e = new Empresa(1010, "Dell");
        p = new Pessoa("Bárbara", 12341234);
        t = new Tecnico("João", 43214321);
        ce = new ClienteEmpresa(123, e, 00000000000, p.getNome(), p.getTelefone());
        c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi",
                1, t, ce, "Windows", "10", "wireless", "192.168.1.1");
    }

    @Test
    public void testCriarRegistroChamadoComDadosValidos() {
        //cenário onde se cria um novo registro de chamado
        rc = new RegistroChamado("Sem serviço de rede", c, t);

        assertEquals("Sem serviço de rede", rc.getAssunto());
        //Asserção do Chamado
        assertEquals("Problema de Rede", c.getTitulo());
        assertEquals("Sem serviço de conexão wi-fi", c.getDescricao());
        assertEquals(1, c.getPrioridade());
        assertEquals("Windows", c.getSistemaOperacional());
        assertEquals("10", c.getVersaoSO());
        assertEquals("wireless", c.getTipoConexao());
        assertEquals("192.168.1.1", c.getEnderecoRede());
        //Asserção do Cliente
        assertEquals(ce, c.getCliente());
        //Asserção do Técnico
        assertEquals("João", t.getNome());
        assertEquals(43214321, t.getTelefone());

    }

    @Test
    public void testEditarRegistroChamadoComDadosValidos() {
        //cenário onde se edita os dados do registro de chamado
        t = new Tecnico("Ana", 44214321);
        c = new Chamado("Problema de Banco de Dados", "Dados de clientes duplicados",
                4, t, ce, "Linux", "15.9", "SQL");
        rc = new RegistroChamado("Sem serviço de rede", c, t);
        rc.setAssunto("Banco de Dados hackeado");
        rc.setChamado(c);
        rc.setTecnico(t);

        assertEquals("Banco de Dados hackeado", rc.getAssunto());
        //Asserção do Chamado
        assertEquals("Dados de clientes duplicados", c.getDescricao());
        assertEquals(4, c.getPrioridade());
        assertEquals(t, c.getTecnico());
        assertEquals(ce, c.getCliente());
        assertEquals("Linux", c.getSistemaOperacional());
        assertEquals("15.9", c.getVersaoSO());
        assertEquals("SQL", c.getBancoDeDados());
        //Asserção do Técnico
        assertEquals("Ana", t.getNome());
        assertEquals(44214321, t.getTelefone());

    }

    @Test(expected = Exception.class)
    public void testCriarAssuntoDoRegistroChamadoNulo() throws Exception {
        //cenário onde se cria assunto do registro de chamado nulo
        rc = new RegistroChamado("", c, t);
    }
}
