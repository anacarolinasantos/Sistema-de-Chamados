package entidade;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bárbara
 */
public class PessoaTest {

    private Pessoa p;

    @Test
    public void testCriarPessoaComDadosValidos(){
        //cenário onde de cria uma pessoa com nome e telefone corretos
        p = new Pessoa("Júlia", 41210000);
        assertEquals("Júlia", p.getNome());
        assertEquals(41210000, p.getTelefone());
        
    }
    
    @Test
    public void testEditarPessoaComDadosValidos() {
        //cenário onde se cria uma pessoa com nome e telefone corretos
        p = new Pessoa("Júlia", 41210000);
        p.setNome("Carolina");
        p.setTelefone(87654321);
        assertEquals("Carolina", p.getNome());
        assertEquals(87654321, p.getTelefone());
    }

    @Test(expected = Exception.class)
    public void testCriarPessoaComNomeNulo(){
        //cenário onde se verifica se o nome da pessoa é nulo
        p = new Pessoa("", 12345678);
       
    }

    @Test(expected = Exception.class)
    public void testCriarPessoaComTelefoneNegativo() {
        //cenário onde se verifica se o telefone da pessoa é negativo
        p = new Pessoa("Piera", -1);     
    }
    
}
