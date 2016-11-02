package entidadeTest;

import entidade.Pessoa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class PessoaTest {

    private Pessoa p, p2;
    
    @Before
    public void setUp() {
       p = new Pessoa("Bárbara", 12345678);
       p2 = new Pessoa("", -1);
    }


    @Test
    public void alteracaoTest() {
        Assert.assertEquals("Bárbara", p.getNome());
        Assert.assertEquals(12345678, p.getTelefone());
        p.setNome("Carolina");
        p.setTelefone(87654321);
        Assert.assertEquals("Carolina", p.getNome());
        Assert.assertEquals(87654321, p.getTelefone());
    }


    @Test
    public void testVerificarNomeDoPessoaNulo(){
        //cenário onde se verifica se o nome do técnico é nulo
        Assert.assertTrue(p2.getNome().equals(""));
       
    }

    @Test
    public void testVerificaTelefoneDoPessoaNegativo() {
        //cenário onde se verifica se o telefone do técnico é negativo
        Assert.assertTrue(p2.getTelefone() < 0);     
    }
}
