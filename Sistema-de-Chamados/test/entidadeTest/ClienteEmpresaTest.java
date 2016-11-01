package entidadeTest;

import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class ClienteEmpresaTest {

    @Test
    public void testCadastrarClienteEmpresa() {
        //cenário onde se cadastra um novo cliente na Empresa
    }

    @Test
    public void testEditarClienteEmpresa() {
        //cenário onde se edita os dados de um cliente da Empresa
    }

    @Test(expected = Exception.class)
    public void testVerificarCodigoDeClienteEmpresaENegativo() throws Exception {
        //cenário onde se verifica se o código do cliente da Empresa é negativo
    }

    @Test(expected = Exception.class)
    public void testVerificarCPFDoClienteEmpresaENegativo() throws Exception {
        //cenário onde se verifica se o CPF do cliente da Empresa é negativo
    }

    @Test(expected = Exception.class)
    public void testVerificarCPFDoClienteEmpresaPossui11Numeros() throws Exception {
        //cenário onde se verifica se o CPF do cliente da Empresa possui onze digitos
    }
}
