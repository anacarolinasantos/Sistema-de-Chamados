package controleTest;

import controle.ControleTecnicos;
import controle.ControleTecnicos;
import entidade.Tecnico;
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
 * @author 31525962
 */
public class ControleTecnicosTest {
    
    public ControleTecnicos ct;
    private final long time_max = 100;
    
    public ControleTecnicosTest() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException{
        try (FileOutputStream fos = new FileOutputStream("tecnicos.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            HashMap<Integer, Tecnico> cache = new HashMap<>();

            cache.put(1, new Tecnico("Ana Carolina", 28121311));

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
    public void testInsereTecnicoCorretamente() {
        ct = new ControleTecnicos();
        
        //cenário onde o Técnico ainda não foi cadastrado na base de dados 
        //método deve retornar um técnico que acabou de ser cadastrado
        Tecnico tecCadastrado = ct.inserir(37818744, "Marcos");
        
        assertEquals(37818744, tecCadastrado.getTelefone());
        assertEquals("Marcos", tecCadastrado.getNome());
    }
    
    @Test(timeout = time_max)
    public void testInsereTecnicoJaCadastrado() {
        ct = new ControleTecnicos();
        
        //cenário que o Técnico já foi cadastrado na base de dados 
        //método deve retornar null
        assertNull(ct.inserir(28121311, "Ana Carolina")); //Técnico cadastrado no método setUp desta classe
    }
    
    @Test(expected = Exception.class, timeout = time_max)
    public void testInsereTecnicoComNomeNulo() {
        ct = new ControleTecnicos();
        
        //cenário onde o Técnico está sendo cadastrado com nome com valor nulo 
        //método deve retornar uma exceção
        ct.inserir(37611954, null);
    }
}
