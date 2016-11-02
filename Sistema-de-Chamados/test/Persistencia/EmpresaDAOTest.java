/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.Empresa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
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

    EmpresaDAO empresaDAO;

    public EmpresaDAOTest() {
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

        empresaDAO = new EmpresaDAO();
    }

    @After
    public void tearDown() {
        File file = new File("empresas.dat");
        file.delete();
    }

    @Test
    public void testIncluirNovaEmpresaNaBaseDeDados() {
        //cenário que inclui uma empresa que ainda não foi incluida na base de dados
        Empresa novaEmp = new Empresa(100002, "Citrosuco");

        empresaDAO.put(novaEmp);
        assertTrue(empresaDAO.voltaEmpresa().containsValue(novaEmp));
    }

    @Test(expected = Exception.class)
    public void testIncluirEmpresaNovamenteNaBaseDeDados() {
        //cenário que inclui uma empresa que já foi incluida na base de dados
        Empresa empExistente = new Empresa(100001, "Mackenzie");
        
        empresaDAO.put(empExistente);
    }
    
    @Test
    public void testBuscarUmaEmpresaIncluidaNaBaseDeDados(){
        Collection<Empresa> empresas = new ArrayList<>();
        Empresa emp = new Empresa(100001, "Mackenzie");
        empresas.add(emp);
    }
}
