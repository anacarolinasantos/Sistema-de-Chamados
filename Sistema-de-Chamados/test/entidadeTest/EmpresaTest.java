package entidadeTest;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class EmpresaTest {

    @Test
    public void testCadastrarEmpresa() {
        //cenário onde há a criação de uma nova Empresa
    }

    @Test
    public void testEditarEmpresa() {
        // cenário onde há alteração de dados da Empresa(nome e número de contrato)
    }

    @Test(expected = NullPointerException.class)
    public void testVerificaNomeEmpresa() throws Exception {
        //cenário onde há a verificação do nome de Empresa existe 
    }

    @Test(expected = NullPointerException.class)
    public void testVerificaNumeroDeContratoNegativo() throws Exception {
        //cenário onde se verifica se o número de contrato da Empresa é negativo
    }
}
