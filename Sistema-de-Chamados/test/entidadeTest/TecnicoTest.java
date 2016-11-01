package entidadeTest;

import entidade.Tecnico;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Bárbara
 */
public class TecnicoTest {

    private Tecnico t, t2;

    public TecnicoTest() {
        t = new Tecnico("Bárbara", 12345678);
        t2 = new Tecnico("", -12345678);
        
    }

    @Test
    public void testCadastrarTecnico() {
        //cenário onde se cadastra um novo técnico
        assertEquals("Bárbara", t.getNome());
        assertEquals(12345678, t.getTelefone());
    }

    @Test
    public void testEditarTecnico() {
        //cenário onde se edita os dados do técnico
        t.setNome("Carolina");
        t.setTelefone(87654321);
        assertEquals("Carolina", t.getNome());
        assertEquals(87654321, t.getTelefone());
    }
   
    @Test
    public void testVerificarNomeDoTecnicoNulo(){
    //cenário onde se verifica se o nome do técnico é nulo
        assertTrue(t2.getNome().equals(""));
    }
    
    
    @Test
    public void testVerificaTelefoneNegativo(){
        //cenário onde se verifica se o telefone é negativo
        assertTrue(t2.getTelefone() < 0);
    }
}
