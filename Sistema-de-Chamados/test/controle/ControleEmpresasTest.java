package controle;

import controle.ControleEmpresas;
import entidade.Empresa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana Carolina
 */
public class ControleEmpresasTest {

    private ControleEmpresas ce;
    private final long time_max = 100;
    
    public ControleEmpresasTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("empresas.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            HashMap<Long, Empresa> cache = new HashMap<>();

            cache.put((long) 100001, new Empresa(100001, "Mackenzie"));

            oos.writeObject(cache);

            oos.flush();
            fos.flush();

            oos.close();
        }
    }

    @After
    public void tearDown() {
        File file = new File("empresas.dat");
        file.delete();
    }

    @Test(timeout = time_max)
    public void testBuscaEmpresaExistente() {
        ce = new ControleEmpresas();
        
        //cenário onde a Empresa buscada existe na base de dados (no arquivo empresas.dat)
        Empresa empresaRetornada = ce.retorna(100001, "Mackenzie");

        assertEquals("Mackenzie", empresaRetornada.getNomeEmpresa());
        assertEquals(100001, empresaRetornada.getNumeroContrato());
    }

    @Test(timeout = time_max)
    public void testBuscaEmpresaQueNaoExiste() {
        ce = new ControleEmpresas();
        
        //cenário onde a Empresa não existe na base de dados
        assertNull(ce.retorna(200000, "Mack"));
    }

    @Test(timeout = time_max)
    public void testVerificaNomeENumeroContratoEmUsoNaMesmaEmpresaERetorna1() {
        ce = new ControleEmpresas();
        
        //cenário onde não é possivel cadastrar a Empresa, pois número do contrato e o nome da empresa ja estao em uso
        //método deve retorna 1
        Empresa emp = ce.inserir(100002, "Citrosuco");

        assertEquals(1, ce.validar(emp.getNumeroContrato(), emp.getNomeEmpresa()));
    }

    @Test(timeout = time_max)
    public void testVerificaNomeENumeroContratoEmUsoEmEmpresasDiferentesERetorna1() {
        ce = new ControleEmpresas();
        
        //cenário onde não é possivel cadastrar a Empresa, pois número do contrato e o nome da empresa ja estao em uso
        //método deve retorna 1
        Empresa emp1 = ce.inserir(100003, "Del Valle");
        Empresa emp2 = ce.inserir(100004, "Facebook");

        assertEquals(1, ce.validar(emp1.getNumeroContrato(), emp2.getNomeEmpresa()));
    }

    @Test(timeout = time_max)
    public void testVerificaNomeEmUsoERetorna2() {
        ce = new ControleEmpresas();
        
        //cenário onde não é possivel cadastrar a Empresa, pois nome da empresa ja esta em uso
        //método retorna 2
        Empresa emp = ce.inserir(100005, "Forever 21");

        assertEquals(2, ce.validar(200000, emp.getNomeEmpresa()));

    }

    @Test(timeout = time_max)
    public void testVerificaNumeroContratoEmUsoERetorna3() {
        ce = new ControleEmpresas();
        
        //cenário onde não é possivel cadastrar a Empresa, pois numero de contrato ja esta em uso
        //método retorna 3
        Empresa emp = ce.inserir(100006, "Cerrefour");

        assertEquals(3, ce.validar(emp.getNumeroContrato(), "IBM"));
    }

    @Test(timeout = time_max)
    public void testVerificaNomeENumeroContratoDisponivelERetorna4() {
        ce = new ControleEmpresas();
        
        //cenário onde é possível cadastrar a Empresa e metódo retorna 4
        assertEquals(4, ce.validar(100007, "Lenovo"));
    }

    @Test(timeout = time_max)
    public void testInserirEmpresaJaCadastrada() {
        ce = new ControleEmpresas();
        
        //cenário que a Empresa já foi cadastrada na base de dados 
        //método deve retornar null
        Empresa empCadastrada = ce.inserir(100008, "Skype");

        assertNull(ce.inserir(empCadastrada.getNumeroContrato(), empCadastrada.getNomeEmpresa()));
    }

    @Test(timeout = time_max)
    public void testInserirEmpresaCorretamente() {
        ce = new ControleEmpresas();
        
        //cenário onde a Empresa ainda não foi cadastrada na base de dados 
        //método deve retornar uma empresa que acabou de ser cadastrada
        Empresa empCadastrada = ce.inserir(100009, "WhatsApp");

        assertEquals(100009, empCadastrada.getNumeroContrato());
        assertEquals("WhatsApp", empCadastrada.getNomeEmpresa());
    }
    
    @Test(expected = Exception.class, timeout = time_max)
    public void testInserirEmpresaComNomeNulo() {
        ce = new ControleEmpresas();
        
        //cenário onde a Empresa está sendo cadastrada com nome com valor nulo 
        //método deve retornar uma exceção
        ce.inserir(100010, null);
    }
    
    @Test(expected = Exception.class, timeout = time_max)
    public void testInserirEmpresaComNumeroContratoNegativo() {
        ce = new ControleEmpresas();
        
        //cenário onde a Empresa está sendo cadastrada com número do contrato com valor negativo 
        //método deve retornar uma exceção
        ce.inserir(-1, "Burger King");
    }

    //método checar faz a mesma coisa que método validar
    @Test(timeout = time_max)
    public void testChecarEmpresaExistente() {
        ce = new ControleEmpresas();
        
        //cenário onde a Empresa existe na base de dados
        //método deve retornar true
        Empresa empCadastrada = ce.inserir(100012, "Apple");

        assertTrue(ce.checar(empCadastrada.getNumeroContrato(), empCadastrada.getNomeEmpresa()));
    }

    @Test(timeout = time_max)
    public void testChecarEmpresaInexistente() {
        ce = new ControleEmpresas();
        
        //cenário onde a Empresa não existe na base de dados
        //método deve retornar false
        Empresa empNova = new Empresa(100013, "LG");

        assertFalse(ce.checar(empNova.getNumeroContrato(), empNova.getNomeEmpresa()));
    }
}
