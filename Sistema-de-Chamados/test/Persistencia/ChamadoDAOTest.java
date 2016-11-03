/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.Chamado;
import entidade.ClienteEmpresa;
import entidade.Empresa;
import entidade.RegistroChamado;
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
public class ChamadoDAOTest {
    
    private ChamadoDAO chamadoDAO;
    private ClienteEmpresa ce;
    private Tecnico t;
    private Chamado c;
    private HashMap<Integer, Chamado> cacheChamados;
    private HashMap<Integer, RegistroChamado> cacheRegistros;
    
    public ChamadoDAOTest() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        ce = new ClienteEmpresa(001, new Empresa(1010, "Dell"), 00000000000, "Bárbara", 12341234);
        t = new Tecnico("João", 43214321);
        c = new Chamado(ce.getCodigo(), "Problema de Rede", "Sem serviço de conexão wi-fi", 1, t, ce, "Windows", "10", "wireless", "192.168.1.1");
        
        try (FileOutputStream fos = new FileOutputStream("chamados.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            cacheChamados = new HashMap<>();

            cacheChamados.put(1, new Chamado("Problema de Banco de Dados", "Dados de clientes duplicados", 4, t, ce, "Linux", "15.9", "SQL"));

            oos.writeObject(cacheChamados);

            oos.flush();
            fos.flush();

            oos.close();
        }

        try (FileOutputStream fosR = new FileOutputStream("registroChamados.dat")) {
            ObjectOutputStream oosR = new ObjectOutputStream(fosR);

            cacheRegistros = new HashMap<>();

            cacheRegistros.put(1, new RegistroChamado("Sem serviço de rede", c, t));

            oosR.writeObject(cacheRegistros);

            oosR.flush();
            fosR.flush();

            oosR.close();
        }
        
    }

    @After
    public void tearDown() {
        File fileChamado = new File("chamados.dat");
        fileChamado.delete();

        File fileRegistro = new File("registroChamados.dat");
        fileRegistro.delete();
    }
    
    @Test
    public void testIncluirNovoRegistroNaBaseDeDados() throws FileNotFoundException, IOException, ClassNotFoundException {
        //cenário que inclui um registro de chamado que ainda não foi incluido na base de dados
        c = new Chamado("Problema de SO", "Sistema Operacional com lentidão",
                3, t, ce, "Windows", "Vista", "Manutenção de hardware", 20.10);
        RegistroChamado novoReg = new RegistroChamado("Problema de SO", c, t);

        chamadoDAO.putRegistro(novoReg);

        FileInputStream fis = new FileInputStream("registroChamados.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<Integer, RegistroChamado> cacheRetornado = (HashMap<Integer, RegistroChamado>) ois.readObject();

        ois.close();
        fis.close();

        assertEquals(novoReg.getAssunto(), cacheRetornado.get(2).getAssunto());
        assertEquals(novoReg.getChamado().getCausaProblema(), cacheRetornado.get(2).getChamado().getCausaProblema());
    }

    @Test(expected = Exception.class)
    public void testIncluirChamadoJaExistenteNaBaseDeDados() {
        //cenário que inclui um chamado que já foi incluido na base de dados
        Chamado chamadoExistente = new Chamado("Problema de Banco de Dados", "Dados de clientes duplicados", 4, t, ce, "Linux", "15.9", "SQL");  //chamado adicionada no setUp dessa classe

        chamadoDAO.put(chamadoExistente);
    }

    @Test
    public void testBuscarUmChamadoIncluido() {
        //cenário que busca um chamado que já foi incluido na base de dados
        c = new Chamado("Problema de SO", "Sistema Operacional com lentidão",
                3, t, ce, "Windows", "Vista", "Manutenção de hardware", 20.10);
        chamadoDAO.put(c);

        assertTrue(chamadoDAO.getChamados().contains(c));
    }
}
