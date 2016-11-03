/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ClienteEmpresa;
import entidade.Empresa;
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
 * @author Ana Carolina
 */
public class ControladorPrincipalTest {

    ControladorPrincipal cp;

    public ControladorPrincipalTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        //criação do arquivo de empresas
        FileOutputStream fos = new FileOutputStream("empresas.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        HashMap<Long, Empresa> cacheEmp = new HashMap<>();

        cacheEmp.put((long) 100001, new Empresa(100001, "Mackenzie"));

        oos.writeObject(cacheEmp);

        oos.flush();
        fos.flush();

        oos.close();
        fos.close();

        //criação do arquivo de técnicos
        fos = new FileOutputStream("tecnicos.dat");
        oos = new ObjectOutputStream(fos);

        HashMap<Integer, Tecnico> cacheTec = new HashMap<>();

        cacheTec.put(1, new Tecnico("Ana Carolina", 28121311));

        oos.writeObject(cacheTec);

        oos.flush();
        fos.flush();

        oos.close();
        fos.close();

        //criação do arquivo de clientes
        fos = new FileOutputStream("clientes.dat");
        oos = new ObjectOutputStream(fos);

        HashMap<Long, ClienteEmpresa> cacheCli = new HashMap<>();

        String cpf = "22824265760";

        cacheCli.put(Long.parseLong(cpf), new ClienteEmpresa(1, new Empresa(100001, "Mackenzie"), Long.parseLong(cpf), "Paula", 37666570));

        oos.writeObject(cacheCli);

        oos.flush();
        fos.flush();

        oos.close();
        fos.close();

        //criação do arquivo de chamados
        fos = new FileOutputStream("chamados.dat");
        oos = new ObjectOutputStream(fos);
        
        oos.writeObject(cashChamado);
        
        oos.flush();
        fos.flush();
        
        oos.close();
        fos.close();

        //criação do arquivo de registro de chamados
        fos = new FileOutputStream("registroChamados.dat");
        oos = new ObjectOutputStream(fos);
        
        oos.writeObject(cashRegistroChamado);
        
        oos.flush();
        fos.flush();

        oos.close();
        fos.close();
    }

    @After
    public void tearDown() {
        File fileEmp = new File("empresas.dat");
        fileEmp.delete();

        File fileTec = new File("tecnicos.dat");
        fileTec.delete();

        File fileCli = new File("clientes.dat");
        fileCli.delete();
    }

    @Test
    public void testConfereSeControladoresForamCriados() {
        cp = new ControladorPrincipal();

        assertNotNull(cp.getCtrChamados());
        assertNotNull(cp.getCtrClientes());
        assertNotNull(cp.getCtrEmpresa());
        assertNotNull(cp.getCtrTecnicos());
    }

    @Test
    public void TestIniciarControladorPrincipal() {
        //cenário onde se inicializa o controlador principal
    }
}
