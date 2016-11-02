/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.ClienteEmpresa;
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
public class ClienteDAOTest {

    private ClienteDAO clienteDAO;
    private HashMap<Long, ClienteEmpresa> cache;

    public ClienteDAOTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("clientes.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            cache = new HashMap<>();

            String cpf = "22824265760";

            cache.put(Long.parseLong(cpf), new ClienteEmpresa(1, new Empresa(100001, "Mackenzie"), Long.parseLong(cpf), "Paula", 37666570));

            oos.writeObject(cache);

            oos.flush();
            fos.flush();

            oos.close();
        }

        clienteDAO = new ClienteDAO();
    }

    @After
    public void tearDown() {
        File file = new File("clientes.dat");
        file.delete();
    }

    @Test
    public void testIncluirNovoClienteNaBaseDeDados() throws FileNotFoundException, IOException, ClassNotFoundException {
        //cenário que inclui um cliente que ainda não foi incluido na base de dados
        Empresa emp = new Empresa(100002, "Citrosuco");
        ClienteEmpresa novoCli = new ClienteEmpresa(2, emp, Long.parseLong("14947671980"), "Luiza", 42733492);

        clienteDAO.put(novoCli);

        FileInputStream fis = new FileInputStream("clientes.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<Long, ClienteEmpresa> cacheRetornado = (HashMap<Long, ClienteEmpresa>) ois.readObject();

        ois.close();
        fis.close();

        assertEquals(novoCli.getCpf(), cacheRetornado.get(novoCli.getCpf()).getCpf());
        assertEquals(novoCli.getEmpresa().getNomeEmpresa(), cacheRetornado.get(novoCli.getCpf()).getEmpresa().getNomeEmpresa());
        assertEquals(novoCli.getNome(), cacheRetornado.get(novoCli.getCpf()).getNome());
        assertEquals(novoCli.getTelefone(), cacheRetornado.get(novoCli.getCpf()).getTelefone());
    }

    @Test(expected = Exception.class)
    public void testIncluirClienteJaExistenteNaBaseDeDados() {
        //cenário que inclui um cliente que já foi incluido na base de dados
        ClienteEmpresa cliExistente = new ClienteEmpresa(1, new Empresa(100001, "Mackenzie"), Long.parseLong("22824265760"), "Paula", 37666570);  //cliente adicionado no setUp dessa classe

        clienteDAO.put(cliExistente);
    }

    @Test
    public void testBuscarUmClienteIncluido() {
        //cenário que busca um cliente que já foi incluida na base de dados
        Empresa emp = new Empresa(100003, "Del Valle");
        ClienteEmpresa cli = new ClienteEmpresa(2, emp, Long.parseLong("35283912191"), "Brenda", 56342019);
        clienteDAO.put(cli);

        assertEquals(cli.getCpf(), clienteDAO.get(cli.getCpf()).getCpf());
        assertEquals(cli.getEmpresa().getNomeEmpresa(), clienteDAO.get(cli.getCpf()).getEmpresa().getNomeEmpresa());
        assertEquals(cli.getNome(), clienteDAO.get(cli.getCpf()).getNome());
        assertEquals(cli.getTelefone(), clienteDAO.get(cli.getCpf()).getTelefone());
    }

    @Test
    public void testBuscarVariosClientesIncluidos() {
        //cenário que busca vários clientes que já foram incluidos na base de dados
        Empresa emp1 = new Empresa(100003, "Del Valle");
        ClienteEmpresa cli1 = new ClienteEmpresa(2, emp1, Long.parseLong("30725306700"), "Gabriel", 35565761);
        cache.put(cli1.getCpf(), cli1);
        clienteDAO.put(cli1);

        Empresa emp2 = new Empresa(100004, "Facebook");
        ClienteEmpresa cli2 = new ClienteEmpresa(3, emp2, Long.parseLong("17025305740"), "Douglas", 34823558);
        cache.put(cli2.getCpf(), cli2);
        clienteDAO.put(cli2);

        Empresa emp3 = new Empresa(100005, "Forever 21");
        ClienteEmpresa cli3 = new ClienteEmpresa(4, emp3, Long.parseLong("90633164100"), "Isabella", 34145580);
        cache.put(cli3.getCpf(), cli3);
        clienteDAO.put(cli3);

        for (ClienteEmpresa cliEsperado : cache.values()) {
            assertEquals(cliEsperado.getCpf(), clienteDAO.get(cliEsperado.getCpf()).getCpf());
            assertEquals(cliEsperado.getEmpresa().getNomeEmpresa(), clienteDAO.get(cliEsperado.getCpf()).getEmpresa().getNomeEmpresa());
            assertEquals(cliEsperado.getNome(), clienteDAO.get(cliEsperado.getCpf()).getNome());
            assertEquals(cliEsperado.getTelefone(), clienteDAO.get(cliEsperado.getCpf()).getTelefone());
        }
    }
}
