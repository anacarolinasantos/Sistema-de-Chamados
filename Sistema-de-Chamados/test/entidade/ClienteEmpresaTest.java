package entidade;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class ClienteEmpresaTest {

    private ClienteEmpresa ce;
    private Empresa e = new Empresa(1010, "Microsoft");

    Integer codigo = 100;

    @Test
    public void testCriarClienteEmpresaComDadosInvalidos() {
        //cenário onde se cadastra um novo cliente na Empresa com dados válidos
        ce = new ClienteEmpresa(codigo, e, 00000000000, "Larissa", 12345678);
        assertEquals(codigo, ce.getCodigo());
        assertEquals(e, ce.getEmpresa());
        assertEquals(00000000000, ce.getCpf());
        assertEquals("Larissa", ce.getNome());
        assertEquals(12345678, ce.getTelefone());
    }

    @Test
    public void testEditarClienteEmpresa() {
        //cenário onde se edita os dados de um cliente da Empresa
        ce = new ClienteEmpresa(codigo, e, 00000000000, "Larissa", 12345678);
        codigo = 101;

        ce.setCodigo(codigo);
        ce.setCpf(00000000001);
        ce.setNome("Luan");
        ce.setTelefone(87654321);
        assertEquals(codigo, ce.getCodigo());
        assertEquals(00000000001, ce.getCpf());
        assertEquals("Luan", ce.getNome());
        assertEquals(87654321, ce.getTelefone());
    }

    @Test(expected = Exception.class)
    public void testCriarCodigoClienteEmpresaNegativo(){
        //cenário onde se verifica se o CPF do cliente da Empresa é negativo
        codigo = -100;
        ce = new ClienteEmpresa(codigo, e,-00000000000, "Larissa", 12345678);

    }
    
    @Test(expected = Exception.class)
    public void testCriarCPFDoClienteEmpresaNegativo(){
        //cenário onde se verifica se o CPF do cliente da Empresa é negativo
        ce = new ClienteEmpresa(codigo, e,-00000000000, "Larissa", 12345678);

    }
    
    @Test(expected = Exception.class)
    public void testCriarClienteEmpresaComNomeNulo(){
        //cenário onde se verifica se o código do cliente da Empresa é negativo
        ce = new ClienteEmpresa(codigo, e, 00000000000, "", 12345678);
        
    }
    
    @Test(expected = Exception.class)
    public void testCriarTelefoneoClienteEmpresaNegativo(){
        //cenário onde se verifica se o CPF do cliente da Empresa é negativo
        ce = new ClienteEmpresa(codigo, e,00000000000, "Larissa", -12345678);

    }
}
