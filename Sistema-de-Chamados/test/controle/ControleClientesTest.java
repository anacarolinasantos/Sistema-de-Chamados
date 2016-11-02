package controle;

import entidade.ClienteEmpresa;
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
public class ControleClientesTest {

    public ControleClientes cc;

    public ControleClientesTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("clientes.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            HashMap<Long, ClienteEmpresa> cache = new HashMap<>();

            String cpf = "22824265760";

            cache.put(Long.parseLong(cpf), new ClienteEmpresa(1, new Empresa(100001, "Mackenzie"), Long.parseLong(cpf), "Paula", 37666570));

            oos.writeObject(cache);

            oos.flush();
            fos.flush();

            oos.close();
        }
    }

    @After
    public void tearDown() {
        File file = new File("clientes.dat");
        file.delete();
    }

    @Test
    public void testInserirNovoCliente() {
        cc = new ControleClientes();

        //cenário onde o Cliente ainda não foi cadastrado na base de dados 
        //método deve retornar um cliente que acabou de ser cadastrado
        String cpf = "52998224725";
        ClienteEmpresa cliente = cc.incluiNovoCliente(new Empresa(100009, "WhatsApp"), Long.parseLong(cpf), "Barbara", 37616315);

        assertEquals(100009, cliente.getEmpresa().getNumeroContrato());
        assertEquals("WhatsApp", cliente.getEmpresa().getNomeEmpresa());
        assertEquals(Long.parseLong(cpf), cliente.getCpf());
        assertEquals("Barbara", cliente.getNome());
        assertEquals(37616315, cliente.getTelefone());
    }

    @Test
    public void testInserirClienteJaCadastrado() {
        cc = new ControleClientes();

        //cenário que o Cliente já foi cadastrado na base de dados 
        //método deve retornar null
        String cpf = "22824265760";

        assertNull(cc.incluiNovoCliente(new Empresa(100001, "Mackenzie"), Long.parseLong(cpf), "Paula", 37666570));
    }

    @Test(expected = Exception.class)
    public void testInserirClienteComNomeNulo() {
        cc = new ControleClientes();

        //cenário onde o Cliente está sendo cadastrada com nome com valor nulo 
        //método deve retornar uma exceção
        String cpf = "64669833154";
        cc.incluiNovoCliente(new Empresa(100008, "Skype"), Long.parseLong(cpf), "", 37666432);
    }

    @Test(expected = Exception.class)
    public void testInserirClienteComEmpresaNula() {
        cc = new ControleClientes();
        
        //cenário onde o Cliente está sendo cadastrada com empresa com valor nulo 
        //método deve retornar uma exceção
        String cpf = "36274447520";
        cc.incluiNovoCliente(null, Long.parseLong(cpf), "Ana Carolina", 37666571);
    }

    @Test(expected = Exception.class)
    public void testInserirClienteComCPFComTamanhoIncorreto() {
        cc = new ControleClientes();
        
        //cenário onde o Cliente está sendo cadastrada com cpf com tamanho incorreto 
        //método deve retornar uma exceção
        cc.incluiNovoCliente(new Empresa(100012, "Apple"), 362744475, "Paloma", 37816466);
    }

    @Test(expected = Exception.class)
    public void testInserirClienteComCPFInvalidoComTamanhoCorreto() {
        cc = new ControleClientes();
        
        //cenário onde o Cliente está sendo cadastrada com cpf inválido (não está de acordo com a especificação do Ministério da Fazenda)
        //método deve retornar uma exceção
        String cpf = "36274446520";
        cc.incluiNovoCliente(new Empresa(100013, "LG"), Long.parseLong(cpf), "Piera", 28639713);
    }
}
