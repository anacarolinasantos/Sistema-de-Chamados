package entidade;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class RegistroChamadoTest {

    private RegistroChamado rc;

    @Test
    public void testCadastrarRegistroChamadoComDadosValidos() {
        //cenário onde se cadastra um novo registro de chamado
    }

    @Test
    public void testEditarRegistroChamadoComDadosValidos() {
        //cenário onde se edita os dados do registro de chamado
    }

    @Test(expected = Exception.class)
    public void testCriarAssuntoDoRegistroChamadoNulo() throws Exception {
        //cenário onde se verifica se o assunto do registro de chamado é nulo
    }
}
