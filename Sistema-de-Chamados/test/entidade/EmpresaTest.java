package entidade;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bárbara
 */
public class EmpresaTest {
    
    private Empresa e;
    
    @Test
    public void testCriarEmpresaComDadosValidos() {
        //cenário onde há a criação de uma nova Empresa 
        //com número de contrato e nome válidos 
        e = new Empresa(1010, "Mackenzie");
        assertEquals(1010, e.getNumeroContrato());
        assertEquals("Mackenzie", e.getNomeEmpresa());
    }

    @Test
    public void testEditarEmpresaComDadosValidos() {
        //cenário onde há alteração de dados 
        //da Empresa(número de contrato e nome)
        e = new Empresa(1010, "Mackenzie");
        e.setNomeEmpresa("Dell");
        e.setNumeroContrato(1020);
        assertEquals(1020, e.getNumeroContrato());
        assertEquals("Dell", e.getNomeEmpresa());
        
    }

    @Test(expected = Exception.class)
    public void testCriarEmpresaComNomeNulo() throws Exception {
        //cenário onde se cria um empresa com nome nulo
        e = new Empresa(1030, "Motorola");
        
    }
}