/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.Empresa;
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
public class EmpresaDAOTest {

    private EmpresaDAO empresaDAO;
    private HashMap<Long, Empresa> cache;

    public EmpresaDAOTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("empresas.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            cache = new HashMap<>();

            cache.put((long) 100001, new Empresa(100001, "Mackenzie"));

            oos.writeObject(cache);

            oos.flush();
            fos.flush();

            oos.close();
        }

        empresaDAO = new EmpresaDAO();
    }

    @After
    public void tearDown() {
        File file = new File("empresas.dat");
        file.delete();
    }

    @Test
    public void testIncluirNovaEmpresaNaBaseDeDados() throws FileNotFoundException, IOException, ClassNotFoundException {
        //cenário que inclui uma empresa que ainda não foi incluida na base de dados
        Empresa novaEmp = new Empresa(100002, "Citrosuco");

        empresaDAO.put(novaEmp);

        FileInputStream fis = new FileInputStream("empresas.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<Long, Empresa> cacheRetornado = (HashMap<Long, Empresa>) ois.readObject();

        ois.close();
        fis.close();

        assertEquals(novaEmp.getNomeEmpresa(), cacheRetornado.get((long) 100002).getNomeEmpresa());
        assertEquals(novaEmp.getNumeroContrato(), cacheRetornado.get((long) 100002).getNumeroContrato());
    }

    @Test(expected = Exception.class)
    public void testIncluirEmpresaJaExistenteNaBaseDeDados() {
        //cenário que inclui uma empresa que já foi incluida na base de dados
        Empresa empExistente = new Empresa(100001, "Mackenzie");  //empresa adicionada no setUp dessa classe

        empresaDAO.put(empExistente);
    }

    @Test
    public void testBuscarUmaEmpresaIncluida() {
        //cenário que busca uma empresa que já foi incluida na base de dados
        Empresa emp = new Empresa(100003, "Del Valle");
        empresaDAO.put(emp);

        assertEquals(emp.getNomeEmpresa(), empresaDAO.voltaEmpresa().get(emp.getNumeroContrato()).getNomeEmpresa());
        assertEquals(emp.getNumeroContrato(), empresaDAO.voltaEmpresa().get(emp.getNumeroContrato()).getNumeroContrato());
    }

    @Test
    public void testBuscarVariasEmpresasIncluidas() {
        //cenário que busca várias empresas que já foram incluidas na base de dados
        Empresa emp1 = new Empresa(100003, "Del Valle");
        cache.put(emp1.getNumeroContrato(), emp1);
        empresaDAO.put(emp1);

        Empresa emp2 = new Empresa(100004, "Facebook");
        cache.put(emp2.getNumeroContrato(), emp2);
        empresaDAO.put(emp2);

        Empresa emp3 = new Empresa(100005, "Forever 21");
        cache.put(emp3.getNumeroContrato(), emp3);
        empresaDAO.put(emp3);

        for (Empresa empEsperada : cache.values()) {
            assertEquals(empEsperada.getNomeEmpresa(), empresaDAO.voltaEmpresa().get(empEsperada.getNumeroContrato()).getNomeEmpresa());
        }
    }
}
