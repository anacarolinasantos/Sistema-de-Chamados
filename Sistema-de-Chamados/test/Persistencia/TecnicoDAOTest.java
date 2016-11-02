/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.Tecnico;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class TecnicoDAOTest {

    private TecnicoDAO tecnicoDAO;
    private HashMap<Integer, Tecnico> cache;

    public TecnicoDAOTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("tecnicos.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            cache = new HashMap<>();

            cache.put(1, new Tecnico("Ana Carolina", 28121311));

            oos.writeObject(cache);

            oos.flush();
            fos.flush();

            oos.close();
        }

        tecnicoDAO = new TecnicoDAO();
    }

    @After
    public void tearDown() {
        File file = new File("tecnicos.dat");
        file.delete();
    }

    @Test
    public void testIncluirNovoTecnicoNaBaseDeDados() throws FileNotFoundException, IOException, ClassNotFoundException {
        //cenário que inclui um técnico que ainda não foi incluido na base de dados
        Tecnico novoTec = new Tecnico("Marcos", 37818744);
        tecnicoDAO.put(novoTec);

        FileInputStream fis = new FileInputStream("tecnicos.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<Integer, Tecnico> cacheRetornado = (HashMap<Integer, Tecnico>) ois.readObject();

        ois.close();
        fis.close();

        assertEquals(novoTec.getNome(), cacheRetornado.get(2).getNome());
        assertEquals(novoTec.getTelefone(), cacheRetornado.get(2).getTelefone());
    }

    @Test(expected = Exception.class)
    public void testIncluirTecnicoJaExistenteNaBaseDeDados() {
        //cenário que tenta incluir um técnico que já foi incluido na base de dados
        Tecnico tecExistente = new Tecnico("Ana Carolina", 28121311);  //empresa adicionada no setUp dessa classe

        tecnicoDAO.put(tecExistente);
    }

    @Test
    public void testBuscarUmTecnicoIncluido() {
        //cenário que busca um técnico que já foi incluido na base de dados
        Tecnico tec = new Tecnico("Joaquim", 27620097);
        tecnicoDAO.put(tec);

        assertEquals(tec.getNome(), tecnicoDAO.voltaCashTecnico().get(2).getNome());
        assertEquals(tec.getTelefone(), tecnicoDAO.voltaCashTecnico().get(2).getTelefone());
    }

    @Test
    public void testBuscarVariosTecnicosIncluidos() {
        //cenário que busca várias empresas que já foram incluidas na base de dados
        Tecnico tec1 = new Tecnico("Piera", 32311396);
        tecnicoDAO.put(tec1);

        Tecnico tec2 = new Tecnico("Mariana", 30817564);
        tecnicoDAO.put(tec2);

        Tecnico tec3 = new Tecnico("Paloma", 61226260);
        tecnicoDAO.put(tec3);


        int cont = 1;
        for (Tecnico tecEsperado : cache.values()) {
            assertEquals(tecEsperado.getNome(), tecnicoDAO.voltaCashTecnico().get(cont).getNome());
            assertEquals(tecEsperado.getTelefone(), tecnicoDAO.voltaCashTecnico().get(cont).getTelefone());
            cont++;
        }
    }
}
