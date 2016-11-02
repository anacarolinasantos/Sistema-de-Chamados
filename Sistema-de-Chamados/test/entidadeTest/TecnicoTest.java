package entidadeTest;

import entidade.Tecnico;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class TecnicoTest {

    private Tecnico t, t2;

    @Before
    public void setUp() {
       t = new Tecnico("Bárbara", 12345678);
       t2 = new Tecnico("", -1);
    }


    @Test
    public void alteracaoTest() {
        Assert.assertEquals("Bárbara", t.getNome());
        Assert.assertEquals(12345678, t.getTelefone());
        t.setNome("Carolina");
        t.setTelefone(87654321);
        Assert.assertEquals("Carolina", t.getNome());
        Assert.assertEquals(87654321, t.getTelefone());
    }


    @Test
    public void testVerificarNomeDoTecnicoNulo(){
        //cenário onde se verifica se o nome do técnico é nulo
        Assert.assertTrue(t2.getNome().equals(""));
       
    }

    @Test
    public void testVerificaTelefoneDoTecnicoNegativo() {
        //cenário onde se verifica se o telefone do técnico é negativo
        Assert.assertTrue(t2.getTelefone() < 0);     
    }
}
