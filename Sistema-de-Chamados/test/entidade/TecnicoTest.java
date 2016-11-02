package entidade;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class TecnicoTest {

    private Tecnico t;

    @Test
    public void testCriarTecnicoComDadosValidos() {
        //cenário onde se cria um técnico com nome e telefone corretos
        t = new Tecnico("Bárbara", 12345678);
        assertEquals("Bárbara", t.getNome());
        assertEquals(12345678, t.getTelefone());
    }

    @Test
    public void testEditarTecnicoComDadosValidos() {
        //cenário onde se edita um técnico com nome e telefone corretos
        t = new Tecnico("Bárbara", 12345678);
        t.setNome("Carolina");
        t.setTelefone(87654321);
        assertEquals("Carolina", t.getNome());
        assertEquals(87654321, t.getTelefone());
    }
    
    @Test(expected = Exception.class)
    public void testCriarTecnicoComNomeNulo(){
        //cenário onde se verifica se o nome do técnico é nulo
        t = new Tecnico("", 12345678);
    }
    
     @Test(expected = Exception.class)
    public void testCriarTecnicoComTelefoneNegativo() {
        //cenário onde se verifica se o telefone do técnico é negativo
        t = new Tecnico("Piera", -1);     
    }
}
